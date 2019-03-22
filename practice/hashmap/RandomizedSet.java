import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/*
    You need to design and implement a data structure called RandomizedSet.

    It should provide insert(int val), remove(int val), and getRandom() in O(1).

    It should contain any duplicate because it's a set.

    Followup: what if it can contain some duplicate? (See: RandomizedSet-II.java)
*/
class RandomizedSet {
    private List<Integer> data;
    private Map<Integer, Integer> numIdxMap;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        data = new ArrayList<>();
        numIdxMap = new HashMap<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(numIdxMap.containsKey(val)){
            return false;
        }
        
        numIdxMap.put(val, data.size());
        data.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!numIdxMap.containsKey(val)){
            return false;
        }
        
        int idxInList = numIdxMap.get(val);
        if(idxInList != data.size() - 1){
            data.set(idxInList, data.get(data.size() - 1));
            numIdxMap.put(data.get(data.size() - 1), idxInList);
        }
        data.remove(data.size() - 1);
        numIdxMap.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        if(data.size() == 0){
            throw new NoSuchElementException("Empty set.");
        }

        int randomIdx = (int) (Math.random() * data.size());
        return data.get(randomIdx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */