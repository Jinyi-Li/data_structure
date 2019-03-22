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
        
        // Push elems on the most left branches to the stack. (exactly the same as in-order traversal)
        Node head = null;
        Node prev = null;        
        Stack<Node> nodesToVisit = new Stack<>();
        Node tmp = root;        
        while(tmp != null){
            nodesToVisit.push(tmp);
            tmp = tmp.left;
        }
        
        Node curr = root;
        while(!nodesToVisit.isEmpty()){
            curr = nodesToVisit.pop();
            if(prev == null){   // this is the first elem to be poped (the smallest)
                head = curr;    // store the smallest elem as "head" in order to return it as result
                prev = curr;            
            }else{                 // do the normal in-order traversal, and make:
                prev.right = curr; // the smaller.right -> the larger
                curr.left = prev;  // the larget.left -> the smaller
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
        // connect the first and the last
        head.left = curr;
        curr.right = head;
        // return the first (smallest)
        return head;
    }
    
}
