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
}