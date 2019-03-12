/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/ 
class Solution {
    public Node treeToDoublyList(Node root) {
        if(root == null){
            return null;
        }
        
        Node head = null;
        Node prev = null;
        
        Stack<Node> nodesToVisit = new Stack<>();
        Node tmp = root;        
        while(tmp != null){
            nodesToVisit.push(tmp);
            tmp = tmp.left;
        }
        
        System.out.println(nodesToVisit.peek().val);
        
        Node curr = root;
        while(!nodesToVisit.isEmpty()){
            curr = nodesToVisit.pop();
            if(prev == null){
                head = curr;
                prev = curr;            
            }else{
                prev.right = curr;
                curr.left = prev;
            }            
            
            if(curr.right != null){
                tmp = curr.right;
                while(tmp != null){
                    nodesToVisit.push(tmp);
                    tmp = tmp.left;
                }
            }
            prev = curr;
        }
        
        head.left = curr;
        curr.right = head;
        return head;
    }
    
}