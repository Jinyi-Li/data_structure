class MthreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums == null || nums.length == 0){
            return new ArrayList<>(0);
        }
                
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        
        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            
            int twoSum = 0 - nums[i];
            int left = i + 1;
            int right = nums.length - 1;            
            while(left < right){
                if(nums[left] + nums[right] == twoSum){
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));                                
                    while(right - 1 > left && nums[right - 1] == nums[right]){
                        right--;
                    }                    
                    right--;
                    while(left + 1 < right && nums[left + 1] == nums[left]){
                        left++;
                    }
                    left++;
                }else if(nums[left] + nums[right] > twoSum){
                    while(right - 1 > left && nums[right - 1] == nums[right]){
                        right--;
                    }                    
                    right--;                    
                }else{
                    while(left + 1 < right && nums[left + 1] == nums[left]){
                        left++;
                    }
                    left++;
                }                
            }            
        }
        return result;
    }
}