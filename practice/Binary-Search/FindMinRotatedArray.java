package practice.binarysearch;

public class MfindMinRotatedArray {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            // 注意这里，是用mid和right比较。
            // 因为如果用mid和mid+1比较，也不知道那个转折点在哪啊。
            // 但是用mid和right比较，至少知道该往哪边走。
            //      因为mid>right等价于在mid和right之间有拐点。
            if (nums[mid] > nums[right]) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (nums[left] < nums[right]) {
            return nums[left];
        } else {
            return nums[right];
        }
    }
}