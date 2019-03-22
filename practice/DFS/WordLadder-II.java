import java.util.*;

/*
    Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]

    Output:
    [
      ["hit","hot","dot","dog","cog"],
      ["hit","hot","lot","log","cog"]
    ]
*/
public class Solution {    

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // the result
        List<List<String>> ladders = new ArrayList<>();
        // next tranversable words of a word
        Map<String, List<String>> nextWordMap = new HashMap<>();
        // the distance to end
        Map<String, Integer> distance = new HashMap<>();

        // distance = {lot=2, hit=0, log=3, dot=2, hot=1, dog=3}
        // map = {lot=[hot, dot, log], log=[lot, dog], dot=[hot, lot, dog], 
        // hot=[hit, dot, lot], dog=[dot, log]}

        dict.add(start);
        bfs(nextWordMap, distance, start, dict);
        // distance = {lot=2, hit=0, log=3, dot=2, cog=4, hot=1, dog=3}
        List<String> path = new ArrayList<>();
        dfs(ladders, path, end, start, distance, nextWordMap);
        return ladders;
    }

    void dfs(List<List<String>> ladders, List<String> path, 
             String curr, String start, Map<String, Integer> distance,
             Map<String, List<String>> nextWordMap) {
        path.add(curr);// destination
        if (curr.equals(start)) {
            Collections.reverse(path);
            ladders.add(new ArrayList<>(path));  // deep copy
            Collections.reverse(path);  // backtrack
        } else {
            for (String next : nextWordMap.get(curr)) {  // 对于curr每一个可能写变换next
                if (distance.containsKey(next) && distance.get(next) == distance.get(curr) - 1) {
                    dfs(ladders, path, next, start, distance, nextWordMap);
                }
            }
        }
        path.remove(path.size() - 1); //Pitfall: backtrack
    }

    void bfs(Map<String, List<String>> nextWordMap, Map<String, Integer> distance,
             String start, Set<String> dict) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);  // start bfs from root
        distance.put(start, 0);  // distance to start is zero
        for (String s : dict) {
            nextWordMap.put(s, new ArrayList<>());
            // nextWordMap = {lot=[], hit=[], log=[], dot=[], cog=[], hot=[], dog=[]}
        }
        System.out.println("nextWordMap = " + nextWordMap);

        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            dist++;
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                List<String> nextList = expand(curr, dict);
                // for all subnodes
                for (String next : nextList) {
                    nextWordMap.get(next).add(curr); // nextWordMap.get(curr).add(next)也可以
                    // nextWordMap = {lot=[hot, dot, log], hit=[hot], 
                    // log=[lot, dog, cog], dot=[hot, lot, dog], cog=[dog, log], 
                    // hot=[hit, dot, lot], dog=[dot, log, cog]}
                    if (!distance.containsKey(next)) {
                        distance.put(next, dist);
                        queue.offer(next);
                    }
                }
            }
        }

    }

    List<String> expand(String str, Set<String> dict) {
        List<String> expansion = new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != str.charAt(i)) {  // pitfall: 否则会加入很多他自己
                    String curr = str.substring(0, i) + ch + str.substring(i + 1);
                    if (dict.contains(curr)) {
                        expansion.add(curr);
                    }
                }
            }
        }
        return expansion;
    }


    public static void main(String[] args) {
        Solution s = new Solution();

        String[] arr = new String[]{"hot", "dot", "dog", "lot", "log"};
        Set<String> dict = new HashSet<>();
        Collections.addAll(dict, arr);
        String start = "hit";
        String end = "cog";

        System.out.println(s.findLadders(start, end, dict));
    }
}