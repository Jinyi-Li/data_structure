public class McopyListWithRandomPointer {
  
    // map of {node -> copyOfNode}.
    private Map<RandomListNode, RandomListNode> visited = new HashMap<RandomListNode, RandomListNode>();        

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        RandomListNode curr = head;

        // Creating the new head node.
        RandomListNode newHead = new RandomListNode(head.label);
        visited.put(head, newHead);
        
        RandomListNode newCurr = newHead;

        // Iterate on the linked list until all nodes are cloned.
        while (curr != null) {
            // Get the clones of the nodes referenced by random and next pointers.
            newCurr.random = getClonedNode(curr.random);
            newCurr.next = getClonedNode(curr.next);

            // Move one step ahead in the linked list.
            curr = curr.next;
            newCurr = newCurr.next;
        }
        return newHead;
    }
    
    private RandomListNode getClonedNode(RandomListNode node) {
        if (node != null) {      
            if (!this.visited.containsKey(node)) {              
                visited.put(node, new RandomListNode(node.label));        
            }
            return visited.get(node);
        }
        return null;
    }
}