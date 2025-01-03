package hexlet.code;

import org.junit.jupiter.api.Test;
import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Tests {
    static Path testfile1 = getFixturePath("file1.json");
    static Path testfile2 = getFixturePath("file2.json");
    static Path testfile3 = getFixturePath("file1.yml");
    static Path testfile4 = getFixturePath("file2.yml");
    static Path testfile5 = getFixturePath("file1.ml");
    static Path testfile6 = getFixturePath("file9.yml");

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
           .toAbsolutePath().normalize();
    }

    @Test
    public void testStylishJsonContainsString() throws Exception {
        String expected = "    chars1: [a, b, c]\n  - chars2: [d, e, f]";
        String results1 = generate(testfile1, testfile2, "stylish");
        assertTrue(results1.contains(expected));
    }

    @Test
    public void testPlainJsonContainsString() throws Exception {
        String expected = "Property 'chars2' was updated. From [complex value] to false";
        String results2 = generate(testfile1, testfile2, "plain");
        assertTrue(results2.contains(expected));
    }

    @Test
    public void testStylishYamlContainsString() throws Exception {
        String expected = "    chars1: [a, b, c]\n  - chars2: [d, e, f]";
        String results3 = generate(testfile3, testfile4, "stylish");
        assertTrue(results3.contains(expected));
    }

    @Test
    public void testPlainYamlContainsString() throws Exception {
        String expected = "Property 'chars2' was updated. From [complex value] to false";
        String results4 = generate(testfile3, testfile4, "plain");
        assertTrue(results4.contains(expected));
    }

    @Test
    public void testUknownFileTypeException() throws Exception {
        assertThrows(Exception.class, () -> {
            Differ.generate(testfile5, testfile4, "plain");
            throw new Exception("Format of file(s) is wrong or not specified");
        });
    }

    @Test
    public void testJsonJsonContainsString() throws Exception {
        String expected = "\"chars2\": \"was updated. From [d, e, f] to false\"";
        String results2 = generate(testfile1, testfile2, "json");
        assertTrue(results2.contains(expected));
    }
}
