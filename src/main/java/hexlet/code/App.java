package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.concurrent.Callable;

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
        String resultOfComparison = Differ.generate(path1, path2, format);
        System.out.println(resultOfComparison);
        return 0;
    }
}
