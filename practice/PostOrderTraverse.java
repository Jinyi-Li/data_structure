/**
 * Sliding windows.
 */

import java.util.*;

public class Solution {

    public void postOrderTraverse(Node root) {
        if(root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        while(root != null) {
            stack.push(root);
            root = root.left;
        }

        Node previous = null;
        while(!stack.isEmpty()) {
            Node curr = stack.peek();

            if(curr.right != null && curr.right != previous) {
                curr = curr.right;
                while(curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
                previous = stack.peek();
            } else {
                previous = stack.pop();
                System.out.println("curr = " + previous.val);
            }
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
        Solution solution = new Solution();
        Node root = new Node(2);
        Node n2 = new Node(1);
        Node n3 = new Node(4);
        Node n5 = new Node(0);

        root.left = n2;
        n2.left = n5;
        n2.right = n3;

        Node n4 = new Node(3);
        root.right = n4;

        solution.postOrderTraverse(root);
    }
}
