
import hexlet.code.Formatter;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FormatterTest {
    private static List<String> list1 =  List.of("stylish", "changed", "checked");
    private static List<String> list2 =  List.of("plain", "changed", "checked");
    private static ArrayList<String> parameters = new ArrayList<>();

    @Test
    void testFormatterStylish() throws Exception {
        parameters = new ArrayList<>(list1);

        String line = Formatter.formatter(parameters, false, true);

        assertTrue("  - checked: false\n  + checked: true".equals(line));
    }

    @Test
    void testFormatterPlain() throws Exception {
        parameters = new ArrayList<>(list2);

        String line = Formatter.formatter(parameters, false, true);

        assertTrue("Property 'checked' was updated. From false to true".equals(line));
    }
}
