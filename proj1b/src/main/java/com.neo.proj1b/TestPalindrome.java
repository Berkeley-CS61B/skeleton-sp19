package com.neo.proj1b;

import com.neo.proj1b.Deque;
import com.neo.proj1b.Palindrome;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

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
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("door"));
    }

    @Test
    public void testIsPalindromeComparator() {
        assertTrue(palindrome.isPalindrome("ab", new OffByOne()));
        assertTrue(palindrome.isPalindrome("cdcd", new OffByOne()));
    }

    @Test
    public void testIsPalindromeComparatorOffset() {
        OffByN offby5 = new OffByN(5);
        assertTrue(palindrome.isPalindrome("af", offby5));
        assertFalse(palindrome.isPalindrome("fh", offby5));
    }
}