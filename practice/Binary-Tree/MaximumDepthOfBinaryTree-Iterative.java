/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    /* Iteration Version */
    /* Use a ResultType to mimic the behavior of stack!! */
    private static class ResultType {
        TreeNode node;
        int depth;
        private ResultType(TreeNode node, int depth){
            this.node = node;
            this.depth = depth;
        }
    }
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        int max = 0;        
        Stack<ResultType> stack = new Stack<>();
        stack.push(new ResultType(root, 1));
        
        while(!stack.isEmpty()){
            ResultType current = stack.pop();            
            TreeNode node = current.node;

            if(node == null){
                continue;
            }
            
            int currentDepth = current.depth;
            max = Math.max(max, currentDepth);            
            stack.push(new ResultType(node.left, currentDepth + 1));
            stack.push(new ResultType(node.right, currentDepth + 1));
        }
        return max;
    }

    /* Recursion Version */
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        return Math.max(leftMax, rightMax) + 1;
    }
}