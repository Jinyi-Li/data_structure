import java.util.*;
import java.util.Map.Entry;

class Solution {

    // this is a dummy node as the root
    private Node root;

    public Solution() {
        root = new Node();
    }

    public void add(String word) {
        validate(word);

        Node curr = root;
        for (char ch : word.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                curr.children.put(ch, new Node());
            }
            curr = curr.children.get(ch);
        }
        // REMEMBER TO SET THE LAST FLAG TO TRUE!
        curr.isFullWord = true;
    }

    public boolean search(String word) {
        validate(word);

        if (root.children.size() == 0) {
            return false;
        }

        return searchPartialWord(word, 0, root);
    }

    private boolean searchPartialWord(String word, int charIndex, Node curr) {
        // no such curr in the tree, return false
        if (curr == null) {
            return false;
        }
        // such curr exists and this is the last char i need.
        if (charIndex == word.length()) {
            return curr.isFullWord;
        }

        char charToSearch = word.charAt(charIndex);
        // not wildcard char, just do the search
        if (charToSearch != '.') {
            return searchPartialWord(word, charIndex + 1, curr.children.get(charToSearch));
        }

        // is a wildcard, check all its children!
        for (Entry<Character, Node> charNodeEntry : curr.children.entrySet()) {
            if (searchPartialWord(word, charIndex + 1, charNodeEntry.getValue())) {
                return true;
            }
        }
        return false;
    }

    private void validate(String word) {
        if (word == null || word.trim().length() == 0) {
            throw new IllegalArgumentException();
        }
    }

    private static class Node {
        // a map of {char -> Node reference particularly for this char}
        Map<Character, Node> children;
        boolean isFullWord;

        private Node() {
            children = new HashMap<>();
            isFullWord = false;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.add("dad");
        solution.add("bad");
        solution.add("mad");

        System.out.println(solution.search("daddy"));
        System.out.println(solution.search("bad"));

    }
}