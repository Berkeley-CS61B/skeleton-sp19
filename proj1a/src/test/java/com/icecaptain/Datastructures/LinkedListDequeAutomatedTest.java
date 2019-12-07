package com.icecaptain.Datastructures;

import org.junit.Assert;
import org.junit.Test;


public class LinkedListDequeAutomatedTest {
    @Test
    public void testRecursiveGet() {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        for (int i = 0; i < 4; i++) {
            list.addLast(i);
        }
        Assert.assertEquals(list.getRecursive(0).intValue(), 0);
        Assert.assertEquals(list.getRecursive(1).intValue(), 1);
        Assert.assertEquals(list.get(2).intValue(), 2);
    }
}
