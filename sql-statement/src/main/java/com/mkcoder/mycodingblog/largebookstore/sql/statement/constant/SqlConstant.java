package com.mkcoder.mycodingblog.largebookstore.sql.statement.constant;

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
    SPACE(" ");

    String keyword;

    SqlConstant(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

}
