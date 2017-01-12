import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Node root;
    public static List<Node> nodes;
    public static boolean sumFound;
    public static int sumToFind;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int _numTasks = Integer.parseInt(br.readLine());
            for(int t = 0; t < _numTasks; t++) {
                int _numNumbers = Integer.parseInt(br.readLine());
                for(int r = 0; r < _numNumbers; r++) {
                    int next = Integer.parseInt(br.readLine());
                    if(root == null) {
                        root = new Node(next);
                    } else {
                        executeNesting(root, next);
                    }
                }
                sumFound = false;
                sumToFind = Integer.parseInt(br.readLine());
                executeSum(root, 0);
                System.out.println((t+1) + " " + (sumFound ? "gelijk" : "niet gelijk"));


            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    private static void executeNesting(Node parentNode, int valueToAdd) {
        if(valueToAdd < parentNode.getValue()) {
            //Add value in a new child if it has no left child, else check on the parent's left child
            if(parentNode.hasLeftChild()) {
                executeNesting(parentNode.getLeftChild(), valueToAdd);
            } else {
                parentNode.addLeft(new Node(valueToAdd));
            }
        } else if (valueToAdd > parentNode.getValue()) {
            if(parentNode.hasRightChild()) {
                executeNesting(parentNode.getRightChild(), valueToAdd);
            } else {
                parentNode.addRight(new Node(valueToAdd));
            }
        }
    }

    private static void executeSum(Node currentNode, int currentSum) {
        currentSum += currentNode.getValue();
        if(currentSum==sumToFind) sumFound = true;
        for(Node child : currentNode.getAllChildren()){
            executeSum(child, currentSum);
        }
    }
}

class Node {
    private Node left, right;
    private int value;

    public Node (int value) {
        this.value = value;
    }

    public Node (int value, Node left) {
        this.value = value;
        this.left = left;
    }

    public Node (int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void addLeft(Node left){
        this.left = left;
    }

    public void addRight(Node right) {
        this.right = right;
    }

    public boolean hasLeftChild() {
        return left != null;
    }

    public boolean hasRightChild() {
        return right != null;
    }

    public Node getLeftChild() {
        return left;
    }

    public Node getRightChild() {
        return right;
    }

    public List<Node> getAllChildren() {
        List<Node> children = new ArrayList<>();
        if(hasLeftChild()) children.add(left);
        if(hasRightChild()) children.add(right);
        return children;
    }
}
