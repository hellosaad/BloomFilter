import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class HashTableTest {
    private Hashtable<String, String> hm;

    public HashTableTest(Hashtable<String, String> hm) {
        this.hm = hm;
    }

    public void test() throws IOException
    {
        //Test Performance
        BufferedReader reader1 =new BufferedReader(new FileReader("/Users/saadmohammedkhaled/Desktop/bloomfilter/src/main/java/test.csv"));

        String testline = "";
        long startTime = System.nanoTime();
        while((testline=reader1.readLine())!=null) {
            String [] testarr =testline.trim().split(",");
            hm.containsKey(testarr[0]);
        }
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("HashTable execution time "+ executionTime/1000000+ " ms");


        // Test with IP addresses in the filter (should return true)
        String[] testIpsInHashTable = {"4.207.255.255", "4.223.255.255", "4.255.255.255"};
        for (String ip : testIpsInHashTable) {
            boolean mightContain = hm.containsKey(ip);
            if (!mightContain) {
                System.out.println("Error: HashTable should contain " + ip);
            }
        }
        // Test with IP addresses not in the HashTable (should return false)
        String[] testIpsNotInHashTable = {"4.4.4.4", "5.5.5.5", "6.6.6.6"};
        for (String ip : testIpsNotInHashTable) {
            boolean mightContain =  hm.contains(ip);
            if (mightContain) {
                System.out.println("Error: HashTable should not contain " + ip);
            }
        }
    }
}
