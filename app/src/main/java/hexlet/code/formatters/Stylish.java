package hexlet.code.formatters;

import hexlet.code.Constant;

public class Stylish {
    public static String format(String operation, String key, Object value1, Object value2) {
        String value1ToString = String.valueOf(value1);
        String value2ToString = String.valueOf(value2);
        if (Constant.NOCHANGES.equals(operation)) {
            return ("    " + key + ": " + value1ToString);
        } else if (Constant.CHANGED.equals(operation)) {
            return  ("  - " + key + ": " + value1ToString + "\n  + " + key + ": " + value2ToString);
        } else if (Constant.REMOVED.equals(operation)) {
            return ("  - " + key + ": " + value1ToString);
        } else {
            return ("  + " + key + ": " + value2ToString);
        }
    }
}
