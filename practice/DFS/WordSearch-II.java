/*
    Given a matrix of lower alphabets and a dictionary. Find all words in the dictionary that can be found in the matrix. A word can start from any position in the matrix and go left/right/up/down to the adjacent position. One character only be used once in one word. No same word in dictionary

    Example: 
            doaf 
            agai 
            dcan

    dictionary: {"dog", "dad", "dgdg", "can", "again"}

    return: {"dog", "dad", "can", "again"}

    for each char in matrix:
        if()
*/
class Solution {
    
    final static int[] X_MOVES = {-1,1,0,0};
    final static int[] Y_MOVES = {0,0,-1,1};
    boolean[][] visited;
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public List<String> wordSearchII(char[][] board, List<String> words) {
        visited = new boolean[board.length][board[0].length];
        
        if (board == null || words == null) {
            return new ArrayList<>();
        }

        Map<String, Boolean> trieMap = new HashMap<>();
        Set<String> set = new HashSet<>();
        
        // build a trie-like HashMap
        for (int i = 0; i < words.size(); i++){
            String word = words.get(i);
            for (int j = 1; j < word.length(); j++){
                trieMap.putIfAbsent(word.substring(0, j), false);
            }
            trieMap.put(word, true);
        }
        // pitfall: start from every character
        for (int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                visited[i][j] = true;
                dfs(board, set, String.valueOf(board[i][j]),i, j, trieMap);
                visited[i][j] = false;
            }
        }
        return new ArrayList<String>(set);
    }
    
    private void dfs(char[][] board,
                    Set<String> set, 
                    String prefix,
                    int x, int y,
                    Map<String, Boolean> trieMap) {
        // don't have this prefix, no need to continue
        if (!trieMap.containsKey(prefix)) {
            return;
        }
        // is a word
        if (trieMap.get(prefix)) {
            set.add(prefix);
        }
        
        // try all four directions
        for (int i = 0; i < 4; i++){
            int newX = x + X_MOVES[i];
            int newY = y + Y_MOVES[i];
            if (inBound(board, newX, newY) && !visited[newX][newY]){
                visited[newX][newY] = true;
                // recursion
                dfs(board, set, prefix + board[newX][newY], newX, newY, trieMap);
                // backtrack
                visited[newX][newY] = false;
            }            
        }
    }
    
    private boolean inBound(char[][] board, int x, int y) {
        return x>=0 && y>= 0 && x<board.length && y<board[x].length;
    }
}