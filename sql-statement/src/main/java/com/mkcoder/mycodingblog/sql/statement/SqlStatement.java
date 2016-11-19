package com.mkcoder.mycodingblog.sql.statement;

import com.mkcoder.mycodingblog.sql.statement.builderimpl.FromStatement;
import com.mkcoder.mycodingblog.sql.statement.builderimpl.SelectStatement;
import com.mkcoder.mycodingblog.sql.statement.builderimpl.WhereStatement;

/**
 * the main class that will hold the SqlStatement Builder whereClause build it do all the heavy lifting here
 */
public final class SqlStatement {

    private class SqlStatementWhere {
        SqlStatementSelectFrom selectFrom;
        WhereStatement where;

        public SqlStatementWhere(SqlStatementSelectFrom sqlStatementSelectFrom, WhereStatement where) {
            this.selectFrom = sqlStatementSelectFrom;
            this.where = where;
        }
    }

    private class SqlStatementSelectFrom {
        SelectStatement select;
        FromStatement from;

        public SqlStatementSelectFrom(SelectStatement select, FromStatement from) {
            this.select = select;
            this.from = from;
        }

        public SqlStatementWhere where(WhereStatement where) {
            return new SqlStatementWhere(this, where);
        }

    }

    public SqlStatementSelectFrom select(SelectStatement select, FromStatement from) {
        return new SqlStatementSelectFrom(select, from);
    }
}
