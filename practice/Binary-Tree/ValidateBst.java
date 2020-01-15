/**
 * Runtime O(n). Space O(n) for stack space
 *
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    /* Inorder Traversal  */
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        
        Stack<TreeNode> nodesToVisit = new Stack<>();
        enstack(nodesToVisit, root);
        
        TreeNode prev = null;
        while(!nodesToVisit.isEmpty()){
            TreeNode curr = nodesToVisit.pop();
            
            if(prev != null){
                if(prev.val >= curr.val){
                    return false;
                }                
            }
            prev = curr;
                        
            if(curr.right != null){
                enstack(nodesToVisit, curr.right);
            }            
        }
        return true;
    }
    
    private void enstack(Stack<TreeNode> stack, TreeNode root){
        while(root != null){
            stack.push(root);
            root = root.left;
        }
    }

    /* Recursion */
    public boolean isValidBST(TreeNode root) {
        return validateSubTree(root, null, null);
    }
    
    private boolean validateSubTree(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }
        
        if(lower != null && node.val <= lower) {
            return false;
        }
        if(upper != null && node.val >= upper) {
            return false;
        }
        
        return validateSubTree(node.right, node.val, upper) 
            && validateSubTree(node.left, lower, node.val);
    }
}