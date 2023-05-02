import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BinaryTree {
    Node root;

     // Constructor to initialize an empty binary tree
    public BinaryTree() {
        root = null;
    }

    // Private method to recursively insert a new node in the binary tree
    private void insert(String value, Node node) {
        if (value.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new Node(value);
            } else {
                insert(value, node.left);
            }
        } else if (value.compareTo(node.value) > 0) {
            if (node.right == null) {
                node.right = new Node(value);
            } else {
                insert(value, node.right);
            }
        } else
        {
            // Value already exists
        }
    }

    // Public method to insert a new node in the binary tree
    public void insert(String value) {
        if (root == null) {
            root = new Node(value);
        } else {
            insert(value, root);
        }
    }

     // Public method to print the elements of the binary tree in inorder
     public void printInorder() {
        inorderTraversal(root);
    }

    // Private method to perform an inorder traversal of the binary tree
    private void inorderTraversal(Node node) {
        if (node == null) {
            return;
        }
            inorderTraversal(node.left);
            System.out.println(node.value);
            inorderTraversal(node.right);
        
    }
    //To search whether a value exists in the binary tree or not
    public boolean search(String value) {
        return search(value, root);
    }
    
    private boolean search(String value, Node node) {
        if (node == null) {
            return false;
        } else if (node.value.equals(value)) {
            return true;
        } else if (value.compareTo(node.value) < 0) {
            return search(value, node.left);
        } else {
            return search(value, node.right);
        }
    }
    

    public static void main(String[] args) throws IOException {
        String csvFile = "/Users/saadmohammedkhaled/Desktop/bloomfilter/src/main/java/data2.csv";
        String line = "";
        String csvDelimiter = ",";
        BinaryTree bt = new BinaryTree();

        // First pass to count the number of lines
        int numLines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
        {
            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(csvDelimiter);
                String value = data[0]; // Concatenate the two values with a comma separator
                bt.insert(value);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

         // Reading the data from the csv file and inserting it into the binary tree
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
        {
            for (int i = 0; i < numLines; i++) {
                line = br.readLine();
                String[] data = line.split(csvDelimiter);
                for (String value : data) {
                    bt.insert(value);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}