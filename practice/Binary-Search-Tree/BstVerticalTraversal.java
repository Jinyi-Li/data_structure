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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        
        Map<Integer, List<Integer>> levelNodesMap = new TreeMap<>();
        
        Queue<TreeNode> nodesQueue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        
        nodesQueue.offer(root);
        levelQueue.offer(0);
        
        while(!nodesQueue.isEmpty()){
            TreeNode curr = nodesQueue.poll();
            int level = levelQueue.poll();
            
            if(!levelNodesMap.containsKey(level)){
                levelNodesMap.put(level, new ArrayList<>());
            }
            levelNodesMap.get(level).add(curr.val);
            
            if(curr.left != null){
                nodesQueue.offer(curr.left);
                levelQueue.offer(level - 1);
            }
            if(curr.right != null){
                nodesQueue.offer(curr.right);
                levelQueue.offer(level + 1);
            }
        }        
        
        List<List<Integer>> res = new ArrayList<>();
        for(List<Integer> nodeList : levelNodesMap.values()){            
            res.add(nodeList);
        }
        return res;
    }        
}