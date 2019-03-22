/*
    Given a string s, partition s such that every substring of the partition is a palindrome.

    Return all possible palindrome partitioning of s.
    
    Input: "aab"
    Output:
    [
      ["aa","b"],
      ["a","a","b"]
    ]
*/
public class Solution {
    /*
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        // write your code here
        
        List<List<String>> result = new ArrayList<>();
        if (s == null) {
            return result;
        }
        
        dfs(s, new ArrayList<String>(), result);
        return result;
    }
    
    private void dfs (String s, List<String> subset, List<List<String>> result) {
        // 全都切完表示subset加完了，把subset加入result并返回上一层
        if (s.length() == 0) {
            result.add(new ArrayList<String>(subset));
            return;
        }
        
        // 在for循环里面遍历从string切出1个，2个，3个。。字母的情况
        for (int i = 0; i < s.length(); i++) {
            String part = s.substring(0, i + 1);
            if (!isPalindrome(part)) {
                continue;
            }
            subset.add(part);
            // 每次传入的是剩下的一截string，所以不用startIndex这个变量
            dfs(s.substring(i + 1, s.length()), subset, result);
            subset.remove(subset.size() - 1);
        }
    }
    
    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}