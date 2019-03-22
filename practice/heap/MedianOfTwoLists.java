class Solution {
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        heapify(nums1);
        heapify(nums2);
        
        if(maxHeap.size() != minHeap.size()){
            return maxHeap.peek();
        }
        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
    
    private void heapify(int[] data){
        for(int value : data){
            if (maxHeap.isEmpty()) {
                maxHeap.offer(value);            
                continue;
            }

            if (maxHeap.size() == minHeap.size() + 1 && value < maxHeap.peek()) {
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(value);
            } else if (maxHeap.size() == minHeap.size() + 1) {
                minHeap.offer(value);
            } else if (maxHeap.size() == minHeap.size() && value < minHeap.peek()) {
                maxHeap.offer(value);
            } else {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(value);
            }        
        }
    }
}