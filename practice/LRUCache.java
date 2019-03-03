class LRUCache {
    
    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;
    private int size;
    private final int CAPACITY;
    
    public LRUCache(int capacity) {
        CAPACITY = capacity;
        cache = new HashMap<>(CAPACITY);
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;        
        size = 0;           
    }
    
    public int get(int key) {
        if(size == 0){
            return -1;
        }
        
        if(cache.containsKey(key)){
            Node target = cache.get(key);
            updateList(target);
            return target.val;
        }
        return -1;        
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)){
            Node target = cache.get(key);
            updateList(target);
            target.val = value;
            cache.put(key, target);    
            return;
        }
        
        if(size + 1 > CAPACITY){
            Node removed = removeLRU();
            cache.remove(removed.key);
            size--;
        }                
        
        Node newNode = addToList(key, value);
        cache.put(key, newNode);
        size++;
    }
    
    /* ************ Helper methods and class ************* */
        
    private void updateList(Node target) {
        target.prev.next = target.next;   
        target.next.prev = target.prev;
            
        Node newNext = head.next;
        target.prev = head;        
        target.next = newNext;                
        head.next = target;        
        newNext.prev = target;        
    }
    
    private Node removeLRU() {
        Node toRemove = tail.prev;        
        if(size == 1){
            head.next = tail;
            tail.prev = head;
            return toRemove;
        }
        
        Node newLRU = tail.prev.prev;
        newLRU.next = tail;
        tail.prev = newLRU;  
        return toRemove;
    }
    
    private Node addToList(int key, int value) {
        Node node = new Node(key, value);
        Node newNext = head.next;
        head.next = node;        
        node.prev = head;
        node.next = newNext;
        newNext.prev = node;
        return node;
    }
    
    private static class Node {        
        int key;
        int val;
        Node next;
        Node prev;
        
        private Node(int theKey, int theVal) {
            key = theKey;
            val = theVal;            
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */