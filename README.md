# Lab 1 - Generics & Collections Framework

## Exercise 1.1
Implemented a generic WarehouseStore<T extends Product> with:
- addItem
- removeItem by ID
- findByCategory

Demonstrates type safety using Java Generics.


## Exercise 1.2
Compared performance of:
- ArrayList
- LinkedList
- HashSet
- TreeSet

Measured:
- insertion time
- lookup time
- iteration time

### Findings:
- ArrayList: fast iteration, slow insert in middle
- LinkedList: slow lookup
- HashSet: fastest lookup
- TreeSet: sorted but slower insertion

Best choice for "recently viewed products":
➡ ArrayList (fast access + preserves order)


## Exercise 1.3
Implemented custom Comparator:
- Sort by category (ASC)
- Then price (DESC)

Used Comparator.comparing().thenComparing()