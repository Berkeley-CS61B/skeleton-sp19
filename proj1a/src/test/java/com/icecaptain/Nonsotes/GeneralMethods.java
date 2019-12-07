package com.icecaptain.Nonsotes;

import edu.princeton.cs.algs4.In;

import java.util.*;
import java.util.stream.Collectors;

public class GeneralMethods {

    public static class Node {
        private int value;
        private Node next;
    }

    private Node head;
    private Node tail;

    public interface OurComparable {
        public int compareTo(Object o);
    }

    public static OurComparable max(OurComparable[] items) {
        int maxDex = 0;
        for (int i = 0; i < items.length; i++) {
            int cmp = items[i].compareTo(items[maxDex]);
            if (cmp > 0) {
               maxDex = i;
            }
        }
        return items[maxDex];
    }

    public static void main(String[] args) {
//        String name = "a";
//        String bname = "b";
//        System.out.println(name.compareTo(bname));
//
        Map<String, Integer> wordCount = new HashMap<String, Integer>();
        Set<String> set = new HashSet<String>();

        In in = new In("mit");
        while (in.hasNextLine()) {
           String line = in.readLine();
           List<String> words = Arrays.asList(line.split(" "));
           for (String word : words) {
               // get the word count
             if (wordCount.containsKey(word)) {
                 wordCount.put(word, (Integer) wordCount.get(word) + 1);
             } else {
                 wordCount.put(word, 1);
             }

             // get the unique words
               set.add(word);

           }


          //  System.out.println(line);
            System.out.println("unique words : " + set.size());

           // word count
//            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
//
//            }

            wordCount.forEach((k, v) -> {
                System.out.println(k + " : " + v);
                System.out.println("this works");
            });

            set.forEach(v -> {
                System.out.println(v);
            });

            words.forEach(word -> {

            });

        }
    }
}
