package com.icecaptain.Nonsotes;

import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    private String name;
    private int size;

    public Dog(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }

    @Override
    public int compareTo(Dog dog) {
        return this.size - dog.size;
    }

    public static class NameComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    public static Comparator<Dog> getNameComaparator() {
        return new NameComparator();
    }
}
