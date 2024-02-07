## Read (5 min)

### Introduction to data aggregation

Data aggregation is any process in which information is gathered and expressed in a summary form for purposes such as
statistical analysis. One of the main benefits of data aggregation is reducing the amount of data that needs to be
stored. Imagine if you were collecting data about different age groups in the United States. If you wanted to store an
age variable for every individual currently living in the country (more than 300 million people) and if you stored them
as integer values, that would require ~1.2 gigabytes of space (4 bytes * 300,000,000). But it is highly unlikely that
you would be interested in individual age values. What you would probably look at is the average age, standard
deviation, min and max age, number of people in each of the age groups and so on. Storing that type of information
requires much less space, anywhere between 1-10 million times less space, but it still provides you with most of the
insights that are useful.

### Scenario: Aggregating data from logs

Imagine you are running a service that sells products to users. A user logs in to your service, they are shown a home
page and from there they're able to search for desired products. The service keeps track of their activities as they are
using your service and stores that information in the form of logs (text records). There are three main pieces of
information that are being tracked:

* UserId - A unique number that identifies each user. By using this number in logs, as opposed to name and surname,
  we're protecting user details.
* Page - The page that the user visited while using the service e.g. home page, product page, checkout page etc.
* Time spent on page - number of seconds the user spent looking at the mentioned page.

Here's how a snippet taken from the log file might look:

```
userId, page, timeOnPageInSeconds
23, "home-page", 15
23, "productA-page", 30
42, "home-page", 10
23, "checkout-page", 20
42, "product-page", 5
42, "home-page", 15
23, "home-page", 20
```

While the information stored in the logs helps us track user activity in the system, it is not enough on its own. First
of all the information is not sorted, either per user or per page. That is expected since the service handles multiple
users at the same time and they are all going to different pages as they're using the service. Second problem is that
the data in its current form is too detailed. What we would prefer to have is a summary of how much time each user spent
on each page in total and on average. We might also be interested in how many times the user visited a specific page.

To achieve both of the mentioned goals, we need to be able to sort the data captured in logs. After the data is sorted,
aggregating it becomes much simpler (and we'll see why a bit later). To be able to sort this data, we will create a
class that will represent each individual record stored in the log file and have the mentioned class implement the
Comparable interface.

## Try (15 min)

`UserPageVisit` represents a single record stored in the log file. The class implements the `Comparable` interface to
enable instances of the class to be sorted. The visits are sorted by `userId` and then by the `page`. Although
`UserPageVisit` contains the time spent on page, it is used in aggregation, and ignored when sorting.

Implement the methods in `UserPageVisit` according to their documentation.
Verify your methods are implemented correctly by running `UserPageVisitTest`.
You're not expected to write any new tests.

**Doneness checklist:**
- `UserPageVisitTest` tests all pass

## Read (10 min)

Now that we have defined `UserPageVisit`, we can describe the sorting and aggregation workflow.

The first step is to sort the visits based on the natural ordering.
Sorting the original log snippet would result in:

```
userId, page, timeOnPageInSeconds
23, "checkout-page", 20
23, "home-page", 15
23, "home-page", 20
23, "productA-page", 30
42, "home-page", 10
42, "home-page", 15
42, "product-page", 5
```

Now that the log records are sorted, we can apply the aggregation. The aggregation algorithm will go through the sorted
list of visits, and if the `userId` and `page` remain unchanged it will aggregate the data for that particular visit. Once
the `userId` or `page` changes, the aggregation for the new visit will begin, until we have aggregated data for all unique
visits. The aggregation algorithm will keep track of total and average time spent on each page, as well as the number of
visits for each page. The final data will look like this:

```
userId, page, totalTimeOnPageInSeconds, averageTimeOnPageInSeconds, numberOfVisits
23, "checkout-page", 20, 20.0, 1
23, "home-page", 35, 17.5, 2
23, "productA-page", 30, 30.0, 1
42, "home-page", 25, 12.5, 2
42, "product-page", 5, 5.0, 1
```

Now, read the code and documentation for `UserPageVisitAggregate` and `UserPageVisitAggregator`. Pay attention to the
following:
* How is `UserPageVisitGrouper` being used in `UserPageVisitAggregator`
* How does `UserPageVisitAggregator` decide when to create a new `UserPageVisitAggregate` - effectively starting a new
  aggregation.

## Try (5 min)

`UserPageVisitGrouper` is nearly complete; it just needs the method to sort `UserPageVisit` in ascending order by user
ID and page as `UserPageVisitAggregator` expects.

Implement that method according to its documentation, the run the `UserPageVisitGrouperTest` and
`UserPageVisitAggregatorTest` through IntelliJ. 

Finally, run the `./gradlew -q clean :test` command from your command line. If everything passes,
your job here is done!

**Doneness checklist:**
- The `./gradlew -q clean :test` command passes
