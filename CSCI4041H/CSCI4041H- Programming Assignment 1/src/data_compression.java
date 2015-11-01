import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Bat-Orgil on 10/25/2015.
 */
public class data_compression {

    // data_compression coding takes a string, and returns a tree where each node contains character, frequency, left,right.
    // If the node is leaf, it contains the character and the frequency
    // If the node is non-leaf, it only contains the sum of the frequency of its two children.
    public static Node huffman(String str){
        // Map frequency keeps track of the frequency of the letters.
        HashMap<Character,Integer> frequency= new HashMap();
        for(int i=0; i<str.length(); i++){
            char c= str.charAt(i);
            if(frequency.containsKey(c)){
                frequency.put(c,frequency.get(c)+1);
            }else{
                frequency.put(c,1);
            }
        }
        //Initialize the priority queue.
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for(char key : frequency.keySet()){
            pq.add(new Node(frequency.get(key),key));
        }

        int iteration= pq.size()-1;
        for(int i=1; i<= iteration; i++){
            // Merge the two smallest node x,y to z with frequencies combined.
            Node z=  new Node();
            Node x = pq.poll();
            Node y = pq.poll();
            z.left=x; z.right=y;
            z.freq= x.freq+y.freq;
            pq.add(z);
        }
        return pq.poll();
    }


    // This method prints the huffman codes for all of the characters.
    // Once the traversal hits a leaf, which is a character, it prints the second parameter s, the string which was built through dfs calls.
    static void dfs1(Node n, String s){
        if(n.isLeaf()){
            System.out.println(n.c + ":" + s);
            return;
        }
        if(n.left!=null) dfs1(n.left, s+"0");
        if(n.right!=null) dfs1(n.right, s+"1");
    }

    public static void main(String[] args){
        File f= new File("char_info.txt");

        //StringBuilder instead of String for faster performance
        StringBuilder sb = new StringBuilder("");
        try {
            Scanner in = new Scanner(f);
            while(in.hasNext()){
                String s= in.nextLine();
                sb.append(s);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("File didn't found. Make sure the textfile is in System.getProperty(\"user.dir\") folder");
        }
        if(sb.toString()==""){
            System.out.println("No characters are read from the input");
            return;
        }
        // ans contains our answer tree for the given string.
        Node ans= huffman(sb.toString());
        // Print the codes.
        dfs1(ans, "");
        System.out.println("Huffman's tree: ");
        BTreePrinter.printNode(ans);
    }

}

// Node class which was used to build a tree.
class Node implements Comparable<Node>{
    char c; // The given character
    int freq;   // And its frequency
    Node left,right;

    Node(){};

    Node(int f, char ch){
        freq=f;
        c=ch;
    }

    boolean isLeaf(){
        // Which in our problem is equivalent to checking if it has a character or not
        return (left==null && right==null);
    }


    @Override
    public int compareTo(Node o) {
        return freq-o.freq;
    }
}

// Modified from http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram for our purpose
class BTreePrinter {

    public static <T extends Comparable<?>> void printNode(Node root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                if(node.isLeaf()) {
                    System.out.print(node.c);
                }
                else{
                    System.out.print(node.freq);
                }
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}
