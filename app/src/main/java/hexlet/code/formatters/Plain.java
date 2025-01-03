package hexlet.code.formatters;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Plain {

    public static String preFormat(Object value) {
        String newValue = "";
        List<String> list = List.of("HashMap", "Map","LinkedHashMap", "ArrayList", "Array", "LinkedList");
        if (Objects.equals(value, null)) {
            newValue = "null";
        } else {
            if (list.contains(value.getClass().getSimpleName())) {
                newValue = "[complex value]";
            } else if ("String".equals(value.getClass().getSimpleName())) {
                newValue = "'" + value + "'";
            } else {
                newValue = String.valueOf(value);
            }
        }
        return newValue;
    }

    public static String format(String operation, String key, Object value1, Object value2) {
        String value1ToString = preFormat(value1);
        String value2ToString = preFormat(value2);
        if (operation.equals("changed")) {
            return "Property '" + key + "' was updated. From " + value1ToString
                    + " to " + value2ToString; //to fix complex value
        } else if (operation.equals("removed")) {
            return "Property '" + key + "' was removed";
        } else if (operation.equals("added")) {
            return "Property '" + key + "' was added with value: " + value2ToString;
        } else {
            return null;
        }
    }
}
