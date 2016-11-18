package com.mkcoder.mycodingblog.sql.statement.builderimpl;

import com.mkcoder.mycodingblog.sql.statement.builder.SqlBuilder;
import com.mkcoder.mycodingblog.sql.statement.constant.SqlConstant;
import com.mkcoder.mycodingblog.sql.statement.table.model.TableColumn;

import javax.inject.Named;
import java.util.Map;
import java.util.Objects;

/**
 * this class describes a select statement which usually has SELECT followed by one or more tableNamesOrAlias-names or with a star
 */
@Named
public class SelectStatement implements SqlBuilder<String> {

    private static final SqlConstant SELECT = SqlConstant.SELECT;
    private final FromStatement from;
    private TableColumn tableNamesOrAlias;

    public SelectStatement(FromStatement fromStatement) {
        this.from = Objects.requireNonNull(fromStatement, "From statement must be provided to a select statement");
        tableNamesOrAlias = new TableColumn();
    }

    public SelectStatement column(String name) {
        tableNamesOrAlias.addColumn(name);
        return this;
    }

    public SelectStatement column(String name, String alias) {
        tableNamesOrAlias.addColumn(name, alias);
        return this;
    }

    public SelectStatement column(Map<String, String> columnName)  {
        columnName.entrySet().stream().forEach(entry ->
                tableNamesOrAlias.addColumn(entry.getKey(), entry.getValue()));
        return this;
    }

    public String build() {
        return SELECT + tableNamesOrAlias.build() + from.build();
    }

    public static class Alias {
        public static String as(String alias) {
            return alias;
        }
    }
}
