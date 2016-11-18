package com.mkcoder.mycodingblog.sql.statement.table.model;

import com.mkcoder.mycodingblog.sql.statement.builder.Builder;
import com.mkcoder.mycodingblog.sql.statement.constant.SqlConstant;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a column or expression and an alias if avialable
 */
@Named
public class TableColumn implements Builder<String> {

    @Named
    public class column implements Builder<String>{
        private String name;
        private String alias;

        protected column(String name) {
            this.name = name;
        }

        protected column(String name, String alias) {
            this.name = name;
            this.alias = SqlConstant.AS.getKeyword() + alias + SqlConstant.SPACE;
        }

        public String build() {
            return SqlConstant.SPACE + this.name + buildAlias();
        }

        private String buildAlias() {
            return  alias == null || alias.isEmpty() || alias.equals("") ?
                    "" : alias;

        }
    }

    private List<column> columns = new ArrayList<>();

    public void addColumn(String column) {
        columns.add(new column(column));
    }

    public void addColumn(String column, String alias) {
        columns.add(new column(column, alias));
    }

    public String build() {
        return columns.stream().map( column::build ).collect(Collectors.joining(", "));
    }

}
