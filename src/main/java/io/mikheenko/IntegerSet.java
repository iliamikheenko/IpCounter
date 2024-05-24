package io.mikheenko;

import java.util.BitSet;

/**
 * This class implements a set data structure that
 * efficiently stores and counts unique integer values.
 */
public class IntegerSet {

    private final BitSet negative = new BitSet(Integer.MAX_VALUE);
    private final BitSet positive = new BitSet(Integer.MAX_VALUE);

    /**
     * Add a unique int number to the set
     * @param i - integer number
     */
    public void add(int i) {
        if (i < 0) {
            negative.set(~i);
        } else {
            positive.set(i);
        }
    }

    /**
     * Count the number of unique integer values stored in the set.
     * @return The total count of unique integer elements in the set.
     */
    public long count() {
        return (long) negative.cardinality() + positive.cardinality();
    }

}
