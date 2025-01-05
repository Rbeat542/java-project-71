import hexlet.code.Compare;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompareTest {

    private static Map<String, Object> map = Map.of("setting1", "Some Value", "setting2", 200, "setting3", 0);
    private static HashMap<String, Object> joinedMap = new HashMap<String, Object>(map);
    private static HashMap<String, Object> map1 = new HashMap<String, Object>();
    private static HashMap<String, Object> map2 = new HashMap<String, Object>();
    private static Map<String, Object> mapTemp = Map.of("setting1", "Another Value", "setting4", 300, "setting3", 10);
    private static HashMap<String, Object> map3 = new HashMap<String, Object>(mapTemp);
    String format = "stylish";


    @Test
    public void testCompareWithEmptyMaps() throws Exception {
        assertThrows(Exception.class, () -> {
            Compare.compareAndFormat(joinedMap, map1, map2, format);
            throw new Exception();
        });
    }

    @Test
    public void testCompareAddedKeySet() throws Exception {
        String expected = Compare.compareAndFormat(joinedMap, map1, map3, format);
        String expected1 = Compare.getOperation("setting1", map1, map3);
        assertTrue(expected.contains("  + setting1: Another Value"));
        assertTrue("added".equals(expected1));
    }
}
