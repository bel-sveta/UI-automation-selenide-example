package org.svetlana.utils;

import java.util.List;

public class Utils {

    public static <T extends Comparable<? super T>> boolean isSortedAscending(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                System.out.println("Ascending Sorting fails on the item # " + i);
                return false;
            }
        }
        return true;
    }

    public static <T extends Comparable<? super T>> boolean isSortedDescending(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) < 0) {
                System.out.println("Descending Sorting fails on the item # " + i);
                return false;
            }
        }
        return true;
    }
}
