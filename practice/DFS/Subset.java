/*
    Given [1,2,3], return [[], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]]
*/
class Solution {
    /**
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets(int[] nums) {
        // write your code here
        List<List<Integer>> results = new ArrayList<>();
        
        if (nums == null) {
            return results;
        }
        Arrays.sort(nums);
        helper(nums, 0, new ArrayList<Integer>(), results);
        return results;
    }
    
    private void helper (int[] nums, int startIndex, List<Integer> subset, List<List<Integer>> result) {
        
        result.add(new ArrayList<Integer>(subset));
        
        for (int i = startIndex; i < nums.length; i++) {
            // 去重
            if (i != startIndex && nums[i] == nums[i-1]) {
                // 前面有一个一样的数且没有被选中
                continue;
            }
            subset.add(nums[i]);
            helper(nums, i + 1, subset, result);
            subset.remove(subset.size() - 1);
        }
    }
}