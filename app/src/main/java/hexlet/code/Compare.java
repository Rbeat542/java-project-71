package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Compare {
    public static String compareAndFormat(HashMap<String, Object> joinedMap, HashMap<String, Object> map1,
                                            HashMap<String, Object> map2, String format) throws Exception {
        String line = "";
        var keys = joinedMap.keySet();
        for (var key : keys) {
            var value1 = map1.get(key);
            var value2 = map2.get(key);
            String operation = getOperation(key, map1, map2);
            var params = List.of(format, operation, key);
            var parameters = new ArrayList<>(params);
            String newLine = Formatter.formatter(parameters, value1, value2);
            if (!Objects.equals(null, newLine)) {
                line = line + "\n" + newLine;
            }
        }
        return finalFormatOfString(line, format);
    }

    public static String getOperation(String key, HashMap map1, HashMap map2) {
        String operation = "";
        if (map1.containsKey(key) && map2.containsKey(key)
                && !Objects.equals(map1.get(key), map2.get(key))) {
            operation = Constant.CHANGED;
        } else if (map1.containsKey(key) && !map2.containsKey(key)) {
            operation = Constant.REMOVED;
        } else if (map2.containsKey(key) && !map1.containsKey(key)) {
            operation = Constant.ADDED;
        } else {
            operation = Constant.NOCHANGES;
        }
        return operation;
    }

    public static String finalFormatOfString(String line, String format) {
        if (format.equals(Constant.FORMATSTYLISH)) {
            line = "{" + line + "\n" + "}";
        }
        if (format.equals(Constant.FORMATJSON)) {
            line = "{" + line + "\n" + "}";
            line = line.replace(",\n}", "\n}");
        }
        if (format.equals(Constant.FORMATPLAIN)) {
            line = line.substring(1);
        }
        return line;
    }

}
