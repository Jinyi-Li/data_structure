import java.util.*;


/*
    runtime O(n^2) = n to iterate each start * n to iterate each end
    space O(n) = memo space for each start
*/
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);

        // store the result for each substring starting from "index i"
        // null(=unvisited), true, false
        Boolean[] isValidSubstring = new Boolean[s.length()];

        // string s, start index, dictionary, memo matrix
        return isValidWord(s, 0, dict, isValidSubstring);
    }

    private boolean isValidWord(String s, int start, Set<String> dict, int[] isValidSubstring) {
        // if i have reached the end, it means this is valid; otherwise, it'll break the loop earlier!
        if (start == s.length()) {
            return true;
        }

        // if the substring s[start, end] is inspected, return the memorized result!
        if (isValidSubstring[start] != -1) {
            return isValidSubstring[start] == 1;
        }


        for (int i = start; i < s.length(); i++) {
            if (dict.contains(s.substring(start, i + 1))
                    && isValidWord(s, i + 1, dict, isValidSubstring)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        List<String> wordDict = Arrays.asList("cats", "sand", "dog");
        System.out.println(wordBreak.wordBreak("catsanddog", wordDict));
    }
}
