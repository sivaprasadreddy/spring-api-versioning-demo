# Spring API Versioning

This project demonstrates different approaches to API versioning in Spring Boot applications.

## Versioning Strategies

- **Header**
- **Query Parameter**
- **MediaType Parameter**
- **Path Segment**

## Examples

**1. Header**

```shell
GET /api/books
Accept: application/json
API-Version: 1.0
```

**2. Query Parameter**

```shell
GET /api/books?version=1.0
Accept: application/json
```

**3. MediaType Parameter**

```shell
GET /api/books
Accept: application/json?ver=1.0
```

**4. Path Segment**

```shell
GET /api/1.0/books
Accept: application/json
```

```shell
GET /api/v2/books
Accept: application/json
```
