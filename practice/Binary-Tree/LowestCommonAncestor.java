/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }

    只有两种情况：

    Case 1：p和q在两棵不同的子树 => 返回current root node
    Case 2：p和q在同侧（p在q的子树或q在p的子树）=> 先遇到p就返回p，先遇到q就返回q
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {        
        return search(root, p.val, q.val);
    }
    
    private TreeNode search(TreeNode node, int pValue, int qValue) {
        if(node == null) {
            return null;
        }
        if(node.val == pValue || node.val == qValue) {
            return node;
        }
        
        TreeNode targetNodeFromLeft = search(node.left, pValue, qValue);
        TreeNode targetNodeFromRight = search(node.right, pValue, qValue);
        
        // if one is on the left, the other is on the right
        if(targetNodeFromLeft != null && targetNodeFromRight != null) {
            return node;
        }
        
        // if only one node was found
        if(targetNodeFromLeft != null) {
            return targetNodeFromLeft;
        }
        if(targetNodeFromRight != null) {
            return targetNodeFromRight;
        }
        
        // if neither of them was found
        return null;
    }
}