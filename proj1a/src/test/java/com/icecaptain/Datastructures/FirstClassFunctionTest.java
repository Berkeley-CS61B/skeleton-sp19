package com.icecaptain.Datastructures;

import org.junit.Assert;
import org.junit.Test;

public class FirstClassFunctionTest {

    // in python
//    def tenx(x):
//        return 10 * x
//
//    def do_twice(f, x):
//        return f(f(x))
    public interface IntUnaryFunction {
        int apply(int x);
    }
    public static class TenX implements IntUnaryFunction {
        public int apply(int x) {
            return 10 * x;
        }
    }
    public static class TwentyX implements IntUnaryFunction {
        public int apply(int num) {
            return 20 * num;
        }
    }
    public static int doTwice(IntUnaryFunction f, int x) {
       return f.apply(f.apply(x));
    }

    // showing the general comparable method in java

    public static void main(String[] args) {
        System.out.println(doTwice(new TenX(), 10));
        Assert.assertEquals((doTwice(new TenX(), 2)), 200);
    }

}
