package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Tests {
    static Path file1 = getFixturePath("file1.json");
    static Path file2 = getFixturePath("file2.json");
    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
           .toAbsolutePath().normalize();
    }


    @Test
    public void testFirstLine() throws Exception {
        ArrayList<String> results = App.generate(file1, file2);
        assertEquals("  - follow: false", results.getFirst());
    }

    @Test
    public void testLastLine() throws Exception {
        ArrayList<String> results = App.generate(file1, file2);
        assertEquals("  + verbose: true", results.getLast());
    }
}
