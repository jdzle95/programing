import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        char[] first = new char[]{'a', 'b', 'c', 'e', 'f', 'z'};
        char[] second = new char[]{'b', 'a', 'd', 'f', 'p', 'q'};
        boolean[] expected = new boolean[]{true, true, true, true, false, false};
        boolean[] actual = new boolean[first.length];
        for (int i = 0; i < first.length; i++) {
            actual[i] = offByOne.equalChars(first[i], second[i]);
        }
        assertArrayEquals(actual, expected);
    }
}
