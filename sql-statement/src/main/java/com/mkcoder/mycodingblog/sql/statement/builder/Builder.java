package com.mkcoder.mycodingblog.sql.statement.builder;

import com.mkcoder.mycodingblog.sql.statement.exception.SqlStatementMissingFromClause;

/**
 * A builder interface
 */
public interface Builder<T> {

    T build() throws SqlStatementMissingFromClause;

}
