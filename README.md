# Coffee API

This project is a Spring Boot application that provides a RESTful API to manage coffee entities. It demonstrates the use of JPA entity graphs, custom repository implementations, dynamic field selection, and data preloading at startup.

## Features

- CRUD operations for Coffee entities
- Dynamic selection of fields at runtime using query parameters
- Preload sample data at application startup
- Custom JPA repository for dynamic queries
- Ignore null values in JSON responses

## Requirements

- Java 8 or higher
- Maven
- A database (H2, MySQL, PostgreSQL, etc.)

## Getting Started

### Clone the Repository

```sh
git clone https://github.com/salvatorecorsaro/CoffeeDynamicFieldsWithEntityGraph.git
cd coffee-api
