package practice.binarysearch;

class HfindMinRotatedArrayII {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        return findMinRecursive(nums, left, right);
    }
        
    private int findMinRecursive(int[] nums, int left, int right){
        if(left >= right - 1){
            return Math.min(nums[left], nums[right]);
        }
        
        int mid = left + (right - left) / 2;
        
        if(nums[mid] > nums[right]){
            return findMinRecursive(nums, mid, right);
        }else if(nums[mid] < nums[right]){
            return findMinRecursive(nums, left, mid);
        }else{
            // 当mid==right时，无法判断pivot在哪里，所以兵分两路。
            return Math.min(findMinRecursive(nums, mid, right),
                findMinRecursive(nums, left, mid));   
        }
    }
}