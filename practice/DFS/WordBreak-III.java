/*
    Given a word and a dictionary, return the number of sentences the word can form,
    by adding spaces between characters.

    Input:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    Output: 2
    because there are:
    [
      "cats and dog",
      "cat sand dog"
    ]
    Example 2:

    Input:
    s = "pineapplepenapple"
    wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
    Output: 3
    because there are:
    [
      "pine apple pen apple",
      "pineapple pen apple",
      "pine applepen apple"
    ]
    Explanation: Note that you are allowed to reuse a dictionary word.
*/
public class Solution {
    public int wordBreak3(String s, Set<String> dict) {
        int n = s.length();
        String lowerS = s.toLowerCase();
        
        Set<String> lowerDict = new HashSet<String>();
        for(String str : dict) {
            lowerDict.add(str.toLowerCase());
        }
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = i; j < n;j++){
                String sub = lowerS.substring(i, j + 1);
                if(lowerDict.contains(sub)){
                    dp[i][j] = 1;
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                for(int k = i; k < j; k++){
                    dp[i][j] += (dp[i][k] * dp[k + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }
}