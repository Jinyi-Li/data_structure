/*
    Given a list of integer, find the maximum (j - i) such that 
    nums[j] > nums[i].

    For example, {34, 8, 10, 3, 2, 80, 30, 33, 1}, output = 6
    (j = 7, i = 1).

    Runtime O(n)
    Space O(1)

    The idea is to memoize the minSoFar from the left and the maxSoFar
    from the right, then find the maximum difference between two indices.
*/
class Solution {    
    
    public int getMaxDiff(int[] nums) {
        
        int maxDiff = Integer.MIN_VALUE;
        int[] minSoFar = new int[nums.length];
        int[] maxSoFar = new int[nums.length];

        for(int i = 0; i < nums.length; i++){
            if(i == 0){
                minSoFar[i] = nums[i];                
            }else{
                minSoFar[i] = Math.min(minSoFar[i - 1], nums[i]);
            }
        }

        for(int i = nums.length - 1; i >= 0; i--){
            if(i == nums.length - 1){
                maxSoFar[i] = nums[i];
            }else{
                maxSoFar[i] = Math.max(maxSoFar[i + 1], nums[i]);
            }
        }

        int minIndex = 0;
        int maxIndex = 0;
        while(minIndex < nums.length && maxIndex < nums.length){
                        
            if(maxSoFar[maxIndex] > minSoFar[minIndex]){
                maxDiff = Math.max(maxDiff, maxIndex - minIndex);
                maxIndex++;
            }else{
                minIndex++;
            }
        }

        return maxDiff;
    }
}
