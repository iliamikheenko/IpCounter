package io.mikheenko;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IpConverterTest {

    @Test
    public void convertIpAddress() {
        int actual = IpConverter.toInt("10.0.0.1");
        int expected = -1979711487;
        assertEquals(expected, actual);
    }

    @Test
    public void convertAnotherIpAddress() {
        int actual = IpConverter.toInt("192.168.1.0");
        int expected = 1084752128;
        assertEquals(expected, actual);
    }

    @Test
    public void convertMaxIpAddress() {
        int actual = IpConverter.toInt("255.255.255.255");
        int expected = Integer.MAX_VALUE;
        assertEquals(expected, actual);
    }

    @Test
    public void convertMinIpAddress() {
        int actual = IpConverter.toInt("0.0.0.0");
        int expected = Integer.MIN_VALUE;
        assertEquals(expected, actual);
    }

}
