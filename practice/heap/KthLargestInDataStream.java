/*
    Given an initial array nums = [4,5,8,2] and an integer k,
    each time I call add(int val) method, it should return the current kth 
    largest number including this newly added integer val.

    For example: 
    Input = [4,5,8,2], k = 3
    k.add(3) // return 4 because 4 is the 3rd largest in [4,5,8,2,3]
    k.add(5) // return 5 because 5 is the 3rd largest in [4,5,8,2,3,5]
*/
class KthLargest {
    
    private Queue<Integer> queue = new PriorityQueue<>();
    private final int LIMIT;

    public KthLargest(int k, int[] nums) {
        LIMIT = k;
        
        for(int number : nums){
            add(number);
        }
    }
    
    public int add(int val) {
        queue.offer(val);
        
        if(queue.size() > LIMIT){
            queue.poll();
        }
        return queue.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */