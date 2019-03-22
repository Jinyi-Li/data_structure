/*
    Given a list of nums = [1,2,3,4,3,2,1], val = 3,
    you need to remove all elems with val = 3 and
    return the new length of 5.
*/
class Solution {
    public int removeElement(int[] nums, int val) {
        if(nums.length < 1){
            return 0;
        }    
        
        int idx = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != val){
                nums[idx] = nums[i];
                idx++;
            }
        }
        
        return idx;
    }
}