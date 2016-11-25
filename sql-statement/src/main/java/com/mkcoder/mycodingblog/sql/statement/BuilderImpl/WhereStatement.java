package com.mkcoder.mycodingblog.sql.statement.builderimpl;


import com.mkcoder.mycodingblog.sql.statement.builder.SqlBuilder;
import com.mkcoder.mycodingblog.sql.statement.constant.SqlConstant;
import com.mkcoder.mycodingblog.sql.statement.exception.SqlConditions;
import com.mkcoder.mycodingblog.sql.statement.exception.SqlConditionsFailedException;

import javax.inject.Named;

/**
 * builds a where statement
 */
@Named
public class WhereStatement implements SqlBuilder<String> {
    private final StringBuilder sb = new StringBuilder();
    private String endsWith;
    private JoinStatement join;
    private SelectStatement select;

    public WhereStatement(SelectStatement select) {
        this.select = select;
    }

    public WhereStatement(JoinStatement join) {
        this.join = join;
    }

    public AndOr whereCaluse(String clause) throws SqlConditionsFailedException {
        SqlConditions.checkstatement(clause,
                SqlConditions.Condition.NON_EMPTY_STRING);
        sb.append(clause);
        return new AndOr(this);
    }

    public String build() {
        return ((select == null) ?
                join.build() :
                select.build()) +
                SqlConstant.SPACE + SqlConstant.WHERE + SqlConstant.SPACE +
                sb.toString();
    }

    public final class AndOr {
        private final WhereStatement where;

        public AndOr(WhereStatement where) {
            this.where = where;
        }

        public WhereConjoiningStatement and() throws SqlConditionsFailedException {
            SqlConditions.checkstatement(sb.toString(), SqlConditions.Condition.ENDS_WITH_AND_OR);
            sb.append(SqlConstant.SPACE + "" + SqlConstant.AND + SqlConstant.SPACE );
            return new WhereConjoiningStatement(this.where);
        }

        public WhereConjoiningStatement or() {
            sb.append(SqlConstant.SPACE).append(SqlConstant.OR);
            return new WhereConjoiningStatement(this.where);
        }

        public WhereStatement closeStream() {
            return this.where;
        }
    }

    public final class WhereConjoiningStatement {

        private final WhereStatement where;

        private WhereConjoiningStatement(WhereStatement whereStatement) {
            this.where = whereStatement;
        }

        public AndOr whereClause(String clause) throws SqlConditionsFailedException {
            SqlConditions.checkstatement(clause,
                    SqlConditions.Condition.NON_EMPTY_STRING);
            sb.append(SqlConstant.SPACE).append(clause).append(SqlConstant.SPACE);
            return new AndOr(this.where);
        }

        public WhereLikeStatement like() throws SqlConditionsFailedException {
            SqlConditions.checkstatement(sb.toString().trim(), SqlConditions.Condition.NOT_ENDS_WITH_AND_OR);
            return new WhereLikeStatement(where);
        }
    }

    public final class WhereLikeStatement {
        private final WhereStatement whereConjoin;

        private WhereLikeStatement(WhereStatement whereConjoiningStatement) throws SqlConditionsFailedException {
            this.whereConjoin = whereConjoiningStatement;
        }


        public RightHandSide columnName(String s) throws SqlConditionsFailedException {
            SqlConditions.checkstatement(s,
                    SqlConditions.Condition.NON_EMPTY_STRING);
            sb.append(SqlConstant.SPACE).append(s).append(SqlConstant.SPACE).append(SqlConstant.LIKE);
            return new RightHandSide(whereConjoin);
        }
    }

    public class RightHandSide  {
        private final WhereStatement where;

        public RightHandSide(WhereStatement where) {
            this.where = where;
        }

        public AndOr like(String paramater) throws SqlConditionsFailedException {
            SqlConditions.checkstatement(paramater,
                    SqlConditions.Condition.NON_EMPTY_STRING);
            sb.append(SqlConstant.SPACE).append(paramater);
            return new AndOr(this.where);
        }
    }
}
