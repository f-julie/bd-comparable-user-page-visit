package com.amazon.ata.comparable_comparator_sort;

/**
 * This class contains statistics for a particular user page view, defined by its userId and page. We create instances
 * of this class by providing a base UserPageVisit object, which is used to check if other UserPageVisits can be
 * added to the current instance. The stats that are tracked are the average time spent on page, total time spent on
 * page and the number of times a user has visited the given page.
 */
public class UserPageVisitAggregate {
    private UserPageVisit baseVisit;
    private int totalTimeOnPageInSeconds;
    private int numberOfVisits;

    /**
     * Constructs a UserPageVisitAggregate object from the provided user page visit object.
     * @param baseVisit initial page visit which is used to check if other page visit objects can
     *                  be added to this aggregate.
     */
    public UserPageVisitAggregate(UserPageVisit baseVisit) {
        this.baseVisit = baseVisit;
        this.totalTimeOnPageInSeconds = baseVisit.getTimeOnPageInSeconds();
        this.numberOfVisits = 1;
    }

    /**
     * Modifies all the stats with new value if the provided visit matches stored base visit.
     * @param userPageVisit UserPageVisit whose value we're adding to the stats.
     */
    public void aggregate(UserPageVisit userPageVisit) {
        if (canAggregate(userPageVisit)) {
            this.totalTimeOnPageInSeconds += userPageVisit.getTimeOnPageInSeconds();
            this.numberOfVisits++;
        }
    }

    /**
     * Checks if the provided visit can be aggregated with this visit aggregate.
     * @param userPageVisit UserPageVisit whose value we're adding to the stats.
     * @return true if provided visit can be aggregated, false otherwise.
     */
    public boolean canAggregate(UserPageVisit userPageVisit) {
        return this.baseVisit.equals(userPageVisit);
    }

    public double getTotalTimeOnPageInSeconds() {
        return totalTimeOnPageInSeconds;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public double getAverageTimeOnPageInSeconds() {
        return (double) totalTimeOnPageInSeconds / numberOfVisits;
    }

    public long getUserId() {
        return baseVisit.getUserId();
    }

    public String getPage() {
        return baseVisit.getPage();
    }
}
