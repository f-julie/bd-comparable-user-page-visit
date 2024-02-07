package com.amazon.ata.comparable_comparator_sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserPageVisitAggregateTest {
    @Test
    public void canAggregate_sameReference_returnsTrue() {
        // GIVEN
        UserPageVisit userPageVisit = new UserPageVisit(1L, "home-page", 10);
        UserPageVisitAggregate aggregate = new UserPageVisitAggregate(userPageVisit);

        // WHEN
        boolean result = aggregate.canAggregate(userPageVisit);

        // THEN
        assertTrue(result);
    }

    @Test
    public void canAggregate_sameUserIdSamePage_returnsTrue() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisitAggregate aggregate = new UserPageVisitAggregate(userPageVisit1);

        // WHEN
        boolean result = aggregate.canAggregate(userPageVisit2);

        // THEN
        assertTrue(result);
    }

    @Test
    public void canAggregate_sameUserIdDifferentPage_returnsFalse() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "product-page", 10);
        UserPageVisitAggregate aggregate = new UserPageVisitAggregate(userPageVisit1);

        // WHEN
        boolean result = aggregate.canAggregate(userPageVisit2);

        // THEN
        assertFalse(result);
    }

    @Test
    public void canAggregate_differentUserIdSamePage_returnsFalse() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(2L, "home-page", 10);
        UserPageVisitAggregate aggregate = new UserPageVisitAggregate(userPageVisit1);

        // WHEN
        boolean result = aggregate.canAggregate(userPageVisit2);

        // THEN
        assertFalse(result);
    }

    @Test
    public void canAggregate_differentUserIdDifferentPage_returnsFalse() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(2L, "product-page", 10);
        UserPageVisitAggregate aggregate = new UserPageVisitAggregate(userPageVisit1);

        // WHEN
        boolean result = aggregate.canAggregate(userPageVisit2);

        // THEN
        assertFalse(result);
    }

    @Test
    public void aggregate_sameReference_observationsAggregated() {
        // GIVEN
        UserPageVisit userPageVisit = new UserPageVisit(1L, "home-page", 10);
        UserPageVisitAggregate aggregate = new UserPageVisitAggregate(userPageVisit);

        // WHEN
        aggregate.aggregate(userPageVisit);

        // THEN
        assertEquals(1L, aggregate.getUserId());
        assertEquals("home-page", aggregate.getPage());
        assertEquals(2, aggregate.getNumberOfVisits());
        assertEquals(20, aggregate.getTotalTimeOnPageInSeconds());
        assertEquals(10.0, aggregate.getAverageTimeOnPageInSeconds());
    }

    @Test
    public void aggregate_sameUserIdSamePage_observationsAggregated() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisitAggregate aggregate = new UserPageVisitAggregate(userPageVisit1);

        // WHEN
        aggregate.aggregate(userPageVisit2);

        // THEN
        assertEquals(1L, aggregate.getUserId());
        assertEquals("home-page", aggregate.getPage());
        assertEquals(2, aggregate.getNumberOfVisits());
        assertEquals(20, aggregate.getTotalTimeOnPageInSeconds());
        assertEquals(10.0, aggregate.getAverageTimeOnPageInSeconds());
    }

    @Test
    public void aggregate_sameUserIdDifferentPage_observationsNotAggregated() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "product-page", 10);
        UserPageVisitAggregate aggregate = new UserPageVisitAggregate(userPageVisit1);

        // WHEN
        aggregate.aggregate(userPageVisit2);

        // THEN
        assertEquals(1L, aggregate.getUserId());
        assertEquals("home-page", aggregate.getPage());
        assertEquals(1, aggregate.getNumberOfVisits());
        assertEquals(10, aggregate.getTotalTimeOnPageInSeconds());
        assertEquals(10.0, aggregate.getAverageTimeOnPageInSeconds());
    }

    @Test
    public void aggregate_differentUserIdSamePage_observationsNotAggregated() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(2L, "home-page", 10);
        UserPageVisitAggregate aggregate = new UserPageVisitAggregate(userPageVisit1);

        // WHEN
        aggregate.aggregate(userPageVisit2);

        // THEN
        assertEquals(1L, aggregate.getUserId());
        assertEquals("home-page", aggregate.getPage());
        assertEquals(1, aggregate.getNumberOfVisits());
        assertEquals(10, aggregate.getTotalTimeOnPageInSeconds());
        assertEquals(10.0, aggregate.getAverageTimeOnPageInSeconds());
    }

    @Test
    public void aggregate_differentUserIdDifferentPage_observationsNotAggregated() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(2L, "product-page", 10);
        UserPageVisitAggregate aggregate = new UserPageVisitAggregate(userPageVisit1);

        // WHEN
        aggregate.aggregate(userPageVisit2);

        // THEN
        assertEquals(1L, aggregate.getUserId());
        assertEquals("home-page", aggregate.getPage());
        assertEquals(1, aggregate.getNumberOfVisits());
        assertEquals(10, aggregate.getTotalTimeOnPageInSeconds());
        assertEquals(10.0, aggregate.getAverageTimeOnPageInSeconds());
    }
}
