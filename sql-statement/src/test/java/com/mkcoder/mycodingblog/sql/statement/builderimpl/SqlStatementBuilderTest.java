package com.mkcoder.mycodingblog.sql.statement.builderimpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SqlStatementBuilderTest {


    SqlStatementBuilder builder;

    @Mock
    SelectStatement select;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSqlStatementGivenAnExmptySelectFromThrowError() throws Exception {
        System.out.println(new SqlStatementBuilder(
                new SelectStatement(new FromStatement("dummy-table"))
                .column("*")
        ).build());

        System.out.println(new SqlStatementBuilder(
                new WhereStatement(
                        new SelectStatement(
                                new FromStatement("test", "test2 t", "fake")
                        )
                        .column("col1")
                        .column("col2")
                        .column("col3")
                        .column("col4")
                )
                .whereCaluse("1 > 2")
                .and()
                .whereClause("1 > 2")
                .and()
                .whereClause("123")
                .or()
                .like().columnName("abc").like("123%")
                .closeStream()
        ).build());
    }
}