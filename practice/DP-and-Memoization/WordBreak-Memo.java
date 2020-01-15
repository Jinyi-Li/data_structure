/*
    runtime O(n^2) = n to iterate each start * n to iterate each end
    space O(n) = memo space for each start
*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0 || wordDict == null || wordDict.isEmpty()) {
            return false;
        }
        
        Set<String> dict = new HashSet<>(wordDict);
        // store the result for each substring starting from "index i"
        // null(=unvisited), true, false
        Boolean[] memo = new Boolean[s.length()];
        // string s, start index, dictionary, memo matrix
        return dfs(s, dict, memo, 0);   
    }
    
    private boolean dfs(String s, Set<String> dict, Boolean[] memo, int startIndex) {
        // if i have reached the end, it means this is valid; otherwise, it'll break the loop earlier!
        if(startIndex == s.length()) {
            return true;
        }
        // if the substring s[start, end] is inspected, return the memorized result!
        if(memo[startIndex] != null) {
            return memo[startIndex];
        }
        // 为什么每次传的是原始s，而不是s的substring，然后index从0开始？
        // 因为我们需要用memo，memo里存的都是original index，所以s也要用原始的，不能slice。不然startIndex就是错的了。
        for(int i = startIndex + 1; i <= s.length(); i++) {
            String word = s.substring(startIndex, i);
            if(dict.contains(word) && dfs(s, dict, memo, i)) {
                // i可能越界，因为i可能等于s.length()
                if(i < s.length()) {
                    // 为什么更新memo[i]而不是memo[startIndex]? 
                    // 因为这里dfs检查的是从i开始的情况，所以应该更新memo[i]
                    // 而且memo[startIndex]会在返回上一层的时候更新。
                    memo[i] = true;    
                }
                return true;
            }
        }
        memo[startIndex] = false;
        return false;
    }
}