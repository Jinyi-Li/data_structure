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
        Stack<Node> nodesToVisitStack = new Stack<>();
        Node currentNode = root;
        while(currentNode != null) {
            nodesToVisitStack.push(currentNode);
            currentNode = currentNode.left;
        }
        
        Node dummyHeadNode = new Node();
        Node previousNode = dummyHeadNode;
        while(!nodesToVisitStack.isEmpty()) {
            Node topNode = nodesToVisitStack.pop();
            previousNode.right = topNode;
            topNode.left = previousNode;
            if(topNode.right != null) {
                Node tmp = topNode.right;
                while(tmp != null) {
                    nodesToVisitStack.push(tmp);
                    tmp = tmp.left;
                }
            }
            previousNode = topNode;
        }
        dummyHeadNode = dummyHeadNode.right;
        dummyHeadNode.left = previousNode;
        previousNode.right = dummyHeadNode;
                
        return dummyHeadNode;
    }
}