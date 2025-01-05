package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

public class Differ  {
    public static String generate(String path1, String path2, String format) throws Exception {
        HashMap<String, Object> json1Map = Parser.parseFile(path1);
        HashMap<String, Object> json2Map = Parser.parseFile(path2);
        HashMap<String, Object> joinedMap = collectAndSort(json1Map, json2Map);
        String line = Compare.compareAndFormat(joinedMap, json1Map, json2Map, format);
        return line;
    }

    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2, Constant.FORMATSTYLISH);
    }

    private static HashMap<String, Object> collectAndSort(HashMap<String, Object> map1, HashMap<String, Object> map2) {
        HashMap<String, Object> joinedMap = new HashMap<String, Object>();
        joinedMap.putAll(map1);
        joinedMap.putAll(map2);
        return mapSort(joinedMap);
    }

    private static HashMap<String, Object> mapSort(Map<String, Object> mapUnsorted) {
        HashMap<String, Object> mapSorted = new LinkedHashMap<>();
        mapUnsorted.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> mapSorted.put(x.getKey(), x.getValue()));

        return mapSorted;
    }
}
