package practice.binarysearch;

/**
 * Search in rotated array (only rotated once).
 * 
 * No dups!!
 */
class MsearchInRotatedArray {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while(left < right - 1){
            int mid = left + (right - left) / 2;
            // 如果mid在左上角那条线上
            if(nums[left] < nums[mid]){
                // 以mid为界分为左右两边。
                if(nums[left] <= target && target <= nums[mid]){
                    right = mid;
                }else{
                    left = mid;
                }
            // 如果mid在右下角那条线上
            }else{
                // 以mid为界分为左右两边。
                if(nums[mid] <= target && target <= nums[right]){
                    left = mid;
                }else{
                    right = mid;
                }
            }
        }
        
        if(nums[left] == target){
            return left;
        }
        if(nums[right] == target){
            return right;
        }
        return -1;
    }
}