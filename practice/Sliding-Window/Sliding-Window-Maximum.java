/*
    Given an array, return the maximum value in window for each sliding.
    Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
    Output: [3,3,5,5,6,7] 

    Explanation: 

    Window position                Max
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0 || k < 1 || nums.length < k){
            return new int[0];
        }
        
        // deque of indices: all corresponding elems must be in descending order
        // so that nums[peekFirst] must be largest and nums[peekLast] must be smallest
        Deque<Integer> idxFrame = new ArrayDeque<>();
        
        // first (k-1) elements, don't need to output max value
        int idx = 0;
        while(idx < k - 1){
            addToFrame(idxFrame, nums, idx);
            idx++;
        }
        
        // start from kth elem: add new elem (so there're k elems) -> get max -> remove oldest
        int resIdx = 0;
        int[] res = new int[nums.length - k + 1];
        while(idx < nums.length){
            addToFrame(idxFrame, nums, idx);
            res[resIdx] = nums[idxFrame.peekFirst()];
            removeOldestFromFrame(idxFrame, k, idx);
            resIdx++;
            idx++;
        }
        return res;
    }
    
    // add idx to idxFrame while maintaining the descending order in their corresponding numbers.
    private void addToFrame(Deque<Integer> idxFrame, int[] nums, int idx){
        while(!idxFrame.isEmpty() && nums[idxFrame.peekLast()] <= nums[idx]){
            idxFrame.pollLast();
        }
        idxFrame.offerLast(idx);
    }
    
    // remove the (idx-k+1)th index if it's still here - it could be removed earlier in addToFrame()    
    private void removeOldestFromFrame(Deque<Integer> idxFrame, int k, int idx){
        if(idxFrame.peekFirst() == (idx - k + 1)){
            idxFrame.pollFirst();
        }
    }
}