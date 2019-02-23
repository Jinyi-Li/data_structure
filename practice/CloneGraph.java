/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/
class CloneGragh {
    public Node cloneGraph(Node node) {
        
        Map<Node, Node> realCopyMap = new HashMap<>();
        Queue<Node> nodesToCopy = new LinkedList<>();
        nodesToCopy.offer(node);
        
        // Copy the root node
        Node newRoot = new Node();
        newRoot.val = node.val;
        realCopyMap.put(node, newRoot);        
        
        
        // Copy everything else using BFS with queue to track unvisited nodes.
        while (!nodesToCopy.isEmpty()) {
            Node current = nodesToCopy.poll();            
            Node copy = null;
            if (realCopyMap.containsKey(current)) {
                copy = realCopyMap.get(current);
            } else {
                copy = new Node();
                copy.val = current.val;
            }        
            
            List<Node> newNeighborList = new ArrayList<>();                                  
            for (Node neighbor : current.neighbors) {                
                if (realCopyMap.containsKey(neighbor)) {
                    newNeighborList.add(realCopyMap.get(neighbor));
                } else {
                    Node newNeighbor = new Node();
                    newNeighbor.val = neighbor.val;                    
                    realCopyMap.put(neighbor, newNeighbor);
                    newNeighborList.add(newNeighbor);
                    
                    // Only add unvisited node into the map!!                    
                    nodesToCopy.offer(neighbor);
                }
            }
            copy.neighbors = newNeighborList;            
        }
        
        return newRoot;        
    }
}