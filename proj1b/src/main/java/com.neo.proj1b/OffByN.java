package com.neo.proj1b;

public class OffByN implements CharacterComparator {
    public OffByN(int n) {
       offset = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return Math.abs(diff) == offset;
    }

    private int offset;
}
