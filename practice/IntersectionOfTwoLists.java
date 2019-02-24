/* 
    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    Output: [4,9]

    Order doesn't matter!

    Followup:
    1. What if the given arraies are sorted? How would you optimize the algo?
    Eliminate the sorting part. - O(m + n)

    2. What if nums1's size is small compared to nums2's size?
    Sort nums1 - O(nlogn). 
    Iterate nums2 and do binary search on nums1 - O(mlogn).
    Total runtime - O((m + n)logn), with n << m

    3. What if elements in nums2 are store on disk, and the memory is limited so that you cannot load all elements into the memory at once?
    (1) If nums1 and nums2 are both too huge to fit into the memory, sort them individually using external sort (slice list, fit into memory, store temp partial result to disk, take n% of all partial files into memory and merge).
    (2) If nums1 is small, put elements of nums1 into a map, read memory-fit chunks of nums2 into memory, and compute the intersections.


*/
class IntersectionOfTwoLists {
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0
           || nums2 == null || nums2.length == 0){
            return new int[0];
        }                
        
        // Sort two lists in asce order.
        Arrays.sort(nums1);
        Arrays.sort(nums2);        
        
        // Skip cases without any overlaps!
        if(nums1[0] > nums2[nums2.length - 1] || nums2[0] > nums1[nums1.length - 1]){
            return new int[0];
        }
        
        // Use two pointers to scan two lists!
        int ptr1 = 0;
        int ptr2 = 0;
        List<Integer> res = new ArrayList<>();
        while(ptr1 < nums1.length && ptr2 < nums2.length){
            if(nums1[ptr1] < nums2[ptr2]){
                ptr1++;
            }else if(nums1[ptr1] > nums2[ptr2]){
                ptr2++;
            }else{
                res.add(nums1[ptr1]);
                ptr1++;
                ptr2++;
            }
        }
        
        int[] result = new int[res.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}