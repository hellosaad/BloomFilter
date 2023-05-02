import com.google.common.hash.BloomFilter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestBloomFilter {
    public static void testBloomFilter(BloomFilter<String> enlistedIps) throws IOException
    {
        //Testing time Complexity:
        BufferedReader reader = new BufferedReader(new FileReader("/Users/saadmohammedkhaled/Desktop/bloomfilter/src/main/java/test.csv"));
        String line;
        long startTime = System.nanoTime();
        while ((line = reader.readLine()) != null)
        {
            String[] columns = line.trim().split(",");
            enlistedIps.mightContain(columns[0]);
        }
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("Bloom Filter execution time" + executionTime/1000000 + " ms");



        // Test with IP addresses in the filter (should return true)
        String[] testIpsInFilter = {"4.207.255.255", "4.223.255.255", "4.255.255.255"};
        for (String ip : testIpsInFilter) {
            boolean mightContain = enlistedIps.mightContain(ip);
            if (!mightContain) {
                System.out.println("Error: Bloom filter should contain " + ip);
            }
        }

        // Test with IP addresses not in the filter (should return false)
        String[] testIpsNotInFilter = {"4.4.4.4", "5.5.5.5", "6.6.6.6"};
        for (String ip : testIpsNotInFilter) {
            boolean mightContain = enlistedIps.mightContain(ip);
            if (mightContain)
            {
                System.out.println("Error: Bloom filter should not contain " + ip);
            }
        }

        // Test with IP addresses that have a high probability of false positives
        String[] testIpsFalsePositives = {"4.207.255.256", "4.223.255.200", "5.555.555.555"};
        int falsePositiveCount = 0;
        for (String ip : testIpsFalsePositives) {
            boolean mightContain = enlistedIps.mightContain(ip);
            if (mightContain) {
                falsePositiveCount++;
            }
        }
        if (falsePositiveCount == testIpsFalsePositives.length) {
            System.out.println("All test IPs were identified as potentially enlisted");
        } else {
            System.out.println("PASS : none of the IPs were identified as potentially enlisted");
        }

    }

}
