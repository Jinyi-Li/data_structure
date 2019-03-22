/*
    Given a list of numbers, return its all unique permutations.

    Input: [1,2,3]
    Output: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]

*/
public class Solution {
    /*
     * @param :  A list of integers
     * @return: A list of unique permutations
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }
        // 没有重复的话不需要sort
        Arrays.sort(nums);
        dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), results);
        
        return results;
    }
    
    // visited[]就相当于组合类里的startIndex。visited[]表示哪个数用过了，startIndex是表示前面的拿进组合了
    private void dfs(int[] nums,
                     boolean[] visited,  
                     List<Integer> permutation,
                     List<List<Integer>> results) {
        if (nums.length == permutation.size()) {
            results.add(new ArrayList<Integer>(permutation));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            
            // 去重
            // 当前的数和前面的数一样，但前面的数没用过(!visited)
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            
            permutation.add(nums[i]);
            visited[i] = true;
            dfs(nums, visited, permutation, results); //这五行呈镜像
            // 回溯，递归开始时加入的东西，递归结束后依次撤回
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
           
        }
    }
 }