/*
    Given a binary tree, find the maximum sum on a path from any node to any node.

    For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

    Example 1:

    Input:
           1
          / \
         2   3
            / \         
           -6  -6
           /
          -6

    Output: 6
*/
class Solution {
    private int max = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        traverseTree(root);
        return max;
    }
    
    private int traverseTree(TreeNode curr){
        if(curr == null){
            return 0;
        }
        int root = curr.val;
        int left = traverseTree(curr.left);
        int right = traverseTree(curr.right);
        
        int rootLeftRight = root + left + right;
        int rootLeft = root + left;
        int rootRight = root + right;
        
        // a new maximum sum could be produced by root, root+left, root+right, root+right+left
        max = getMax(max, root, rootLeft, rootRight, rootLeftRight);        
        
        // but you cant pass root+left+right to the upper level - because that's not a single path
        // so you only pass root, or root+left, or root+right
        return getMax(root, rootLeft, rootRight);
    }
    
    private int getMax(int a, int b, int c, int d, int e){
        int max = a;
        if(b > max){
            max = b;
        }
        if(c > max){
            max = c;
        }
        if(d > max){
            max = d;
        }
        if(e > max){
            max = e;
        }
        return max;        
    }
    
    private int getMax(int a, int b, int c){
        return Math.max(a, Math.max(b, c));
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
