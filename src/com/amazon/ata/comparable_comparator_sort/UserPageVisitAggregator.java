package com.amazon.ata.comparable_comparator_sort;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains logic that aggregates a list of UserPageVisits by relying on UserPageVisitGrouper for
 * grouping them in correct order before applying aggregation and creating a list of UserPageVisitAggregates.
 */
public class UserPageVisitAggregator {
    private UserPageVisitGrouper userPageVisitGrouper;

    /**
     * Creats an instance of UserPageVisitAggregator.
     * @param userPageVisitGrouper object used to group the visits before aggregating
     */
    public UserPageVisitAggregator(UserPageVisitGrouper userPageVisitGrouper) {
        this.userPageVisitGrouper = userPageVisitGrouper;
    }

    /**
     * Aggregates unsorted list of UserPageVisits by using the UserPageVisitGrouper to
     * sort them in ascending order, and aggregating the UserPageVisits for particular user page visit.
     * @param userPageVisits List of UserPageVisits
     * @return List of UserPageVisitAggregate containing list of statistics for different user page visits.
     */
    public List<UserPageVisitAggregate> aggregate(List<UserPageVisit> userPageVisits) {
        List<UserPageVisitAggregate> visitAggregates = new ArrayList<>();

        if (null == userPageVisits || userPageVisits.isEmpty()) {
            return visitAggregates;
        }

        List<UserPageVisit> groupedUserPageVisits = userPageVisitGrouper.groupUserPageVisits(userPageVisits);

        // The first visit always starts a new group
        UserPageVisitAggregate visitAggregate = new UserPageVisitAggregate(groupedUserPageVisits.get(0));

        for (int i = 1; i < groupedUserPageVisits.size(); i++) {
            UserPageVisit visit = groupedUserPageVisits.get(i);
            if (visitAggregate.canAggregate(visit)) {
                visitAggregate.aggregate(visit);
            } else {
                visitAggregates.add(visitAggregate);
                visitAggregate = new UserPageVisitAggregate(visit);
            }
        }
        visitAggregates.add(visitAggregate);

        return visitAggregates;
    }
}
