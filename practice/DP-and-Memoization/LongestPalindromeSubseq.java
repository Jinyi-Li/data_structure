package practice.dynamicprogramming;

import java.util.Arrays;

/*
    667. Longest Palindromic Subsequence
    Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

    Example
    Given s = "bbbab" return 4
    One possible longest palindromic subsequence is "bbbb".
 */
public class MlongestPalindromeSubseq {


    private void print(int[][] dp){
        for(int i=0; i<dp.length; i++){
            for(int j=0; j<dp[i].length; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    /* 九章算法：非常简洁的解法。*/
    public int bestSolution(String s) {
        int length = s.length();
        if (length == 0)
            return 0;
        int[][] dp = new int[length][length];
        // bbbab
        // 01234
        for (int i = length - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < length; j++) {
                System.out.println("\ni = " + i + " j = " + j);
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // 2,3 = max(2,2 3,3) = 1
                    // 2,4 = dp(3)
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
                print(dp);
            }
        }
        return dp[0][length - 1];
    }


    /* 我的动态规划递归版解法。*/

    public int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }

        int[][] memoized = new int[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++){
            Arrays.fill(memoized[i], -1);
        }

        int max = 0;
        int newMax = max;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (max < (newMax = getLongest(s, memoized, i, j))) {
                    max = newMax;
                }
            }
        }
        return max;
    }

    private int getLongest(String s,
                           int[][] memoized, int left, int right) {
        // base case: single elem must be palindrome
        if (left == right) {
            return 1;
        }

        // ERROR: 忘记检查index范围了！
        if(left >= s.length() || right < 0){
            return 0;
        }

        int res;
        // base case: two elems could be palindrome
        // ERROR: 没有考虑这个情况，导致代码十分复杂。
        if(left + 1 == right){
            if(s.charAt(left) == s.charAt(right)){
                res = 2;
            }else{
                res = 1;
            }
            return res;
        }

        // 这部分是对的。
        if(memoized[left][right] != -1){
            return memoized[left][right];
        }else{
            if(s.charAt(left) == s.charAt(right)){
                res = 2 + getLongest(s, memoized, left + 1, right - 1);
            }else{
                res = Math.max(getLongest(s, memoized, left + 1, right),
                        getLongest(s, memoized, left, right - 1));
            }
            memoized[left][right] = res;
            return res;
        }
    }

    public static void main(String[] a){
        MlongestPalindromeSubseq m = new MlongestPalindromeSubseq();
        m.bestSolution("bbbab");
    }
}
