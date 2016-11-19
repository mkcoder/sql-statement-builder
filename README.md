# work in a place that still use the sql query to the talk the database?
if so well i got a treat for you . . . this removes that big ol' sql clause in to a SqlStatement which is basically a tree.
Don't like how things are done, or need to additonal checks, i've made it easy to extend the SqlStatements.

# introduction 
## basic 
```
String query = new SqlStatementBuilder(
                new SelectStatement(
                    new FromStatement("<table-name> <alias>")
                ).column("*")
        ).build()
```
## advance use of conjoinStream
```
String query = new SqlStatementBuilder(
                new WhereStatement(
                        new SelectStatement(
                                new FromStatement("test", "test2 t", "fake")
                        )
                        .column("col1")
                        .column("col2")
                        .column("col3")
                        .column("col4")
                )
                .conjoinStream()
                .and("1 > 2")
                .or("2 < 1")
                .like().columnName("col").paramater("1")
                .and()
                .like().columnName("col2").paramater("2")
                .closeStream()
        ).build()
```
# use it in a spring based project
# Spring MVC

# extending the functionality 

# adding more conditions
