package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    @Option(names = {"-f", "--format="}, description = "output format [default: stylish]", paramLabel = "format")
    private static String format = "stylish";

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Display version information and exit.")
    private boolean versionInfoRequested;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean usageHelpRequested;

    @Parameters(description = "path to first file",
            defaultValue = "src/test/resources/file1.json",
            paramLabel = "filepath1")
    private static File file1 = new File("file1.json");

    @Parameters(description = "path to second file",
            defaultValue = "src/test/resources/file2.json",
            paramLabel = "filepath2")
    private static File file2 = new File("file2.json");

    @Override
    public Integer call() throws Exception {
        Call callMethod = new Call(file1, file2, format);
        return callMethod.execute();
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
