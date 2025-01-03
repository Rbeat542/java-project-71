package hexlet.code;

import java.util.Objects;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

public class Differ  {

    public static String generate(String path1, String path2, String format) throws Exception {
        HashMap<String, Object> json1Map = Parser.parseFile(path1);
        HashMap<String, Object> json2Map = Parser.parseFile(path2);
        HashMap<String, Object> joinedUnsortedMap = new HashMap<String, Object>();
        joinedUnsortedMap.putAll(json1Map);
        joinedUnsortedMap.putAll(json2Map);
        HashMap<String, Object> joinedMap = mapSort(joinedUnsortedMap);
        String line = "";
        var keys = joinedMap.keySet();
        for (var key : keys) {
            var value1 = json1Map.get(key);
            var value2 = json2Map.get(key);
            String operation = getOperation(key, json1Map, json2Map);
            String newLine = Formatter.formatter(format, operation, key, value1, value2);  // to optimize
            if (!Objects.equals(null, newLine)) {
                line = line + "\n" + newLine;
            }
        }
        return afterFormat(line, format);
    }

    public static String getOperation(String key, HashMap map1, HashMap map2) {
        String operation = "";
        if (map1.containsKey(key) && map2.containsKey(key)
                && !Objects.equals(map1.get(key), map2.get(key))) {   // to optimize
            operation = "changed";
        } else if (map1.containsKey(key) && !map2.containsKey(key)) {
            operation = "removed";
        } else if (map2.containsKey(key) && !map1.containsKey(key)) {
            operation = "added";
        } else {
            operation = "nochanges";
        }
        return operation;
    }

    public static String afterFormat(String line, String format) {
        if (format.equals("stylish")) {
            line = "{" + line + "\n" + "}";
        }
        if (format.equals("json")) {
            line = "{" + line + "\n" + "}";
            line = line.replace(",\n}", "\n}");
        }
        if (format.equals("plain")) {
            line = line.substring(1);
        }
        return line;
    }

    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2, "stylish");
    }

    private static HashMap<String, Object> mapSort(Map<String, Object> mapUnsorted) {
        HashMap<String, Object> mapSorted = new LinkedHashMap<>();
        mapUnsorted.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> mapSorted.put(x.getKey(), x.getValue()));

        return mapSorted;
    }
}
