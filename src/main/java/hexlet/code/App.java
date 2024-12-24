package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        defaultValue = "/home/vboxuser/java-project-71/app/src/main/java/hexlet/code/file1.json",
        paramLabel = "filepath1")
    private File file1 = new File("file1,json");

    @Parameters(description = "path to second file",
        defaultValue = "/home/vboxuser/java-project-71/app/src/main/java/hexlet/code/file2.json",
        paramLabel = "filepath2")
    private File file2 = new File("file2.json");

    ArrayList<String> result = new ArrayList<>();

    public static void main(String... args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        //System.exit(0);
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

        byte[] file1Contents = Files.readAllBytes(path1);
        byte[] file2Contents = Files.readAllBytes(path2);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json1 = mapSort(mapper.readValue(file1Contents,
            new TypeReference<Map<String, Object>>() { }));
        Map<String, Object> json2 = mapSort(mapper.readValue(file2Contents,
            new TypeReference<Map<String, Object>>() { }));
        ArrayList<String> resultMapUnsorted = generateMap(json1, json2);

        var entries2 = json2.entrySet();
        for (var entry2 : entries2) {
            if (json1.containsKey(entry2.getKey()))    {
                resultMapUnsorted.add("  + " + entry2.getKey() + ": " + entry2.getValue());
            }
            resultMapUnsorted.add("  + " + entry2.getKey() + ": " + entry2.getValue());
        }

        System.out.println("{");
        for (var line :resultMapUnsorted) {
            System.out.println(line);
        }
        System.out.println("}");
        return 0;
    }

    private ArrayList<String> generateMap(Map<String, Object> json1, Map<String, Object> json2) {
        var entries = json1.entrySet();
        for (var entry : entries) {
            var generatedString = generate(entry.getKey(), entry.getValue(), json1, json2);
            result.add(generatedString);
        }
        return result;
    }

    private String generate(String key, Object value, Map<String, Object> json1, Map<String, Object> json2) {
        String newLine = "";
        if (json2.containsKey(key) && json2.get(key).equals(value))    {
            newLine = "    "    + key + ": " + value;
            json2.remove(key);
        } else if (json2.containsKey(key) && !json2.get(key).equals(value)) {
            newLine = "  - " + key + ": " + value;
        } else {
            newLine = "  - " + key + ": " + value;
        }
        return newLine;
    }

    private HashMap<String, Object> mapSort(Map<String, Object> mapUnsorted) {
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
