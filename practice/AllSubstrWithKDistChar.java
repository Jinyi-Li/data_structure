import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Test {
    public List<String> solution(String s, int k) {
        if (s == null || s.length() < 1 || k < 1) {
            return new ArrayList<>(0);
        }

        int startIndex = 0;
        int endIndex = 0;
        int uniqueCharCount = 0;
        int[] charFreqCount = new int[256];
        Arrays.fill(charFreqCount, 0);
        List<String> results = new ArrayList<>();

        // Hold startIndex fixed, move endIndex one step forward and check.
        while (endIndex < s.length()) {
            // Update char counter
            if (charFreqCount[s.charAt(endIndex)] == 0) {
                uniqueCharCount++;
            }
            charFreqCount[s.charAt(endIndex)]++;

            // Check if it's a target substring
            if (uniqueCharCount == k) {
                results.add(s.substring(startIndex, endIndex + 1));
            } else if (uniqueCharCount > k) {
                charFreqCount[s.charAt(startIndex)]--;

                // Decrease num of unique chars if it is a unique char.
                if(charFreqCount[s.charAt(startIndex)] == 0) {
                    uniqueCharCount--;
                }

                // Shrink the left side of the sliding window.
                startIndex++;
            }

            // Move endIndex one step forward
            endIndex++;
        }

        return results;
    }


    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.solution("abaccc", 2));;
    }
}
