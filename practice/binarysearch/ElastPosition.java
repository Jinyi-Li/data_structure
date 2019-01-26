package practice.binarysearch;

public class ElastPosition {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int lastPosition(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int pos = -1;
        int left = 0;
        int right = nums.length - 1;
        int mid;
        // Left <= right to ensure when left==right don't just exist
        while(left <= right){
            mid = left + (right - left) / 2;  // avoid overflow
            if(nums[mid] <= target){ // when equal, keep to the right
                if(nums[mid] == target){
                    pos = mid;
                }
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        if(pos != -1 && nums[pos] == target){
            return pos;
        }
        return -1;
    }
}