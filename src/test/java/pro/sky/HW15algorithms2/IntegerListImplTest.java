package pro.sky.HW15algorithms2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {
    private final IntegerListImpl integerUnderTest = new IntegerListImpl();

    @Test
    void contains() {
        int item = integerUnderTest.add(4);
        assertTrue(integerUnderTest.contains(item));
    }
}