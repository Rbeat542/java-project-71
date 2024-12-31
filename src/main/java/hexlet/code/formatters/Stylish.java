package hexlet.code.formatters;

public class Stylish {
    public static String format(String operation, String key, Object value1, Object value2) {
        String value1ToString = String.valueOf(value1);
        String value2ToString = String.valueOf(value2);
        if (operation.equals("nochanges")) {
            return ("    " + key + ": " + value1ToString);
        } else if (operation.equals("changed")) {
            return  ("  - " + key + ": " + value1ToString + "\n  + " + key + ": " + value2ToString);
        } else if (operation.equals("removed")) {
            return ("  - " + key + ": " + value1ToString);
        } else {
            return ("  + " + key + ": " + value2ToString);
        }
    }
}
