package io.mikheenko;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class IpCounterApp {

    /**
     * Application entry point.
     * This method reads a text file containing IPv4 addresses (one per line),
     * and prints to the console the number of unique IP addresses encountered.
     *
     * @param args The command-line arguments. The only argument should be the path
     *             to the text file containing the IP addresses.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java -jar <path/to/ip_counter.jar> <path/to/ip_addresses>");
            System.exit(1);
        }

        var pathToFile = Path.of(args[0]);
        var uniqueIps = new IntegerSet();

        try (var ips = Files.lines(pathToFile, StandardCharsets.US_ASCII)) {
            ips.forEach(it -> uniqueIps.add(IpConverter.toInt(it)));
            System.out.println("Number of unique IPv4 addresses in the file: " + uniqueIps.count());
        } catch (IOException e) {
            System.err.println("Error during processing file: " + pathToFile);
        }
    }

}
