import java.util.Stack;

public class Test {

    public void solution(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        pushItAndItsLeftChildren(stack, root);

        while(!stack.isEmpty()) {
            Node currentNode = stack.pop();
            System.out.println(currentNode.val);
            if(currentNode.right != null) {
                pushItAndItsLeftChildren(stack, currentNode.right);
            }
        }
    }

    private void pushItAndItsLeftChildren(Stack<Node> stack, Node node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
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
        Node root = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);

        root.left = n2;
        root.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        Test test = new Test();
        test.solution(root);
    }
}
