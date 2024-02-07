package com.amazon.ata.comparable_comparator_sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserPageVisitTest {
    @Test
    public void compareTo_visitsWithSameUserIdsSamePages_resultIsZero() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "home-page", 10);

        // WHEN
        int result = userPageVisit1.compareTo(userPageVisit2);

        // THEN
        assertEquals(0, result);
    }

    @Test
    public void compareTo_visitsWithLeftHavingLowerUserId_resultIsNegative() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(2L, "home-page", 10);

        // WHEN
        int result = userPageVisit1.compareTo(userPageVisit2);

        // THEN
        assertTrue(result < 0, String.format("Expected %s to come before %s", userPageVisit1, userPageVisit2));
    }

    @Test
    public void compareTo_visitsWithLeftHavingHigherUserId_resultIsPositive() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(100L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(2L, "home-page", 10);

        // WHEN
        int result = userPageVisit1.compareTo(userPageVisit2);

        // THEN
        assertTrue(result > 0, String.format("Expected %s to come after %s", userPageVisit1, userPageVisit2));
    }

    @Test
    public void compareTo_visitsWithSameUserIdsLeftHavingFirstPage_resultIsNegative() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "product-page", 10);

        // WHEN
        int result = userPageVisit1.compareTo(userPageVisit2);

        // THEN
        assertTrue(result < 0, String.format("Expected %s to come before %s", userPageVisit1, userPageVisit2));
    }

    @Test
    public void compareTo_visitsWithSameUserIdsLeftHavingLaterPage_resultIsPositive() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "zebra-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "product-page", 10);

        // WHEN
        int result = userPageVisit1.compareTo(userPageVisit2);

        // THEN
        assertTrue(result > 0, String.format("Expected %s to come after %s", userPageVisit1, userPageVisit2));
    }

    @Test
    public void compareTo_visitsWithSameUserIdsSamePages_resultMatchesResultOfCallingEquals() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "home-page", 10);

        // WHEN
        int compareResult = userPageVisit1.compareTo(userPageVisit2);

        // THEN
        boolean equalsResult = userPageVisit1.equals(userPageVisit2);
        assertTrue(equalsResult);
        assertEquals(0, compareResult);
    }

    @Test
    public void compareTo_visitsWithSameDifferentUserIds_resultMatchesResultOfCallingEquals() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(2L, "home-page", 10);

        // WHEN
        int compareResult = userPageVisit1.compareTo(userPageVisit2);

        // THEN
        boolean equalsResult = userPageVisit1.equals(userPageVisit2);
        assertFalse(equalsResult);
        assertNotEquals(0, compareResult);
    }

    @Test
    public void compareTo_visitsWithSameUserIdsDifferentPages_resultMatchesResultOfCallingEquals() {
        // GIVEN
        UserPageVisit userPageVisit1 = new UserPageVisit(1L, "home-page", 10);
        UserPageVisit userPageVisit2 = new UserPageVisit(1L, "product-page", 10);

        // WHEN
        int compareResult = userPageVisit1.compareTo(userPageVisit2);

        // THEN
        boolean equalsResult = userPageVisit1.equals(userPageVisit2);
        assertFalse(equalsResult);
        assertNotEquals(0, compareResult);
    }
}
