package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

public class Call implements Callable<Integer> {
    private final File file1;
    private final File file2;
    private final String format;

    public Call(File file1, File file2, String format) {
        this.file1 = file1;
        this.file2 = file2;
        this.format = format;
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
        String resultOfComparison = Differ.generate(path1.toString(), path2.toString(), format);
        System.out.println(resultOfComparison);
        return 0;
    }
}
