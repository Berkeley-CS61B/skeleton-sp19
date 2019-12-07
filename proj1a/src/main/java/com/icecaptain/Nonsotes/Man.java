package com.icecaptain.Nonsotes;

import java.util.Iterator;

public class Man implements Comparable<Man>, Iterable<Man> {
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int age;
    private String name;

    public Man(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Man nonso) {
        return age - nonso.age;
    }

    @Override
    public Iterator<Man> iterator() {
        return null;
    }

    // delete
    public static void main(String[] args) {
       Man ali = new Man(10, "evi");
       ali.getAge();
       try {
           ali.getName();
           throw new RuntimeException();
       } catch (Exception e) {
           System.out.println("the exception was caught");
       }
        System.out.println("life goes on");
    }
}
