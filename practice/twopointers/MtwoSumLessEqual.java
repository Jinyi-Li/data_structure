package practice.twopointers;

/*
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

Example
Given nums = [2, 7, 11, 15], target = 9
return [1, 2]

因为这道题array是有序的，所以可以用two pointers！
*/
public class MtwoSumLessEqual {
    
    /**
     * @param nums: an array of Integer
     * @param target: target = nums[index1] + nums[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return new int[0];
        }

        int left = 0;
        int right = nums.length - 1;
        while(left < right && right >= 0){
            while((left < right) && (nums[left] + nums[right] < target)){
                left = left + 1;
            }

            if(left < right && nums[left] + nums[right] == target){
                return new int[]{left, right};
            }

            right = right - 1;
        }
 
        return new int[0];
    }
}