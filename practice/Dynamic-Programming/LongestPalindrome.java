package practice.dynamicprogramming;
/*
200. Longest Palindromic Substring
Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

Example
Given the string = "abcdzdcab", return "cdzdc".

Challenge
O(n2) time is acceptable. Can you do it in O(n) time.
*/
public class MlongestPalindrome {
    public String longestPalindrome(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return "";
        }
        int longest = 0;
        int mostLeft = -1;
        int mostRight = -1;
        for (int i = 0; i < s.length(); i++) {
            // ERROR: 没考虑单个char的情况。
            //for (int j = i + 1; j < s.length(); j++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j) && longest < j - i + 1) {
                    longest = j - i + 1;
                    mostLeft = i;
                    mostRight = j;
                }
            }
        }
        if (longest == 0) {
            return "";
        } else {
            return s.substring(mostLeft, mostRight + 1);
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left <= right && left < s.length() && right >= 0) {
            char le = s.charAt(left);
            char ri = s.charAt(right);

            if (!isValidChar(le)) {
                left++;
                continue;
            }
            if (!isValidChar(ri)) {
                right--;
                continue;
            }
            if (le == ri) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean isValidChar(char ch) {
        if (Character.isDigit(ch) || Character.isLetter(ch)) {
            return true;
        }
        return false;
    }
}