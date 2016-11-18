package com.mkcoder.mycodingblog.sql.statement.builderimpl;

import com.mkcoder.mycodingblog.sql.statement.builder.SqlBuilder;

import javax.inject.Named;

/**
 * create a join statement
 * <h1>If this join statement is complex consider using a stored proc and executing that stored proc.</h1>
 */
@Named
public class JoinStatement implements SqlBuilder<String> {

    public JoinStatement(SelectStatement select) {
    }

    public String build() {
        return null;
    }
}
