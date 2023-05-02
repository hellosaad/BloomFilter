import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BinaryTreeTest {

    public static void main(String[] args) throws IOException {
        String csvFile = "/Users/saadmohammedkhaled/Desktop/bloomfilter/src/main/java/data2.csv";
        String line = "";
        String csvDelimiter = ",";
        BinaryTree bt = new BinaryTree();

        // First pass to count the number of lines
        int numLines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvDelimiter);
                String value = data[0]; // Concatenate the two values with a comma separator
                bt.insert(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading the data from the csv file and inserting it into the binary tree
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            for (int i = 0; i < numLines; i++) {
                line = br.readLine();
                String[] data = line.split(csvDelimiter);
                for (String value : data) {
                    bt.insert(value);
                }
            }
            //Test the memory usage of Binary Tree
            long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            System.out.println("Memory used by the BinaryTree: " + usedMemory / 1024 + " KB");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Test the binary tree search performance
        BufferedReader reader1 = new BufferedReader(new FileReader("/Users/saadmohammedkhaled/Desktop/bloomfilter/src/main/java/test.csv"));
        String testline = "";

        long startTime = System.nanoTime();
        while ((testline = reader1.readLine()) != null) {
            String[] testarr = testline.trim().split(",");
            bt.search(testarr[0]);
        }
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("Binary Tree search execution time: " + executionTime / 1000000 + " ms");


        // Test with IP addresses in the tree (should return true)
        String[] testIpsInBinaryTree = {"4.207.255.255", "4.223.255.255", "4.255.255.255"};
        for (String ip : testIpsInBinaryTree) {
            boolean Contain = bt.search(ip);
            if (!Contain) {
                System.out.println("Error: Binary tree should contain " + ip);
            }
        }

        // Test with IP addresses not in the tree (should return false)
        String[] testIpsNotInFilterInBinaryTree = {"4.4.4.4", "5.5.5.5", "6.6.6.6"};
        for (String ip : testIpsNotInFilterInBinaryTree) {
            boolean Contain = bt.search(ip);
            if (Contain) {
                System.out.println("Error: Binary Tree should not contain " + ip);
            }
        }

    }
}
