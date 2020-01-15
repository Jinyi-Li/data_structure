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
        
        // 解题思路：只看left和mid。[left,mid]这段区间要么单调递增，要么不单调。
        // 如果单调递增就按正常sorted array处理，否则先缩小区间范围再继续判断。
        while(left < right - 1){
            int mid = left + (right - left) / 2;            
            // 如果[left, mid]是单调的
            if(nums[left] < nums[mid]){
                // 如果target又是nums[left]和nums[mid]之间的数，那可以去这个单调区间里找
                if(nums[left] <= target && target <= nums[mid]){                    
                    right = mid;
                }else{
                    // 否则先缩小范围再继续循环
                    left = mid;
                }
            // [left,mid]区间不单调，left在左上方，mid在右下方
            }else{
                // 如果target在nums[mid]和nums[right]之间这段必然单调的区间内（因为只可能有一段不单调区间），按正常处理
                if(nums[mid] <= target && target <= nums[right]){
                    left = mid;
                }else{
                    // 否则先缩小范围再说
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