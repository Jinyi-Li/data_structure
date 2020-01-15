import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Test {

    private static final String NULL_NODE = "null";
    private static final String LIST_SEPARATOR = ", ";

    public void solution(Node root) {
        if (root == null) {
            return;
        }

        String serializedTree = serialize(root);
        System.out.println(serializedTree);

        Node newRoot = deserialize(serializedTree);

        String reserializedTree = serialize(newRoot);
        System.out.println(reserializedTree); // it should be the same as serializedTree
    }

    private String serialize(Node root) {
        StringBuilder stringBuilder = new StringBuilder();
        buildString(stringBuilder, root);
        return stringBuilder.toString();
    }

    private void buildString(StringBuilder stringBuilder, Node node) {
        if (node == null) {
            stringBuilder.append(NULL_NODE).append(LIST_SEPARATOR);
        } else {
            stringBuilder.append(node.val).append(LIST_SEPARATOR);
            buildString(stringBuilder, node.left);
            buildString(stringBuilder, node.right);
        }
    }

    public Node deserialize(String data) {
        Queue<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(LIST_SEPARATOR)));
        return buildTree(nodes);
    }

    private Node buildTree(Queue<String> nodes) {
        String val = nodes.poll();
        if (val.equals(NULL_NODE)) {
            return null;
        } else {
            Node node = new Node(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }

    private static class Node {
        int val;
        Node left;
        Node right;

        Node(int theVal) {
            val = theVal;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

        Test test = new Test();
        test.solution(n1);
    }
}
