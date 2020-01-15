/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class MinimumDepthOfBinaryTree {
    
    public int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        // 如果是叶子节点：计数为1
        if(root.left == null && root.right == null) {
            return 1;
        }
        // 如果只有右孩子：minDepth为 右孩子的子树minDepth + root本身
        if(root.left == null){
            return minDepth(root.right) + 1;
        }
        // 如果只有左孩子：同理
        if(root.right == null){
            return minDepth(root.left) + 1;
        }
        // 如果既有左孩子又有右孩子：minDepth为 左右孩子的子树minDepth + root本身
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}