/*
    Given an array of integers, return the subarray with the maximum product.

    NOTICE: the integers could be positive, negative, or 0.
*/
class Solution {
    public int maxProduct(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        
        int maxProduct = nums[0];
        
        // Use max and min to store existing information!
        // Because there could be both positive and negetive numbers in the array,
        // the next maximum product could be produced from the previous minSoFar.
        int max = nums[0];
        int min = nums[0];        
        for(int i = 1; i < nums.length; i++){            
            int curr = nums[i];
            int tmpMax = max;
            max = getMax(max * curr, min * curr, curr);
            min = getMin(tmpMax * curr, min * curr, curr);
            maxProduct = Math.max(maxProduct, max);
        }
        return maxProduct;
    }
    
    private int getMax(int a, int b, int c){
        return Math.max(a, Math.max(b, c));
    }
    private int getMin(int a, int b, int c){
        return Math.min(a, Math.min(b, c));
    }
}