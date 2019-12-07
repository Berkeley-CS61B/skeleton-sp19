package com.neo.proj1b;

import edu.princeton.cs.algs4.In;

public class Palindrome {

    public static Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> dq = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
           dq.addLast(word.charAt(i));
        }
        return dq;
    }

    private boolean isPalindromeHelper(Deque<Character> dq) {
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

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        int wordLen = word.length();
        if (wordLen == 0 || wordLen == 1) return true;

        LinkedListDeque<Character> dq = (LinkedListDeque) wordToDeque(word);

        while (dq.size() > 1) {
            char front = dq.removeFirst();
            char last = dq.removeLast();
            if (!cc.equalChars(front, last)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String file = "words.txt";
        In in = new In(file);
        int count = 0;
        int numWordsToCheck = 10000;

        while (in.hasNextLine()) {
            String word = in.readLine();
            if (word.length() != 4) continue;
            if (isPalindrome(word, new OffByOne())) {
                System.out.println(word);
            }
            count++;
            if (count == numWordsToCheck) break;
        }
    }
}
