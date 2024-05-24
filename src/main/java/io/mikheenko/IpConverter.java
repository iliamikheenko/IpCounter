package io.mikheenko;

/**
 * This class provides functionality to convert an IPv4 address into an integer representation.
 */
public class IpConverter {

    /**
     * Convert IPv4 address to integer representation.
     * @param ipAddress IPv4 address string to convert.
     * @return The integer representation of the IP address.
     */
    public static int toInt(String ipAddress) {
        String[] octets = ipAddress.split("\\.");

        int ipInt = Integer.MIN_VALUE;
        ipInt += Integer.parseInt(octets[0]) << 24;
        ipInt += Integer.parseInt(octets[1]) << 16;
        ipInt += Integer.parseInt(octets[2]) << 8;
        ipInt += Integer.parseInt(octets[3]);

        return ipInt;
    }

}
