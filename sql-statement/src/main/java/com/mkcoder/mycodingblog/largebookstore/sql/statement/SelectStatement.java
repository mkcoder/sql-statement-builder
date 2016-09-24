package com.mkcoder.mycodingblog.largebookstore.sql.statement;

import com.mkcoder.mycodingblog.largebookstore.builder.service.Builder;
import com.mkcoder.mycodingblog.largebookstore.sql.statement.constant.SqlConstant;

/**
 * this class describes a select statement which usually has SELECT followed by one or more column-names or with a star
 */
public class SelectStatement implements Builder<String> {

    private static final String SELECT = SqlConstant.SELECT.getKeyword();
//    private ColumnHeader tableColumn;

    public String build() {
        return null;
    }
}
