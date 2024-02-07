package com.amazon.ata.comparable_comparator_sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserPageVisitGrouperTest {

    @Test
    public void groupUserPageVisits_visitsThatNeedToBeSorted_inputListIsNotModified() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(2L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "home-page", 10);
        List<UserPageVisit> visits = new ArrayList<>();
        visits.add(userPageVisit1);
        visits.add(userPageVisit2);

        UserPageVisitGrouper grouper = new UserPageVisitGrouper();

        // WHEN
        List<UserPageVisit> groupedVisits = grouper.groupUserPageVisits(visits);

        // THEN
        assertEquals(2, groupedVisits.size());
        assertEquals(2L, visits.get(0).getUserId());
        assertEquals(1L, visits.get(1).getUserId());
        assertEquals(1L, groupedVisits.get(0).getUserId());
        assertEquals(2L, groupedVisits.get(1).getUserId());
    }


    @Test
    public void groupUserPageVisits_visitsWithSameUserIdsSamePages_preservesAllVisits() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "home-page", 10);
        List<UserPageVisit> visits = new ArrayList<>();
        visits.add(userPageVisit1);
        visits.add(userPageVisit2);

        UserPageVisitGrouper grouper = new UserPageVisitGrouper();

        // WHEN
        List<UserPageVisit> groupedVisits = grouper.groupUserPageVisits(visits);

        // THEN
        assertEquals(2, groupedVisits.size());
    }

    @Test
    public void groupUserPageVisits_visitsWithDifferentUserIds_sortedByUserId() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(2L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "home-page", 10);
        List<UserPageVisit> visits = new ArrayList<>();
        visits.add(userPageVisit1);
        visits.add(userPageVisit2);

        UserPageVisitGrouper grouper = new UserPageVisitGrouper();

        // WHEN
        List<UserPageVisit> groupedVisits = grouper.groupUserPageVisits(visits);

        // THEN
        assertEquals(2, groupedVisits.size());
        assertEquals(1L, groupedVisits.get(0).getUserId());
        assertEquals(2L, groupedVisits.get(1).getUserId());
    }

    @Test
    public void groupUserPageVisits_visitsWithSameUserIdsDifferentPages_sortedByPages() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "product-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "home-page", 10);
        List<UserPageVisit> visits = new ArrayList<>();
        visits.add(userPageVisit1);
        visits.add(userPageVisit2);

        UserPageVisitGrouper grouper = new UserPageVisitGrouper();

        // WHEN
        List<UserPageVisit> groupedVisits = grouper.groupUserPageVisits(visits);

        // THEN
        assertEquals(2, groupedVisits.size());
        assertEquals("home-page", groupedVisits.get(0).getPage());
        assertEquals("product-page", groupedVisits.get(1).getPage());
    }

    @Test
    public void groupUserPageVisits_visitsWithDifferentUserIdsDifferentPages_sortedByPages() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(2L, "product-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "home-page", 10);
        List<UserPageVisit> visits = new ArrayList<>();
        visits.add(userPageVisit1);
        visits.add(userPageVisit2);

        UserPageVisitGrouper grouper = new UserPageVisitGrouper();

        // WHEN
        List<UserPageVisit> groupedVisits = grouper.groupUserPageVisits(visits);

        // THEN
        assertEquals(2, groupedVisits.size());
        assertEquals(1L, groupedVisits.get(0).getUserId());
        assertEquals(2L, groupedVisits.get(1).getUserId());
    }

}
