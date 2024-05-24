package io.mikheenko;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegerSetTest {

    private IntegerSet set;

    @BeforeEach
    public void init(){
        set = new IntegerSet();
    }

    @Test
    public void addPositiveInteger() {
        set.add(5);
        assertEquals(1, set.count());
    }

    @Test
    public void addNegativeInteger() {
        set.add(-10);
        assertEquals(1, set.count());
    }

    @Test
    public void addMultipleUniqueValues() {
        set.add(3);
        set.add(-7);
        set.add(12);
        assertEquals(3, set.count());
    }

    @Test
    public void addPositiveMaxInteger() {
        set.add(Integer.MAX_VALUE);
        assertEquals(1, set.count());
    }

    @Test
    public void addPositiveMinInteger() {
        set.add(Integer.MIN_VALUE);
        assertEquals(1, set.count());
    }

}
