/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {        
    
    /* I always ensure that stack.peek is an integer! */    
    private Stack<NestedInteger> elemsToReturn = new Stack<>();
    
    /* Put everything (from end to start) into stack and make the peek an integer. */
    public NestedIterator(List<NestedInteger> nestedList) {
        flatten(nestedList);        
    }
    
    private void flatten(List<NestedInteger> elem){
        for(int i = elem.size() - 1; i >= 0; i--){
            elemsToReturn.push(elem.get(i));
        }
        // Check the peek elem
        if(!elemsToReturn.isEmpty() && !elemsToReturn.peek().isInteger()){
            flatten(elemsToReturn.pop().getList());
        }
    }

    @Override
    public Integer next() {
        Integer next = elemsToReturn.pop().getInteger();
        
        if(!elemsToReturn.isEmpty() && !elemsToReturn.peek().isInteger()){
            flatten(elemsToReturn.pop().getList());
        }
        return next;
    }

    @Override
    public boolean hasNext() {
        return !elemsToReturn.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */