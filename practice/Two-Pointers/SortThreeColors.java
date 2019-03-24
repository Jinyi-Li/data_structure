import java.util.Arrays;

class MsortThreeColors {
    public void sortColors(int[] nums) {
        if(nums == null || nums.length == 0){
            return;
        }
        
        int left = 0;
        int right = nums.length - 1;
        int i = 0;
        while(right >= i && left <= right && i < nums.length){
            if(nums[i] == 1){
                i++;
            }else if(nums[i] == 2){
                swap(nums, i, right);
                right--;
                
            }else{
                swap(nums, i, left);
                left++;
                i++;
            }
        }
    }
    
    private void swap(int[] nums, int left, int right){
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}