import java.util.*;

class Solution {

    public void method(String s) {

        int[] extraParenthesesCount = getExtraPathesesCount(s);
        int leftExtra = extraParenthesesCount[0];
        int rightExtra = extraParenthesesCount[1];

        List<String> result = new ArrayList<>();

        removeExtra(s, leftExtra, rightExtra, result, 0);

        System.out.println(result);
    }

    // start - 确保了只会在“start”之后找后续需要删除的括号，从而避免重复！
    private void removeExtra(String s, int leftExtra, int rightExtra,
                             List<String> result, int start) {

        if (leftExtra == 0 && rightExtra == 0) {
            int[] extraParen = getExtraPathesesCount(s);
            if (extraParen[0] == 0 && extraParen[1] == 0) {
                result.add(s);
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (i != start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            String substring = s.substring(0, i) + s.substring(i + 1);

            if (s.charAt(i) == '(') {
                if (leftExtra > 0) {
                    removeExtra(substring, leftExtra - 1, rightExtra, result, i);
                }
            } else if (s.charAt(i) == ')') {
                if (rightExtra > 0) {
                    removeExtra(substring, leftExtra, rightExtra - 1, result, i);
                }
            }
        }
    }

    private int[] getExtraPathesesCount(String s) {
        int[] count = new int[2];
        int leftExtra = 0;
        int rightExtra = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftExtra++;
            } else if (s.charAt(i) == ')') {
                if (leftExtra > 0) {
                    leftExtra--;
                } else {
                    rightExtra++;
                }
            }
        }
        count[0] = leftExtra;
        count[1] = rightExtra;
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.method("((())(");
    }
}