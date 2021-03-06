/**
 * Description
 * Given a binary tree, determine if it is height-balanced.
 * 
    For this problem, a height-balanced binary tree is defined as a 
    binary tree in which the depth of the two subtrees of every node 
    never differ by more than 1.

 * 一个普通的分治法的应用。divide-conquer
 * 
 * 根节点分别派左右节点去检查他们自己的子树，提交他们的最大长度。
 * 根节点收上来以后如果发现左右最大长度差大于1，就把flag设成false。
 */

public class Solution {
    
    private boolean isBalanced = true;
    
    public boolean isBalanced(TreeNode root) {
        traverse(root, 1);
        return isBalanced;
    }
    
    private int traverse(TreeNode node, int depth) {
        if(node == null) {
            return depth;
        }
        int leftDepth = traverse(node.left, depth);
        int rightDepth = traverse(node.right, depth);
        if(Math.abs(leftDepth - rightDepth) > 1) {
            isBalanced = false;
            return 0;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }
}