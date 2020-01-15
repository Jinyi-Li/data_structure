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
            return Collections.emptyList();
        }
        Map<Integer, List<Integer>> columnNodesMap = new TreeMap<>();
        
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        Queue<Integer> columnQueue = new LinkedList<>();
        columnQueue.offer(0);
        
        while(!nodeQueue.isEmpty()){
            TreeNode currentNode = nodeQueue.poll();
            int columnNumber = columnQueue.poll();
            if(!columnNodesMap.containsKey(columnNumber)){
                columnNodesMap.put(columnNumber, new ArrayList<>());
            }
            columnNodesMap.get(columnNumber).add(currentNode.val);
            
            if(currentNode.left != null){
                updateQueues(nodeQueue, columnQueue, currentNode.left, columnNumber-1);
            }
            if(currentNode.right != null){
                updateQueues(nodeQueue, columnQueue, currentNode.right, columnNumber+1);
            }
        }
        
        List<List<Integer>> results = new ArrayList<>();
        for(List<Integer> nodeList : columnNodesMap.values()){
            results.add(nodeList);
        }
        return results;
    }
    
    private void updateQueues(Queue<TreeNode> nodeQueue, Queue<Integer> columnQueue, TreeNode node, int columnNumber){
        nodeQueue.offer(node);
        columnQueue.offer(columnNumber);
    }    
}
