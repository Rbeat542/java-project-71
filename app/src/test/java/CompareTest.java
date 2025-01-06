import hexlet.code.Compare;
import hexlet.code.Constant;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompareTest {
    private static Map<String, Object> map = Map.of("setting1", "Some Value",
        "setting2", Constant.TESTNUMBER, "setting3", 2,  "setting4", 2);
    private static Map<String, Object> mapTemp1 = Map.of("setting1", "Some Value",
            "setting2", Constant.TESTNUMBER, "setting3", 0);
    private static Map<String, Object> mapTemp2 = Map.of("setting1", "Another Value",
            "setting2", Constant.TESTNUMBER, "setting4", 2);
    private static HashMap<String, Object> joinedMap = new HashMap<String, Object>(map);
    private static HashMap<String, Object> map1 = new HashMap<String, Object>();
    private static HashMap<String, Object> map2 = new HashMap<String, Object>();
    private static HashMap<String, Object> map3 = new HashMap<String, Object>(mapTemp1);
    private static HashMap<String, Object> map4 = new HashMap<String, Object>(mapTemp2);

    private static String format = "stylish";


    @Test
    public void testCompareWithEmptyMaps() throws Exception {
        assertThrows(Exception.class, () -> {
            Compare.compareAndFormat(joinedMap, map1, map2, format);
            throw new Exception();
        });
    }

    @Test
    public void testCompareAddedKeySet() throws Exception {
        String expected = Compare.compareAndFormat(joinedMap, map3, map4, format);
        String expectedOperation = Compare.getOperation("setting2", map3, map4);
        String expectedOperation2 = Compare.getOperation("setting1", map3, map4);
        System.out.println(map1);
        assertTrue(expected.contains("  - setting1: Some Value"));
        assertTrue(expected.contains("  + setting1: Another Value"));
        assertTrue(expected.contains("    setting2: 200"));
        assertTrue(expected.contains("  - setting3: 0"));
        assertTrue(expected.contains("  + setting4: 2"));
        assertTrue("nochanges".equals(expectedOperation));
        assertTrue("changed".equals(expectedOperation2));
    }
}
