package hexlet.code.formatters;

public class Stylish {
    public static String format(String operation, String key, Object value1, Object value2) {
        String value1Preformatted = String.valueOf(value1);
        String value2Preformatted = String.valueOf(value1);
        if (operation.equals("nochanges")) {
            return ("    " + key + ": " + value1Preformatted);
        } else if (operation.equals("changed")) {
            return  ("  - " + key + ": " + value1Preformatted + "\n  + " + key + ": " + value2Preformatted);
        } else if (operation.equals("removed")) {
            return ("  - " + key + ": " + value1Preformatted);
        } else {
            return ("  + " + key + ": " + value2Preformatted);
        }
    }
}
