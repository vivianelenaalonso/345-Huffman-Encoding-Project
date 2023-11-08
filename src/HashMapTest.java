

public class HashMapTest {

    private HashMap<String, Integer> map;

    @Before
    public void setUp() {
        map = new HashMap<>();
    }

    @Test
    public void testPutAndGet() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertEquals(1, (int) map.get("one"));
        assertEquals(2, (int) map.get("two"));
        assertEquals(3, (int) map.get("three"));
    }

    @Test
    public void testSize() {
        assertEquals(0, map.size());

        map.put("one", 1);
        assertEquals(1, map.size());

        map.put("two", 2);
        assertEquals(2, map.size());

        map.put("three", 3);
        assertEquals(3, map.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(map.isEmpty());

        map.put("one", 1);
        assertFalse(map.isEmpty());
    }

    @Test
    public void testContainsKey() {
        assertFalse(map.containsKey("one"));

        map.put("one", 1);
        assertTrue(map.containsKey("one"));
    }

    @Test
    public void testResize() {
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, i);
        }

        assertEquals(100, map.size());

        for (int i = 0; i < 100; i++) {
            assertEquals(i, (int) map.get("key" + i));
        }
    }

    @Test
    public void testGetNonExistentKey() {
        assertNull(map.get("nonexistent"));
    }
}
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
