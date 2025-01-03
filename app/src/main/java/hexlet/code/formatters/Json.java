package hexlet.code.formatters;

import java.lang.reflect.Array;
import java.util.Objects;

public class Json {
    public static String preFormat(Object value) {
        String newValue = "";
        if (!Objects.equals(value, null)) {
            if (value instanceof Array) {
                newValue = "[" + value + "]";
            } else if ("java.lang.String".equals(value.getClass().getName())) {
                newValue = "'" + value + "'";
            } else {
                newValue = String.valueOf(value);
            }
        } else {
            newValue = "null";
        }
        return newValue;
    }


    public static String format(String operation, String key, Object value1, Object value2) {
        String value1ToString = preFormat(value1);
        String value2ToString = preFormat(value2);
        if (operation.equals("nochanges")) {
            return ("  \"" + key + "\"" + ": " + "\"" + value1ToString + "\",");
        } else if (operation.equals("changed")) {
            return ("  \"" + key + "\"" + ": " + "\"" + "was updated. From "
                    + value1ToString + " to " + value2ToString + "\",");
        } else if (operation.equals("removed")) {
            return ("  \"" + key + "\"" + ": " + "\"was removed\",");
        } else {
            return ("  \"" + key + "\"" + ": " + "\"" + "was added with value: " + value2ToString + "\",");
        }
    }
}
