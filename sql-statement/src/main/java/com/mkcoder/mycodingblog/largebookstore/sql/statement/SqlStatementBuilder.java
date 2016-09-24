package com.mkcoder.mycodingblog.largebookstore.sql.statement;

import com.mkcoder.mycodingblog.largebookstore.sql.statement.builder.SqlBuilder;

/**
 * This represents a com.mkcoder.mycodingblog.largebookstore.sql.statement.SqlStatementBuilder
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
public class SqlStatementBuilder implements SqlBuilder<String> {

    private final SelectStatement select;
    private final FromStatement from;
    private final WhereStatement where;
    private final JoinStatement join;
    private final GroupByStatement groupBy;

    public SqlStatementBuilder(SelectStatement select, FromStatement from, WhereStatement where, JoinStatement join, GroupByStatement groupBy) {
        this.select = select;
        this.from = from;
        this.where = where;
        this.join = join;
        this.groupBy = groupBy;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(select.build());
        sb.append(from.build());
        sb.append(where.build());
        sb.append(join.build());
        sb.append(groupBy.build());
        return sb.toString();
    }
}
