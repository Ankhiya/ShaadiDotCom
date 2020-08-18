package com.mobile.shaadidotcom.ankhiya.utils;

/**
 * String Utils
 */
public class StringUtils {

    private StringUtils() {
    }

    public static final String NA = "-";

    /**
     * Check for null string
     * @param obj string to be checked
     * @return if string is not null returns trimmed string, - otherwise
     */
    public static String nonNullString(String obj) {
        String requiredString = NA;
        if (obj != null && !obj.isEmpty()) {
            requiredString = obj.trim();
        }
        return requiredString;
    }
}
