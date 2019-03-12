class Solution {

    private Random random = new Random();
    private Map<Integer, List<Integer>> map = new HashMap();
    
    public Solution(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList());
            list.add(i);
            map.put(nums[i], list);
        }    
    }
    
    public int pick(int target) {
        List<Integer> list = map.get(target);
        if(list.size() == 1) {
            return list.get(0);
        }
                    
        int index = random.nextInt(list.size());  
        return list.get(index); 
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */