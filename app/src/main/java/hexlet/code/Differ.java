package hexlet.code;

import java.nio.file.Path;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

public class Differ  {

    public static String generate(Path path1, Path path2, String format) throws Exception {
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
            String whatChanged = "";
            if (json1Map.containsKey(key) && json2Map.containsKey(key)
                    && !Objects.equals(value1, value2)) {   // to optimize
                whatChanged = "changed";
            } else if (json1Map.containsKey(key) && !json2Map.containsKey(key)) {
                whatChanged = "removed";
            } else if (json2Map.containsKey(key) && !json1Map.containsKey(key)) {
                whatChanged = "added";
            } else {
                whatChanged = "nochanges";
            }
            String newLine = Formatter.formatter(format, whatChanged, key, value1, value2);  // to optimize
            if (!Objects.equals(null, newLine)) {
                line = line + "\n" + newLine;
            }
        }
        if (format.equals("stylish")) {
            line = "\n" + "{" + line + "\n" + "}";
        }

        if (format.equals("json")) {
            line = "\n" + "{" + line + "\n" + "}";
            line = line.replace(",\n}", "\n}");
        }
        return line;
    }

    private static HashMap<String, Object> mapSort(Map<String, Object> mapUnsorted) {
        HashMap<String, Object> mapSorted = new LinkedHashMap<>();
        mapUnsorted.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> mapSorted.put(x.getKey(), x.getValue()));

        return mapSorted;
    }
}
