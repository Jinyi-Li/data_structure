class HLRUCache {
    private static class Node {
        Node prev;
        Node next;
        int key;
        int value;

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;
    private int size;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        /* even i set capacity, cache.size()!= capacity!!! */
        cache = new HashMap<>(capacity);
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public int get(int key) {
        if(size == 0 || !cache.containsKey(key)){
            return -1;
        }

        Node theNode = cache.get(key);
        moveUpNode(theNode);
        return theNode.value;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)){
            // update map
            Node theNode = cache.get(key);
            theNode.value = value;
            // update list
            moveUpNode(theNode);
        }else{
            if(size + 1 > capacity){
                // remove from list & map
                evictNodeFromCache();
                size--;
            }

            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;

            // add to list
            addToListHead(newNode);
            // add to map
            cache.put(key, newNode);
            size++;
        }
    }

    private void moveUpNode(Node theNode){
        // remove the node from the middle of the list.
        removeNodeFromList(theNode);
        // add the node to the head of the list.
        addToListHead(theNode);
    }

    private void removeNodeFromList(Node theNode){
        theNode.prev.next = theNode.next;
        theNode.next.prev = theNode.prev;
    }

    private void addToListHead(Node theNode){
        theNode.next = head.next;
        head.next.prev = theNode;
        head.next = theNode;
        theNode.prev = head;
    }

    private void evictNodeFromCache(){
        if(size == 0){
            return;
        }
        if(size == 1){
            // remove from map
            cache.remove(head.next.key);
            // remove from list
            head.next = tail;
            tail.prev = head;
            return;
        }

        // remove from map
        cache.remove(tail.prev.key);
        // remove from list
        Node prevToLast = tail.prev.prev;
        tail.prev = prevToLast;
        prevToLast.next = tail;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */