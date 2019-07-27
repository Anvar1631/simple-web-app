package com.example.simpleapp;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();

        Comparator myComparator = new MyComparator();
        test.test(myComparator);
    }

    private void test(Comparator comparator) {
        List<String> names = Arrays.asList("peter", "mike", "felix");

        Collections.sort(names, comparator);
        names.sort(comparator);
    }

    static class MyComparator implements Comparator<String> {


        @Override
        public int compare(String o1, String o2) {

            return 0;
        }
    }
}
