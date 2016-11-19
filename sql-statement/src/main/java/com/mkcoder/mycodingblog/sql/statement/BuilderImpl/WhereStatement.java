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
    private JoinStatement join;
    private SelectStatement select;

    public WhereStatement(SelectStatement select) {
        this.select = select;
    }

    public WhereStatement(JoinStatement join) {
        this.join = join;
    }

    public WhereStatement whereCaluse(String clause) throws SqlConditionsFailedException {
        SqlConditions.checkstatement(clause,
                SqlConditions.Condition.NON_EMPTY_STRING);
        sb.append(clause);
        return this;
    }

    public WhereConjoiningStatement conjoinStream() {
        return new WhereConjoiningStatement(this);
    }

    public String build() {
        return (select == null) ?
                join.build() :
                select.build() +
                SqlConstant.WHERE + SqlConstant.SPACE +
                sb.toString();
    }

    public final class WhereConjoiningStatement {

        private final WhereStatement where;

        private WhereConjoiningStatement(WhereStatement whereStatement) {
            this.where = whereStatement;
        }

        public WhereConjoiningStatement or() throws SqlConditionsFailedException {
            SqlConditions.checkstatement(sb.toString(), SqlConditions.Condition.ENDS_WITH_AND_OR);

            sb.append(SqlConstant.SPACE + "" + SqlConstant.OR + SqlConstant.SPACE );
            return this;
        }

        public WhereConjoiningStatement and() throws SqlConditionsFailedException {
            SqlConditions.checkstatement(sb.toString(), SqlConditions.Condition.ENDS_WITH_AND_OR);

            sb.append(SqlConstant.SPACE + "" + SqlConstant.AND + SqlConstant.SPACE );
            return this;
        }

        public WhereConjoiningStatement and(String clause) throws SqlConditionsFailedException {
            SqlConditions.checkstatement(clause,
                    SqlConditions.Condition.NON_EMPTY_STRING);
            SqlConditions.checkstatement(sb.toString(), SqlConditions.Condition.ENDS_WITH_AND_OR);

            sb.append(clause).append(SqlConstant.SPACE).append(SqlConstant.AND).append(SqlConstant.SPACE);
            return this;
        }

        public WhereConjoiningStatement or(String clause) throws SqlConditionsFailedException {
            SqlConditions.checkstatement(clause,
                    SqlConditions.Condition.NON_EMPTY_STRING);
            SqlConditions.checkstatement(sb.toString(), SqlConditions.Condition.ENDS_WITH_AND_OR);

            sb.append(clause).append(SqlConstant.SPACE).append(SqlConstant.OR).append(SqlConstant.SPACE);
            return this;
        }

        public WhereLikeStatement like() throws SqlConditionsFailedException {
            SqlConditions.checkstatement(sb.toString().trim(), SqlConditions.Condition.NOT_ENDS_WITH_AND_OR);

            return new WhereLikeStatement(where);
        }

        public WhereStatement closeStream() {
            return this.where;
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
            sb.append(s).append(SqlConstant.SPACE).append(SqlConstant.LIKE).append(SqlConstant.SPACE);
            return new RightHandSide(whereConjoin);
        }

        public class RightHandSide  {
            private final WhereStatement whereConjoin;

            public RightHandSide(WhereStatement whereConjoin) {
                this.whereConjoin = whereConjoin;
            }

            public WhereConjoiningStatement paramater(String paramater) throws SqlConditionsFailedException {
                SqlConditions.checkstatement(paramater,
                        SqlConditions.Condition.NON_EMPTY_STRING);
                sb.append(paramater).append(SqlConstant.SPACE);
                return new WhereConjoiningStatement(this.whereConjoin);
            }
        }
    }
}
