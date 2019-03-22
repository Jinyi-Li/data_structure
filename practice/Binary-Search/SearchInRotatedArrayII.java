package practice.binarysearch;

/**
 * Search in rotated array (only retated once).
 * Allow duplicates!!
 *
 * Test case: [1,1,3,1] search 3. return true!
 * 
 * 当nums[mid]和nums[left]或者nums[right]
 * 相等时，无法判断该往左走还是往右走。所以只能兵分两路。
 */
class MsearchInRotatedArrayII {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return false;
        }
        
        int left = 0;
        int right = nums.length - 1;
        return searchRecursive(nums, left, right, target);        
    }
    
    private boolean searchRecursive(int[] nums, int left, int right, int target){
        // base case
        if(left >= right - 1){
            return (nums[left] == target || nums[right] == target);
        }
        
        // recursive case
        int mid = left + (right - left) / 2;
        if(nums[left] < nums[mid]){
            if(nums[left] <= target && target <= nums[mid]){
                return searchRecursive(nums, left, mid, target);
            }else{
                return searchRecursive(nums, mid, right, target);
            }
        }else if(nums[left] > nums[mid]){
            if(nums[mid] <= target && target <= nums[right]){
                return searchRecursive(nums, mid, right, target);
            }else{
                return searchRecursive(nums, left, mid, target);
            }
        }else{
            // 兵分两路，看左右两边有没有target存在。
            return searchRecursive(nums, left, mid, target)
                || searchRecursive(nums, mid, right, target);
        }
    }
}