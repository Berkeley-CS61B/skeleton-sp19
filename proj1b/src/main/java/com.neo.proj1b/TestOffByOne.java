package com.neo.proj1b;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualsChar() {
       assertTrue(offByOne.equalChars('x', 'x'));
       assertFalse(offByOne.equalChars('y', 'x'));
    }
}