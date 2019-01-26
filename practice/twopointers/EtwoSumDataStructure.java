package practice.twopointers;

public class EtwoSumDataStructure {
    
    private Map<Integer, Integer> numFreqMap = new HashMap<>();
    private List<Integer> nums = new ArrayList<>();
    
    /**
     * @param number: An integer
     * @return: nothing
     */
    public void add(int number) {
        nums.add(number);
        numFreqMap.put(number, numFreqMap.getOrDefault(number, 0) + 1);
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        for(int i = 0; i < nums.size(); i++){
            int num1 = nums.get(i);
            int num2 = value - num1;
            if(num1 == num2){
                if(numFreqMap.get(num1) > 1){
                    return true;
                }
            }else{
                if(numFreqMap.containsKey(num2)){
                    return true;
                }
            }
        }
        return false;
    }
}