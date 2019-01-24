package practice.binarysearch;

/**
 * Find max value in mountain sequence (with a single peek!).
 *
 * No duplicates or allow dupes, both OK.
 */
public class MfindMaxInMountainSeq {
    public int mountainSequence(int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while(left < right - 1){
            int mid = left + (right - left) / 2;
            if(nums[mid] < nums[mid + 1]){  // up-hill
                left = mid;
            }else{
                right = mid;
            }
        }
        return Math.max(nums[left], nums[right]);
    }

    public static void main(String[] args){
        MfindMaxInMountainSeq sol = new MfindMaxInMountainSeq();
        int res = sol.mountainSequence(new int[]{1,3,3,4,5,5,6,2});
        System.out.println("res = " + res);
    }

}
