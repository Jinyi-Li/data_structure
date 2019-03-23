/*
    Given an array of integer contains from 0 to n-1 except one number is missing.
    Find the missing number.

    Input = [0,4,2,3]
    Output = 1

    Followup: what if the array is too long to fit in memory?
    - same idea... read file chunk by chunk and do XOR.
*/
class Solution {
    public int missingNumber(int[] nums) {
        int missing = nums.length;
        for(int i = 0; i < nums.length; i++){
            missing = missing ^ i ^ nums[i];
        }
        return missing;
    }
}