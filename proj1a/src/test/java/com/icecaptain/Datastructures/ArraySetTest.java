package com.icecaptain.Datastructures;

import junit.framework.TestSuite;
import org.junit.Test;
import org.junit.Assert;

public class ArraySetTest {
    @Test
    public void testToString() {
        ArraySet<Integer> arrset = new ArraySet<>();
        for (int i = 0; i < 10; i++) {
            arrset.add(i);
        }

        ArraySet<Integer> arrset2 = new ArraySet<>();
        for (int i = 0; i < 3; i++) {
            arrset2.add(i);
        }

        Assert.assertEquals("{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }", arrset.toString());
        Assert.assertEquals("{ 0, 1, 2 }", arrset2.toString());
    }
}
