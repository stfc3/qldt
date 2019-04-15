package org.stfc.specification;

import java.util.ArrayList;
import java.util.Arrays;

public enum SearchOperation {
    EQUALITY (":", "=")
    , NEGATION ("!", "!=")
    , GREATER_THAN (">", ">")
    , LESS_THAN ("<", "<")
    , GREATER_THAN_OR_EQUAL (">=", ">=")
    , LESS_THAN_OR_EQUAL ("<=", "<=")
    , STARTS_WITH ("*~", "LIKE")
    , ENDS_WITH ("~*", "LIKE")
    , CONTAINS ("~", "LIKE");
	
	private final String code;
    private final String operatorSql;
    
    public String getCode() {
		return code;
	}
	public String getOperatorSql() {
		return operatorSql;
	}
	private SearchOperation(String code, String operatorSql) {
		this.code = code;
		this.operatorSql = operatorSql;
	}

	public static final String[] ALL_OPERATION_SET = { ":", "!=", ">", "<", ">=", "<=", "~", "*~", "~*" };

    public static final ArrayList<String> ALL_OP = new ArrayList<>(Arrays.asList(ALL_OPERATION_SET));

    public static final String[] SIMPLE_OPERATION_SET = { ":", "!", ">", "<", "~" };

    public static final String OR_PREDICATE_FLAG = "'";

    public static final String ZERO_OR_MORE_REGEX = "*";

    public static final String OR_OPERATOR = "OR";

    public static final String AND_OPERATOR = "AND";

    public static final String LEFT_PARANTHESIS = "(";

    public static final String RIGHT_PARANTHESIS = ")";

    public static SearchOperation getSimpleOperation(final String input) {
        switch (input) {
        case ":":
            return EQUALITY;
        case "!":
            return NEGATION;
        case ">":
            return GREATER_THAN;
        case ">=":
            return GREATER_THAN_OR_EQUAL;
        case "<":
            return LESS_THAN;
        case "<=":
            return LESS_THAN_OR_EQUAL;
        case "*~":
            return STARTS_WITH;
        case "~*":
            return ENDS_WITH;
        case "~":
            return CONTAINS;
        default:
            return null;
        }
    }

    public static boolean isInEnum(final String input) {
        return ALL_OP.contains(input);
    }
}