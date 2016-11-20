# work in a place that still use the sql query to the talk the database?
if so well i got a treat for you . . . this removes that big ol' sql clause in to a SqlStatement which is basically a tree.
Don't like how things are done, or need to additonal checks, i've made it easy to extend the SqlStatements.

# introduction 
## basic 
```
String query = new SqlStatementBuilder(
                new SelectStatement(
                    new FromStatement("dummy-table")
                ).column("*")
        ).build();
// Results in: SELECT * FROM dummy-table;       
```
## advance use of conjoinStream
```
String query =  new SqlStatementBuilder(
                    new WhereStatement(
                            new SelectStatement(
                                    new FromStatement("test", "test2 t", "fake")
                            )
                            .column("col1")
                            .column("col2")
                            .column("col3")
                            .column("col4")
                    )
                    .clause("1 > 2")
                    .and()
                    .clause("1 > 2")
                    .and()
                    .clause("123")
                    .or()
                    .like().columnName("abc").like("123%")
                    .closeStream()
                ).build();
// Results in: SELECT col1,  col2,  col3,  col4 FROM test, test2 t, fake WHERE 1 > 2 AND 2 < 1 OR col LIKE 1 AND col2 LIKE 2;        
```
# use it in a spring based project
# Spring MVC

# extending the functionality 

# adding more conditions
