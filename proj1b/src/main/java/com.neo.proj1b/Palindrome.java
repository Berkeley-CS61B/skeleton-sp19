package com.neo.proj1b;

public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> dq = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
           dq.addLast(word.charAt(i));
        }
        return dq;
    }

}
