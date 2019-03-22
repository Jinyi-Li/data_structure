/*
    Given an integer (represented by an array), return the next greater number
    using the same set of digits.

    For example,
    input = 1,2,3
    output = 1,3,2

    input = 1,1,5
    output = 1,5,1

    input = 3,2,1
    output = 1,2,3 (there's no solution so return the min value you can get) 

    runtime = O(n). space = O(1).
*/
class Solution {
    public void nextPermutation(int[] nums) {
        int i;
        for(i = nums.length - 2; i >= 0; i--){
            if(nums[i] < nums[i + 1]){
               break; 
            }
        }

        // it is a descending order number list
        if(i < 0){                 
            reverseList(nums, 0, nums.length - 1);
            return;
        }                

        // Find the one "just greater" than nums[i].
        // REMEMBER to search from the right-most!
        int minIndex = nums.length - 1;
        while(minIndex > i && nums[minIndex] <= nums[i]){
            minIndex--;
        }        
        
        // swap them two
        swap(nums, i, minIndex);
        reverseList(nums, i + 1, nums.length - 1);        
    }

    private void reverseList(int[] nums, int left, int right){    
        while(left < right){            
            swap(nums, left, right);             
            left++;
            right--;
        }
    }
    
    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;   
    }
}