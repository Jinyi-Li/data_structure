/*
    Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

    Examples: 
    [2,3,4] , the median is 3

    [2,3], the median is (2 + 3) / 2 = 2.5

    Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

    For example,
    Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

    Window position                Median
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       1
     1 [3  -1  -3] 5  3  6  7       -1
     1  3 [-1  -3  5] 3  6  7       -1
     1  3  -1 [-3  5  3] 6  7       3
     1  3  -1  -3 [5  3  6] 7       5
     1  3  -1  -3  5 [3  6  7]      6
    Therefore, return the median sliding window as [1,-1,-1,3,5,6].

    Note: 
    You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
*/
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if(nums == null || k < 1 || nums.length < k){
            return new double[0];
        }
        
        // if frame size is 1
        if(k == 1){
            double[] res = new double[nums.length];
            for(int i = 0; i < res.length; i++){
                res[i] = nums[i];
            }
            return res;
        }
        
        // maxHeap contains first half. minHeap contains second half
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // add first (k - 1) numbers
        for(int i = 0; i < k - 1; i++){
            addToFrame(maxHeap, minHeap, nums[i]);
        }
        
        // add a new number -> get median -> remove first in the current frame
        double[] res = new double[nums.length - k + 1];
        int idx = 0;
        for(int i = k - 1; i < nums.length; i++){
            addToFrame(maxHeap, minHeap, nums[i]);
            res[idx++] = getMedian(maxHeap, minHeap, k);
            removeFirstFromFrame(maxHeap, minHeap, nums[i - k + 1]);
        }
        
        return res;
    }
    
    private double getMedian(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, int k){
        if(k % 2 != 0){
            return maxHeap.peek();
        }else{
            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        }        
    }
    
    private void addToFrame(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, int num){
        if(maxHeap.isEmpty()){
            maxHeap.offer(num);
        }else if(maxHeap.size() == minHeap.size() && minHeap.peek() > num){            
            maxHeap.offer(num);            
        }else if(maxHeap.size() == minHeap.size()){
            maxHeap.offer(minHeap.poll());
            minHeap.offer(num);
        }else if(maxHeap.size() == minHeap.size() + 1 && maxHeap.peek() < num){            
            minHeap.offer(num);
        }else{            
            minHeap.offer(maxHeap.poll());
            maxHeap.offer(num);     
        }
    }
    
    private void removeFirstFromFrame(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, int num){
        if(maxHeap.peek() < num){
            minHeap.remove(num);
        }else{
            maxHeap.remove(num);
        }
        
        while(minHeap.size() < maxHeap.size()){
            minHeap.offer(maxHeap.poll());
        }
        while(maxHeap.size() < minHeap.size()){
            maxHeap.offer(minHeap.poll());
        }                
    }
}