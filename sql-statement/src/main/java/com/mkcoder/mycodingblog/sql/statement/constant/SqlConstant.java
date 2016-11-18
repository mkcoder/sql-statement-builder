package com.mkcoder.mycodingblog.sql.statement.constant;

/**
 * these are SQL statement constant.
 */
public enum SqlConstant {

    SELECT("SELECT"),
    FROM("FROM"),
    WHERE("WHERE"),
    JOIN("JOIN"),
    INNER_JOIN("INNER JOIN"),
    LEFT_JOIN("LEFT JOIN"),
    RIGHT_JOIN("RIGHT JOIN"),
    GROUP_BY("GROUP BY"),
    AS("AS"),
    SPACE(" "),
    AND("AND"),
    OR("OR"),
    LIKE("LIKE");

    String keyword;

    SqlConstant(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

    @Override
    public String toString() {
        return keyword;
    }
}
