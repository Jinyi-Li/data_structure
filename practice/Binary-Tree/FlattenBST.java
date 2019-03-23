import java.util.Stack;

public class Solution {

    /*  Iteration Version  */
    public static Node flattenBST(Node root) {
        if(root == null) {
            return null;
        }

        Node prev = null;
        Stack<Node> children = new Stack<>();
        children.push(root);
        while(!children.isEmpty()) {
            Node curr = children.pop();
            if(prev != null) {
                prev.left = null;
                prev.right = curr;
            }
            if(curr.right != null) {
                children.push(curr.right);
            }
            if(curr.left != null) {
                children.push(curr.left);
            }
            prev = curr;
        }
        return root;
    }


    /* Recursion Version */
    public static Node flattenRecursion(Node root) {
        helper(root);
        return root;
    }

    private static Node helper(Node root) {
        if (root == null) {
            return null;
        }

        // 只要将左右子树先变成链表，再将左边和右边接起来就好！
        Node leftLast = helper(root.left);
        Node rightLast = helper(root.right);
        if (leftLast != null) {
            leftLast.left = null;
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        if (rightLast != null) {
            return rightLast;
        }
        if (leftLast != null) {
            return leftLast;
        }
        return root;
    }

    private static class Node {
        int val;
        Node left;
        Node right;

        Node(int theVal) {
            val = theVal;
        }
    }

    private static void printList(Node root){
        while(root != null){
            System.out.println(root.val);
            root = root.right;
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
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        flattenBST(root);
        printList(root);
    }

}
