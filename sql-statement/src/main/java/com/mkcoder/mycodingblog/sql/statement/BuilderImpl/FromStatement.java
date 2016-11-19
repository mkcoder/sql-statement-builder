package com.mkcoder.mycodingblog.sql.statement.builderimpl;


import com.mkcoder.mycodingblog.sql.statement.builder.SqlBuilder;
import com.mkcoder.mycodingblog.sql.statement.constant.SqlConstant;
import com.mkcoder.mycodingblog.sql.statement.exception.FromClauseException;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * builds a from statement
 */
@Named
public class FromStatement implements SqlBuilder<String> {

    private final SqlConstant FROM = SqlConstant.FROM;
    private List<String> tableNames;

    public FromStatement(String columnName, String ... tableNames) throws FromClauseException {
        this.tableNames = new ArrayList<>(tableNames.length);
        this.tableNames.add(columnName);
        this.tableNames.addAll(Arrays.asList(tableNames));
    }

    public String build() {
        // TODO: remove this constant
        return SqlConstant.SPACE + FROM.getKeyword() + SqlConstant.SPACE + tableNames.stream().collect(Collectors.joining(", "));
    }
}
