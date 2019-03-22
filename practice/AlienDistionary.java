/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
] 

Output: "" 

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

*/
class Solution {
       public static String alienOrder(String[] words) {
        StringBuilder sb  = new StringBuilder();

        HashSet[] graph = new HashSet[26];
        Map<Integer,Integer> degree = new HashMap();
           
        // 首先全部出现的char都需要有入度，防止后面一一对应的时候不能访问到
        // eg. wr wrf
        for (String word:words){
            for (char ch: word.toCharArray()){
                degree.put(ch-'a',0);
            }
        }

        for (int i=0;i<words.length-1;i++){

            String w1 = words[i];
            String w2 = words[i+1];

            int len = Math.min(w1.length(),w2.length());
            int k = 0;

            while (k < len){
                char ch1 = w1.charAt(k);
                char ch2 = w2.charAt(k);
                if ( ch1 !=  ch2){
                    if (graph[ch1 - 'a'] == null){
                        graph[ch1 - 'a'] = new HashSet<Integer>();
                    }
                    // 如果添加 说明该节点是第一次出现，需要对入度进行+1 否则就是重复的
                    // eg. w -> t , w -> t 出现两次
                    if (graph[ch1-'a'].add(ch2-'a'))
                        degree.put(ch2-'a',degree.getOrDefault(ch2-'a',0)+1);
                    // 只有当char前面是相同的时候，才能进行这一对char的比较
                    // abce abcd   ->   aeg adf 此时不能判断的情况
                    break;
                }
                
                k++;
            }
        }


        Queue<Integer> queue = new LinkedList();

        for (int node:degree.keySet()){
            if (degree.get(node) == 0)
                queue.offer(node);
        }

        while(!queue.isEmpty()){
            int node = queue.poll();

            sb.append((char)('a'+node));

            HashSet<Integer> connect = graph[node];

            // 如果相连节点为空，说明没有出度，只有入度，需要判空
            if (connect == null)
                continue;
            for (int next:connect){

                int nextDegree = degree.get(next) -1;
                if (nextDegree == 0){
                    degree.put(next,0);
                    queue.offer(next);
                }
                else
                    // 记得更新degree的值
                    degree.put(next,nextDegree);
            }
        }

        // 如果result和degree里面出现的节点数量相同，说明其中有矛盾的节点出现
           // a->b  b->a
       if(sb.length()!=degree.size()) return "";
        return sb.toString();

    }
}