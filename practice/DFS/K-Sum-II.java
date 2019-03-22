public class Solution {
    /*
     * @param A: an integer array
     * @param k: a postive integer <= length(A)
     * @param target: an integer
     * @return: A list of lists of integer
     */
    public List<List<Integer>> kSumII(int[] A, int k, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if(A.length < k){
            return res;
        }
        
        Arrays.sort(A);
        
        helper(A, k, 0, target, 0, new ArrayList<>(), res);
        return res;
    }
    
    private void helper(int[] A, int k, int startIndex, int target, int sum,
                    List<Integer> currRes, List<List<Integer>> res){
        if(currRes.size() == k){
            if(sum == target){
                res.add(new ArrayList<>(currRes));
            }
            return;
        }
        
        for(int i = startIndex; i < A.length; i++){
            currRes.add(A[i]);
            helper(A, k, i + 1, target, sum + A[i], currRes, res);
            currRes.remove(currRes.size() - 1);
        }    
    }
    
    
}