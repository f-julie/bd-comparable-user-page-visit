package com.amazon.ata.comparable_comparator_sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserPageVisitAggregatorTest {
    @Test
    public void aggregate_emptyList_resultIsAlsoAnEmptyList() {
        // GIVEN
        List<UserPageVisit> visits = new ArrayList<>();

        UserPageVisitAggregator aggregator = new UserPageVisitAggregator(new UserPageVisitGrouper());

        // WHEN
        List<UserPageVisitAggregate> visitAggregates = aggregator.aggregate(visits);

        // THEN
        assertEquals(0, visitAggregates.size());
    }

    @Test
    public void aggregate_singleVisit_resultHasSingleVisitAggregateObject() {
        // GIVEN
        UserPageVisit userPageVisit = new UserPageVisit(1L, "home-page", 10);
        List<UserPageVisit> visits = new ArrayList<>();
        visits.add(userPageVisit);

        UserPageVisitAggregator aggregator = new UserPageVisitAggregator(new UserPageVisitGrouper());

        // WHEN
        List<UserPageVisitAggregate> visitAggregates = aggregator.aggregate(visits);

        // THEN
        assertEquals(1, visitAggregates.size());
        assertEquals(1, visitAggregates.get(0).getNumberOfVisits());
        assertEquals(10, visitAggregates.get(0).getTotalTimeOnPageInSeconds());
        assertEquals(10.0, visitAggregates.get(0).getAverageTimeOnPageInSeconds());
    }

    @Test
    public void aggregate_multipleEqualVisits_resultHasSingleVisitAggregateObject() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "home-page", 20);
        List<UserPageVisit> visits = new ArrayList<>();
        visits.add(userPageVisit1);
        visits.add(userPageVisit2);

        UserPageVisitAggregator aggregator = new UserPageVisitAggregator(new UserPageVisitGrouper());

        // WHEN
        List<UserPageVisitAggregate> visitAggregates = aggregator.aggregate(visits);

        // THEN
        assertEquals(1, visitAggregates.size());
        assertEquals(2, visitAggregates.get(0).getNumberOfVisits());
        assertEquals(30, visitAggregates.get(0).getTotalTimeOnPageInSeconds());
        assertEquals(15.0, visitAggregates.get(0).getAverageTimeOnPageInSeconds());
    }

    @Test
    public void aggregate_multipleVisitsWithDifferentUserIds_resultHasMultipleVisitAggregateObjects() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(2L, "home-page", 20);
        List<UserPageVisit> visits = new ArrayList<>();
        visits.add(userPageVisit1);
        visits.add(userPageVisit2);

        UserPageVisitAggregator aggregator = new UserPageVisitAggregator(new UserPageVisitGrouper());

        // WHEN
        List<UserPageVisitAggregate> visitAggregates = aggregator.aggregate(visits);

        // THEN
        assertEquals(2, visitAggregates.size());
        assertEquals(1, visitAggregates.get(0).getNumberOfVisits());
        assertEquals(10, visitAggregates.get(0).getTotalTimeOnPageInSeconds());
        assertEquals(10.0, visitAggregates.get(0).getAverageTimeOnPageInSeconds());
        assertEquals(1, visitAggregates.get(1).getNumberOfVisits());
        assertEquals(20, visitAggregates.get(1).getTotalTimeOnPageInSeconds());
        assertEquals(20.0, visitAggregates.get(1).getAverageTimeOnPageInSeconds());
    }

    @Test
    public void aggregate_multipleVisitsnWithSameUserIdsDifferentPages_resultHasMultipleMetricAggregateObjects() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "product-page", 20);
        List<UserPageVisit> visits = new ArrayList<>();
        visits.add(userPageVisit1);
        visits.add(userPageVisit2);

        UserPageVisitAggregator aggregator = new UserPageVisitAggregator(new UserPageVisitGrouper());

        // WHEN
        List<UserPageVisitAggregate> visitAggregates = aggregator.aggregate(visits);

        // THEN
        assertEquals(2, visitAggregates.size());
        assertEquals(1, visitAggregates.get(0).getNumberOfVisits());
        assertEquals(10, visitAggregates.get(0).getTotalTimeOnPageInSeconds());
        assertEquals(10.0, visitAggregates.get(0).getAverageTimeOnPageInSeconds());
        assertEquals(1, visitAggregates.get(1).getNumberOfVisits());
        assertEquals(20, visitAggregates.get(1).getTotalTimeOnPageInSeconds());
        assertEquals(20.0, visitAggregates.get(1).getAverageTimeOnPageInSeconds());
    }
}
