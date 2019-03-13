/*
    Given a list of integer, find the maximum difference between
    two integers and the larger number appears after the smaller.

    That is, to find Max{nums[j] - nums[i]}, with j > i.

    For example, {2, 3, 10, 6, 4, 8, 1}, output = 8.   
    {7, 9, 5, 6, 3, 2}, output = 2.

    Runtime O(n)
    Space O(1)

    The idea is to keep track of the max and min values in the list (and 
    therefore, get the maximum difference) along the way of traversal.
*/
class Solution {    
    
    public int getMaxDiff(int[] nums) {
        
        int maxNumber = Integer.MIN_VALUE;
        int minNumber = Integer.MAX_VALUE;
        int maxDiff = maxNumber - minNumber;

        for(int i = 0; i < nums.length; i++){
            
            // if the number is large enough to get a new maxDiff
            if(nums[i] > maxNumber){
                maxNumber = nums[i];
                maxDiff = maxNumber - minNumber;
            }
            
            // or if the number is small enough to get a new minNumber
            if(nums[i] < minNumber){
                minNumber = nums[i];
            }
        }

        return maxDiff;
    }
}