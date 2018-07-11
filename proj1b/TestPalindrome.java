import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator obo = new OffByOne();
    static CharacterComparator obn;

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        String[] strs = new String[]{"a", "rAcecar", "noon", "horse", "rancor", "aaaaab"};
        boolean[] expected = new boolean[] {true, true, true, false, false, false};
        boolean[] rst = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            rst[i] = palindrome.isPalindrome(strs[i]);
        }
        assertArrayEquals(expected, rst);
    }

    @Test
    public void testisPalindrome2() {
        String[] strs = new String[]{"a", "flake", "noon", "aba"};
        boolean[] expected = new boolean[] {true, true, false, false};
        boolean[] rst = new boolean[strs.length];

        for (int i = 0; i < strs.length; i++) {
            rst[i] = palindrome.isPalindrome(strs[i], obo);
        }
        assertArrayEquals(expected, rst);
    }

    @Test
    public void testisPalindrome3() {
        String[] strs = new String[]{"a", "af", "fh", "aba"};
        boolean[] expected = new boolean[] {true, true, false, false};
        boolean[] rst = new boolean[strs.length];
        obn = new OffByN(5);
        for (int i = 0; i < strs.length; i++) {
            rst[i] = palindrome.isPalindrome(strs[i], obn);
        }
        assertArrayEquals(expected, rst);
    }

}
