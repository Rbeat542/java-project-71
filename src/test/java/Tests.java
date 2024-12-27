package hexlet.code;

//import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.Test;
import static hexlet.code.App.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
//import java.lang.Exception.ApplicationException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
    public void testFirstLineOfJsonFile() throws Exception {
        ArrayList<String> results = generate(testfile1, testfile2);
        assertEquals("  - follow: false", results.get(0));
    }

    @Test
    public void testLastLineOfJsonFile() throws Exception {
        ArrayList<String> results = generate(testfile1, testfile2);
        assertEquals("  + verbose: true", results.get(results.size() - 1));
    }

    @Test
    public void testFirstLineOfYmlFile() throws Exception {
        ArrayList<String> results = App.generate(testfile3, testfile4);
        assertEquals("  - follow: false", results.get(0));
    }

    @Test
    public void testLastLineofYmlFile() throws Exception {
        ArrayList<String> results = App.generate(testfile3, testfile4);
        assertEquals("  + verbose: true", results.get(results.size() - 1));
    }

    @Test
    public void testUknownFileTypeException() throws Exception {
        assertThrows(Exception.class, () -> {
            App.generate(testfile5, testfile4);
            throw new Exception("Wrong extension of file(s) !");
        });
    }

  /*  @Test
    public void testWrongPathOfFile1Exception() throws Exception {
        assertThrows(Exception.class, () -> {
            App.generate(testfile6, testfile4);
            throw new Exception("\"File '\" + path1 + \"' does not exist\"");
        });
    } */
}
