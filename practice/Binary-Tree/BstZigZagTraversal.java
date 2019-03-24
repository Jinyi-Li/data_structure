/* 
    Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    return its zigzag level order traversal as:
    [
      [3],
      [20,9],
      [15,7]
    ]
*/
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>(0);
        }        
        List<List<Integer>> res = new ArrayList<>();
        Stack<TreeNode> odd = new Stack<>();
        Stack<TreeNode> even = new Stack<>();
        odd.push(root);
        
        int level = 1;
        while(!odd.isEmpty() || !even.isEmpty()){
            int levelSize;
            List<Integer> nodeList = new ArrayList<>();
            
            if(level % 2 != 0){
                levelSize = odd.size();                
                for(int i = 0; i < levelSize; i++){
                    TreeNode curr = odd.pop();
                    nodeList.add(curr.val);
                    if(curr.left != null){
                        even.push(curr.left);
                    }
                    if(curr.right != null){
                        even.push(curr.right);
                    }
                }                
            }else{
                levelSize = even.size();                
                for(int i = 0; i < levelSize; i++){
                    TreeNode curr = even.pop();
                    nodeList.add(curr.val);
                    if(curr.right != null){
                        odd.push(curr.right);
                    }
                    if(curr.left != null){
                        odd.push(curr.left);
                    }
                }                
            }

            res.add(nodeList);
            level++;
        }
        
        return res;
    }
}