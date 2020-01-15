/*
    Input:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    Output:
    [
      "cats and dog",
      "cat sand dog"
    ]
    Example 2:

    Input:
    s = "pineapplepenapple"
    wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
    Output:
    [
      "pine apple pen apple",
      "pineapple pen apple",
      "pine applepen apple"
    ]
    Explanation: Note that you are allowed to reuse a dictionary word.
*/
public class Solution {
    public List<String> wordBreak(String s, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Map<String, List<String>> memo = new HashMap<String, List<String>>();
        return dfs(s, dict, memo);
    }

    public List<String> dfs(String s, Set<String> dict, Map<String, List<String>> memo){
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        
        List<String> results = new ArrayList<String>();
        
        if (s.length() == 0) {
            return results;
        }
        
        // 至关重要的一句。它保证了最小的word被加到results里，否则results每层都是空的。
        if (dict.contains(s)) {
            results.add(s);
        }
        for (int i = 1; i <= s.length(); i++){
            String word = s.substring(0, i);
            if (!dict.contains(word)) {
                continue;
            }
            
            String suffix = s.substring(i);
            List<String> segmentations = dfs(suffix, dict, memo);
            // 这句话只有在subtringResults不为空时才执行。所以一上来先判断dict.contains(s)是很重要的！
            for (String segmentation : segmentations){
                results.add(word + " " + segmentation);
            }
        }
        
        memo.put(s, results);
        return results;
    }
}