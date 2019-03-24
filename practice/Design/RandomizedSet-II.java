import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/*
    You need to design and implement a data structure called RandomizedSet.

    It should provide insert(int val), remove(int val), and getRandom() in O(1).

    BUT IT ALSO ALLOWS DUPLICATES THIS TIME!

    Solution: Map<Integer, List<Integer>> to store {value -> [all indices in the arraylist]}
    each time remove the last elem in the arraylist & corresponding indices list!
*/
class RandomizedSet {
    private List<Integer> data;
    private Map<Integer, List<Integer>> numIdxMap;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        data = new ArrayList<>();
        numIdxMap = new HashMap<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(!numIdxMap.containsKey(val)){
            numIdxMap.put(val, new ArrayList<>());
        }
        
        numIdxMap.get(val).add(data.size());
        data.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!numIdxMap.containsKey(val) || numIdxMap.get(val).size() == 0){
            return false;
        }
        
        List<Integer> allIdx = numIdxMap.get(val);
        int idxInList = allIdx.get(allIdx.size() - 1);
        if(idxInList != data.size() - 1){
            // fill in with the last element in the whole array list
            int lastIdxInList = data.size() - 1;
            data.set(idxInList, data.get(lastIdxInList));
            
            // update the index of this filled-in value
            List<Integer> indices = numIdxMap.get(data.get(lastIdxInList));
            indices.set(indices.size() - 1, idxInList); // must be the last elem in "indices" list
        }        
        data.remove(data.size() - 1);                
        allIdx.remove(allIdx.size() - 1);
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