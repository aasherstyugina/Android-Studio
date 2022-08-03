package com.example.todoapp;

import java.util.Comparator;

public class DealComparator implements Comparator<Deal> {
    @Override
    public int compare(Deal o1, Deal o2) {
        return Integer.compare(o2.getImportance(),o1.getImportance());
    }
}
