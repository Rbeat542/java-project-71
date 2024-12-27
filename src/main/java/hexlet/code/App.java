package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
    description = "Compares two configuration files and shows a difference.")

class App implements Callable<Integer> {
    @Option(names = {"-f", "--format="}, description = "output format [default: stylish]", paramLabel = "format")
    private String format = "stylish";

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Display version information and exit.")
    boolean versionInfoRequested;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean usageHelpRequested;

    @Parameters(description = "path to first file",
        defaultValue = "src/test/resources/file1.json",
        paramLabel = "filepath1")
    static File file1 = new File("file1.json");

    @Parameters(description = "path to second file",
        defaultValue = "src/test/resources/file2.json",
        paramLabel = "filepath2")
    static File file2 = new File("file2.json");

    public static void main(String... args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(0);
    }

    @Override
    public Integer call() throws Exception {

        Path path1 = Paths.get(file1.toString()).toAbsolutePath().normalize();
        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' does not exist");
        }

        Path path2 = Paths.get(file2.toString()).toAbsolutePath().normalize();
        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist");
        }

        ArrayList<String> resultMapUnsorted = generate(path1, path2);
        System.out.println("{");
        for (var line :resultMapUnsorted) {
            System.out.println(line);
        }
        System.out.println("}");
        return 0;
    }

    public static ArrayList<String> generate(Path path1, Path path2) throws Exception {
        HashMap<String, Object> json1Map = Parser.parseFile(path1);
        HashMap<String, Object> json2Map = Parser.parseFile(path2);
        HashMap<String, Object> joinedUnsortedMap = new HashMap<String, Object>();
        joinedUnsortedMap.putAll(json1Map);
        joinedUnsortedMap.putAll(json2Map);
        HashMap<String, Object> joinedMap = mapSort(joinedUnsortedMap);
        ArrayList<String> result = new ArrayList<>();
        var keys = joinedMap.keySet();
        for (var key : keys) {
            Object valueInMap1 = json1Map.get(key);
            Object valueInMap2 = json2Map.get(key);
            if (json1Map.containsKey(key) && json2Map.containsKey(key)
                    && valueInMap2.equals(valueInMap1)) {
                result.add("    " + key + ": " + json1Map.get(key));
            } else if (json1Map.containsKey(key) && json2Map.containsKey(key) && !valueInMap2.equals(valueInMap1)) {
                result.add("  - " + key + ": " + valueInMap1);
                result.add("  + " + key + ": " + valueInMap2);
            } else if (json1Map.containsKey(key) && !json2Map.containsKey(key)) {
                result.add("  - " + key + ": " + valueInMap1);
            } else {
                result.add("  + " + key + ": " + valueInMap2);
            }
        }
        return result;
    }

    private static HashMap<String, Object> mapSort(Map<String, Object> mapUnsorted) {
        HashMap<String, Object> mapSorted = mapUnsorted.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return mapSorted;
    }
}
