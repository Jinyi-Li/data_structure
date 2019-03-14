/*
    Given a binary tree, return the number of single valued subtrees.

    For example, given a tree like 
         5
        / \
       1   5
     /  \   \
    5   5   5
    the algorithm should return 4. 
    
    Given a tree like this:
         5
        / \
       4   5
     /  \   \
    4   4   5
    the algorithm should return 5.
*/
public class Solution {

    private int numOfUniValuedSubtree;
    public int getNumOfSingleValuedSubtree(Node root){
        if(root == null){
            return 0;
        }
        if(root.left != null){
            helper(root.left);
        }
        if(root.right != null){
            helper(root.right);
        }
        return numOfUniValuedSubtree;
    }

    private boolean helper(Node root){
        // if root itself is null or is a leaf node
        if(root == null){
            return true;
        }

        boolean isLeftUni = helper(root.left);
        if(!isLeftUni){
            return false;
        }

        boolean isRightUni = helper(root.right);
        if(!isRightUni){
            return false;
        }

        // check if root, left, and right have the same value!
        boolean isValid = true;
        if(root.left != null &&  root.right != null){
            isValid = (root.data == root.left.data && root.data == root.right.data);
        }
        if(root.left != null){
            isValid = (root.data == root.left.data);
        }
        if(root.right != null){
            isValid = (root.data == root.right.data);
        }
        if(isValid){
            numOfUniValuedSubtree++;
            return true;
        }else{
            return false;
        }
    }

    public static void main(String args[]) {
        Node root = new Node(5);
        root.left = new Node(4);
        root.right = new Node(5);
        root.left.left = new Node(4);
        root.left.right = new Node(4);
        root.right.right = new Node(5);

        Solution solution = new Solution();
        System.out.println(solution.getNumOfSingleValuedSubtree(root));


    }
}

/* A binary tree node */
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        left = null;
        right = null;
        this.data = data;
    }
};
