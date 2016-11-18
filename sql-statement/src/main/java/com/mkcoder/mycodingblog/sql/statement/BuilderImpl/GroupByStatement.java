package com.mkcoder.mycodingblog.sql.statement.builderimpl;


import com.mkcoder.mycodingblog.sql.statement.builder.SqlBuilder;

import javax.inject.Named;

/**
 * returns a group by statement
 * GROUP BY <column-name-or-names> <asc-desc>
 */
@Named
public class GroupByStatement implements SqlBuilder<String> {

    public String build() {
        return null;
    }
}
