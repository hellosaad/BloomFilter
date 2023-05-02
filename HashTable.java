import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class HashTable {
    public static void main(String[] args) {
        try {
            CSVReader reader;
            reader = new CSVReader(new FileReader("/Users/saadmohammedkhaled/Desktop/bloomfilter/src/main/java/data2.csv"));
            String[] nextLine;
            Hashtable<String, String> hm = new Hashtable<String, String>(300000);

            //Check Memory Usage
            long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            System.out.println("Memory used by the HashTable: " + usedMemory / 1024 + " KB");

            while ((nextLine = reader.readNext()) != null) {
                String key = nextLine[0];
                String value = nextLine[1];
                hm.put(key, value);
            }

            // To Print HashTable Un-Comment Line below
            // System.out.println(hm);

            HashTableTest test = new HashTableTest(hm);
            test.test();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CsvValidationException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}