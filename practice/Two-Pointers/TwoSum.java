package practice.twopointers;

public class EtwoSum {
    /**
     * @param numbers: An array of Integer
     * @param target: target = numbers[index1] + numbers[index2]
     * @return: [index1, index2] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // 1. map {number : frequency}
        // 2. arraylist (numbers)
        // 3. search for a target, start from first number in arraylist
        //    look for its counterpart in the map.
        Map<Integer, List<Integer>> numFreqMap = new HashMap<>();
        for(int i = 0; i < numbers.length; i++){
            if(!numFreqMap.containsKey(numbers[i])){
                numFreqMap.put(numbers[i], new ArrayList<>());
            }
            numFreqMap.get(numbers[i]).add(i);
        }
        
        for(int i = 0; i < numbers.length; i++){
            int another = target - numbers[i];
            if((another == numbers[i] && numFreqMap.get(another).size() > 1)){
                return new int[]{numFreqMap.get(another).get(0), numFreqMap.get(another).get(1)};    
            }else if(another != numbers[i] && numFreqMap.containsKey(another)){
                if(numFreqMap.get(another).get(0) < i){
                    return new int[]{numFreqMap.get(another).get(0), i};
                }else{
                    return new int[]{i, numFreqMap.get(another).get(0)};
                }
            }
        }

        return new int[]{-1, -1};
    }
}