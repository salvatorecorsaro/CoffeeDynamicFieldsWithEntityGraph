# Coffee API

This project is a Spring Boot application that provides a RESTful API to manage coffee entities. It demonstrates the use of JPA entity graphs, custom repository implementations, dynamic field selection, and data preloading at startup.

## Features

- CRUD operations for Coffee entities
- Dynamic selection of fields at runtime using query parameters
- Preload sample data at application startup
- Custom JPA repository for dynamic queries
- Ignore null values in JSON responses

## Requirements

- Java 17 or higher
- Maven

## Filtering Requested Data from the Database

When working with databases, it's common to have entities with numerous fields, but in certain scenarios, you may only need a subset of those fields. Fetching all the fields from the database when you only require a few can be inefficient, especially when dealing with large datasets. This is where the concept of filtering requested data comes into play.

### Criteria API and Tuple Queries

The solution presented in the `CoffeeRepositoryCustomImpl` class utilizes the Jakarta Persistence Criteria API to construct a query that retrieves only the specified fields from the database. The Criteria API provides a type-safe way to build queries dynamically based on the provided criteria.

In this specific implementation, a `CriteriaQuery<Tuple>` is used instead of a `CriteriaQuery<Coffee>`. A `Tuple` is a generic container that can hold a set of values, each representing a selected field from the entity. By using a `Tuple`, we can select only the desired fields rather than fetching the entire entity.

### Building the Criteria Query

To build the criteria query, we start by creating a `CriteriaBuilder` and a `CriteriaQuery<Tuple>` using the `EntityManager`. The `CriteriaBuilder` is responsible for constructing query components, while the `CriteriaQuery<Tuple>` represents the query itself.

Next, we obtain the `Root<Coffee>` entity from the `CriteriaQuery` using `cq.from(Coffee.class)`. The `Root` represents the main entity being queried, which in this case is the `Coffee` entity.

### Selecting Specific Fields

To select only the requested fields, we use the `multiselect` method of the `CriteriaQuery`. The `multiselect` method allows us to specify multiple selections for the query.

In the implementation, we map each field name from the `fields` list to the corresponding attribute of the `Root` entity using `root::get`. This creates a `Selection` object for each field. We then collect these `Selection` objects into a list using `Collectors.toList()` and pass them to the `multiselect` method.

By using `multiselect` with the specified field selections, we instruct the query to retrieve only those fields from the database.

### Executing the Query

To execute the query and retrieve the results, we use `entityManager.createQuery(cq).getResultList()`. This creates a `TypedQuery<Tuple>` based on the `CriteriaQuery` and executes it, returning a list of `Tuple` objects.

Each `Tuple` in the result list represents a row of data with the selected fields. The values in the `Tuple` correspond to the fields specified in the `multiselect` method.

### Mapping Tuples to Entities

After retrieving the list of `Tuple` objects, we need to map them back to `Coffee` entities. This is done using a stream operation on the `results` list.

For each `Tuple`, we create a new `Coffee` entity and iterate over the selected fields. We use the `setCoffeeField` method to set the value of each field in the `Coffee` entity based on the field name and the corresponding value from the `Tuple`.

The `setCoffeeField` method uses a switch statement to determine which setter method to call on the `Coffee` entity based on the field name. This allows us to dynamically set the values of the selected fields in the entity.

Finally, the list of `Coffee` entities with the selected fields is returned.

### Advantages of Filtering Requested Data

Filtering requested data from the database offers several advantages:

1. Improved Performance: By fetching only the required fields, we reduce the amount of data transferred from the database to the application. This can significantly improve performance, especially when dealing with large datasets or complex entities with many fields.

2. Reduced Network Overhead: Retrieving only the necessary fields minimizes the network overhead involved in transferring data from the database to the application. This is particularly beneficial in scenarios where network bandwidth is limited or when working with remote databases.

3. Simplified Data Retrieval: By providing a flexible way to specify the desired fields, the solution simplifies the process of retrieving specific data from the database. The caller can dynamically choose which fields they need, making the data retrieval process more customizable and efficient.

4. Improved Application Efficiency: Fetching only the required fields reduces the memory footprint of the application, as it doesn't need to store and process unnecessary data. This can lead to better overall application performance and scalability.

### Conclusion

Filtering requested data from the database is a powerful technique that allows you to retrieve only the specific fields you need, rather than fetching entire entities. By utilizing the Jakarta Persistence Criteria API and Tuple queries, you can construct dynamic queries that select only the desired fields.

The `CoffeeRepositoryCustomImpl` class demonstrates how to implement this filtering mechanism, providing a flexible and efficient way to retrieve data from the database. By mapping the selected fields to the entity using a stream operation and the `setCoffeeField` method, you can create entities with only the requested fields populated.

Implementing data filtering can significantly improve application performance, reduce network overhead, and simplify data retrieval processes. It is particularly useful in scenarios where you have large datasets or complex entities and need to optimize data retrieval for specific use cases.

By understanding the theory behind filtering requested data and applying it in your application, you can enhance the efficiency and scalability of your database operations.



