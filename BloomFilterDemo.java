import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class BloomFilterDemo
{
    public static void main(String[] args) throws IOException {


        BloomFilter<String> enlistedIps = BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF-8")), 3000000,0.5);

        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Memory used by the Bloom Filter: " + usedMemory / 1024 + " KB");



        //reading from data2.csv and adding values to the 2,49,000 IP addresses
        BufferedReader reader =new BufferedReader(new FileReader("/Users/saadmohammedkhaled/Desktop/bloomfilter/src/main/java/data2.csv"));
        String line = "";
        while((line=reader.readLine())!=null)
        {
            String [] coloumns =line.trim().split(",");
            enlistedIps.put(coloumns[0]);
            //System.out.println(coloumns[0]);
            //break;

        }
        //testing
        TestBloomFilter.testBloomFilter(enlistedIps);
    }
}

