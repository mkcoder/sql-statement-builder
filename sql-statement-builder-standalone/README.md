> # SQL Statement
> -----
> this will be a public project, this module is solely responsible
> for generating a sql statement that can does't know about underling
> structure or component just a visitor and composite pattern'


------------
> ## General idea:
> we will have a SqlStatementVisitorHelper that will have a SqlStatementVisitor that can be injected into using IOC this SQLVisitor 
> will know about we will also have a list of SqlClauseVisitor that will injected
> in the builder of this class we will have something like this:
~~~
 // SqlStatementVisitorHelper.java
 // could also be a factory or something???
 @Inject
 SqlStatementVisitor sqlStatementVisitor;
 ...
 public SqlStatement build() {
    for ( SqlClauseVisitor clause : sqlClauses ) {
           clause.accept(sqlStatementVisitor);
    }
 }
 ~~~
Here is a idea of how the SqlStatementVisitor should look like for:
 - Book
 - Recommended Book (think excerise)
 
 ~~~
// BookSqlStatementVisitor.java
// this is a sample Book Visitor that will generate a sql statement
// we can rub off the details later
public class SqlStatementVisitor ... implements a visitor pattern and also a builder {
      // i am sure we can also refactor these out using annotations
      SqlStatement statement;
/*  on second thought we might not need these
      SelectClause select;
      FromClause from;
      WhereClause where;
*/
      // ... more clause here, we can implement a bigger one depending on the
      // sql we want to generate
      public void accept(SelectClause select) {
        // don't forget all our TableColumn extend
        // are we entrprise yet? --- might need a few more packages ... :P
        // [AbstractTableColumn](large-bookstore-app/pattern-components/iterator/database-table-column-service/src/main/java/com/mkcoder/mycodingblog/largebookstore/database/table/AbstractDatabaseTable.java)
        statement.buildColumnFromModel( select.getModel() );
      }

      public void accept(FromClause from) {
        // this could be that you want to do
        // a cross join on multuple value the from will be forced to implement a builder
        statement.append( from.build() );
      }

      public void accept(WhereClause where) {
         if ( where.isNamed ) {
            // might what to build a named sql statement
            this.isNamed = true;
            this.accept((NamedWhereClause) where)
         } else {
            // a sample spring configuration might look like this:
            /* <bean id="whereClause" class="com.awesome.sause.Criteria.BookCriteriaImpl">
                    <property name="criteria">
                        <list>
                            <value>id</value>
                            <value>title</value>
                        </list>
                    </property>
               </bean>
            */
            for ( String criteria : where.getCriteria() ) {
                statement.appendCriteria( criteria );
            }
         }
      }

      public void accept(NamedWhereClause named) {
        /**
           a sample spring configuration for a named pramater
           <bean id="whereClause" class="com.awesome.sause.Criteria.BookNamedCriteriaImpl">
               <property name="criteria">
                   <map>
                       <entry key="id" value=":id" />
                       <entry key="name" value=":name" />
                   </map>
               </property>
           </bean>
        */
        for ( Entry<String, String> criteria : where.getCriteria() ) {
            statement.appendCriteria( entry.getKey(), entry.getValue() );
        }
      }

      public SqlStatement build() {
        if ( isNamed ) {
            return statement.buildNamed();
        }
            return statement.build();
      }
}
~~~
