/**
 * Description
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */

/**
 * 一个普通的分治法的应用。divide-conquer
 * 
 * 根节点分别派左右节点去检查他们自己的子树，提交他们的最大长度。根节点收上来以后如果发现左右最大长度差大于1，就把flag设成false。
 * 根节点收上来以后如果发现左右最大长度差大于1，就把flag设成false。
 */

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    boolean isBalanced = true;

    public boolean isBalanced(TreeNode root) {
        // write your code here
        helper(root, 1);
        return isBalanced;
    }

    
    private int helper (TreeNode root, int height) {
        if (root == null) {
            return height - 1;
        }
        int leftHeight = helper(root.left, height + 1);
        int rightHeight = helper(root.right, height + 1);
        
        if (Math.abs(leftHeight-rightHeight) > 1) {
            isBalanced = false;
            return 0;
        }
        
        return Math.max(leftHeight, rightHeight);
    }
}