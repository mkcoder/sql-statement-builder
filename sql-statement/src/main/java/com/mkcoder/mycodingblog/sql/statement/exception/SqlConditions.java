package com.mkcoder.mycodingblog.sql.statement.exception;

import com.mkcoder.mycodingblog.sql.statement.constant.SqlConstant;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by muhammadk on 18-Nov-16.
 */
public class SqlConditions {

    public enum Condition {
        NON_EMPTY_STRING("String is empty!", (s) -> s.equals("")),
        ENDS_WITH_AND_OR("You called a top level statement!",
                s -> s.endsWith(SqlConstant.AND.getKeyword()) || s.endsWith(SqlConstant.OR.getKeyword()));


        private final String message;
        private Predicate<String> predicate;


        Condition(String message, Predicate<String> predicate) {
            this.message = message;
            this.predicate = predicate;
        }

        @Override
        public String toString() {
            return name() + "["+message+"]";
        }
    }

    public static void checkstatement(String clause, Condition ... conditions ) throws SqlConditionsFailedException {
        List<Condition> conditionStream = checker(clause, conditions);
        if ( conditionStream.size() > 0 ) {
            throw new SqlConditionsFailedException(
                    conditionStream.stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(" "))
            );
        }
    }

    private static List<Condition> checker(String clause, Condition[] conditions) {
        return Arrays
                .asList(conditions).stream()
                .filter(c -> c.predicate.test(clause))
                .collect(Collectors.toList());
    }


}
