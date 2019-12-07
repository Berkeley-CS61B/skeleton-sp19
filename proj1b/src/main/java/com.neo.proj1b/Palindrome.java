package com.neo.proj1b;

public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> dq = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
           dq.addLast(word.charAt(i));
        }
        return dq;
    }

    public boolean isPalindromeHelper(Deque<Character> dq) {
        if (dq.size() == 1 || dq.size() == 0) {
            return true;
        } else {
            char front = dq.removeFirst();
            char back = dq.removeLast();
            if (back != front) return false;
            return isPalindromeHelper(dq);
        }
    }

    public boolean isPalindromeRec(String word) {
        Deque<Character> dq = wordToDeque(word);
        return isPalindromeHelper(dq);
    }

    public boolean isPalindrome(String word) {
        int wordLen = word.length();
        if (wordLen == 0 || wordLen == 1) return true;

        LinkedListDeque<Character> dq = (LinkedListDeque) wordToDeque(word);

        while (dq.size() > 1) {
           char front = dq.removeFirst();
           char last = dq.removeLast();
           if (front != last) return false;
        }
       return true;
    }
}
