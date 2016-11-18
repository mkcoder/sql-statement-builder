package com.mkcoder.mycodingblog.sql.statement.builderimpl;

import com.mkcoder.mycodingblog.sql.statement.builder.SqlBuilder;
import com.mkcoder.mycodingblog.sql.statement.exception.SqlStatementMissingFromClause;

import javax.inject.Named;

/**
 * This represents a SqlStatementBuilder
 * Language:
 * S -> select <column-name-or-names>
 * column-name-or-names -> [column-name] <from-clause>
 *                         | [column-name], column-name-or-names
 * from-clause ->          from <table-name-or-names>
 * table-name-or-names ->  [table-name]
 *                        | [table-name] <where>
 *                        | [table-name], <table-name-or-names>
 * ...
 *
 */
@Named
public class SqlStatementBuilder implements SqlBuilder<String> {

    SqlBuilder<String> root;

    public SqlStatementBuilder(SqlBuilder<String> builder) {
        this.root = builder;
    }

    public String build() throws SqlStatementMissingFromClause {
        return root.build() + ";";
    }
}
