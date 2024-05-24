# About

The `IpCounter` project is a solution for counting unique IPv4 addresses in a large file.

## Table of Contents
* [Task Description](#task-description)
* [Building and Running](#building-and-running)
* [Solution Description](#solution-description)
* [Converting solution](#converting-solution)
  * [Not-optimized Approach](#not-optimized-approach)
  * [Optimized Approach](#optimized-approach)
  * [Why the toInt method is static](#why-the-toint-method-is-static)
* [Container solution](#container-solution)
  * [Storage for Positive and Negative integers](#storage-for-positive-and-negative-integers)
  * [Adding an integer](#adding-an-integer)
  * [Counting the number of unique values](#counting-the-number-of-unique-values)

## Task Description

A detailed description of the task is available in the [original GitHub repository](https://github.com/Ecwid/new-job/blob/master/IP-Addr-Counter.md).

The main requirements are:
<ul>
 <li>The provided code must use only the features of the standard Java library.</li>
 <li>The code must be written in Java (version 17 and above)</li>
 <li>The solution must include a functional `main()` method to demonstrate its operation.</li>
</ul>

## Building and Running
You can run the `IpCounter` program with your own file. The file should contain valid IPv4 addresses written 
line by line without empty lines.

The test file with IP addresses is located in the `src/test/java/resources/ip_addresses.txt` folder.

`````
   mvn package
`````   
`````   
   java -jar target/ip_counter.jar src/test/java/resources/ip_addresses.txt 
`````
Please note that you only need to provide one argument: a link to the file.

## Solution Description

The assignment requires writing a program that will process a large file with IP addresses using as little space and 
time as possible.

A “naive” algorithm for solving the problem: read line by line, put lines into a `HashSet`. 
There are two problems with this solution — strings and `HashSet` take up a lot of storage space. This approach also
involves storing the actual unique IP addresses themselves, which contributes to the memory usage.


The goal is to develop an algorithm where IP addresses are not stored as strings, so each IP will take up less space.
Additionally, the data about unique IPs will be stored in a data structure that is more memory-efficient.

## Converting solution

IPv4 addresses are 32-bit numbers, meaning there are 2^32 possible IP addresses. These can be converted into integers 
for convenient manipulation. You may learn more about IPv4 from [this article](https://en.wikipedia.org/wiki/IPv4).

### Not-optimized Approach

The first implementation of 'toInt' method looked like this:

`````
public static int toInt(String ipAddress) {
    String[] octets = ipAddress.split("\\.");
    
    int ipInt = Integer.MIN_VALUE;;
    for(int i = 0; i < 4; i++) {
        int octet = Integer.parseInt(octets[i]);
        ipInt = (int)(result + octet * Math.pow(256, (3 - i)));
    }
    return ipInt;
}
`````
It's important to note that the initial value for the result is set to Integer.MIN_VALUE. This is because the algorithm 
needs to convert the IP address into a positive numeric representation, which subsequently needs to be compatible with the int data type.

This implementation while correct, is slow due to invoking the `Math.pow()` method three times for every IP address.

### Optimized Approach

An optimized version that uses bitwise operations for faster execution is shown below:

`````
public static int toInt(String ipAddress) {
    String[] octets = ipAddress.split("\\.");

    int ipInt = Integer.MIN_VALUE;
    ipInt += Integer.parseInt(octets[0]) << 24;
    ipInt += Integer.parseInt(octets[1]) << 16;
    ipInt += Integer.parseInt(octets[2]) << 8;
    ipInt += Integer.parseInt(octets[3]);

    return ipInt;
}
`````

Local tests showed that the optimized approach reduced conversion time by about a third.

### Why the `toInt` method is static

The `toInt` method is static because it's a utility function that converts an IP address in string format to an integer.
This operation does not depend on any object state and does not modify any object state, hence it can be declared
static.

## Container solution

The objective of the task is to count unique IP addresses, rather than storing the IP addresses themselves. The "BitSet"
was chosen as the storage container for this purpose. A BitSet is efficient in storing a set of unique numbers. You can 
read more about Bitset [here](https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/util/BitSet.html) and [here](https://www.baeldung.com/java-bitset).
The structure of this type of container allows you to significantly reduce the amount of memory spent on storing 
information about unique IP addresses.

### Storage for Positive and Negative integers

Since a BitSet can only hold 2^31 positive integers, we maintain two BitSets: one for positive and another for negative 
numbers. 

It's possible to create Bitset without maximum capacity specified, since it can automatically grow this array if needed.
But in this code it is specified, so it can prevent or decrease the unnecessary copying of array elements while growing 
it.
`````
    private final BitSet positive = new BitSet(Integer.MAX_VALUE);
    private final BitSet negative = new BitSet(Integer.MAX_VALUE);
`````

### Adding an integer

The `add()` method is used to add an integer to the io.mikheenko.IntegerSet. It handles negative numbers by converting them 
to positive using the bitwise 'NOT' operation.
`````
    public void add(int i) {
        if (i < 0) {
            negative.set(~i);
        } else {
            positive.set(i);
        }
    }
`````

### Counting the number of unique values

The `count()` method gets the number of unique values in the container using the `cardinality()` method of BitSet.

`````
    public long count() {
        return (long) negative.cardinality() + positive.cardinality();
    }
`````
