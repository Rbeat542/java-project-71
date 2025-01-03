package hexlet.code;

import org.junit.jupiter.api.Test;
import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {

    static String testfile1 = "src/test/resources/file1.json";
    static String testfile2 = "src/test/resources/file2.json";
    static String testfile3 = "src/test/resources/file1.yml";
    static String testfile4 = "src/test/resources/file2.yml";
    static String testfile5 = "src/test/resources/file1.ml";
    static String testfile6 = "src/test/resources/file9.yml";

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
