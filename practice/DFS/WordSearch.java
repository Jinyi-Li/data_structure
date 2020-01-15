/*
    This version only searches for one word. If the word is found in matrix, 
    return true; else false;

    board =
        [
          ['A','B','C','E'],
          ['S','F','C','S'],
          ['A','D','E','E']
        ]

    Given word = "ABCCED", return true.
    Given word = "SEE", return true.
    Given word = "ABCB", return false.
*/
class Solution {
    
    // to change my current position to its neighbors (go down, up, right, left)
    private final int[] X_MOVES = {0, 0, 1, -1};
    private final int[] Y_MOVES = {1, -1, 0, 0};
    
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) {
            return false;
        }

        boolean[][] used = new boolean[board.length][board[0].length];
        
        // try every character in board as the starting character!
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(word.charAt(0) == board[i][j] && containsWord(board, used, i, j, word, 0)){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean containsWord(char[][] board, boolean[][] used, int x, int y, String word, int index){
        // if reaching this line, it means a success!!
        if(index == word.length()){
            return true;
        }
        
        // condition: in bound, not used, same as required!
        if(!inBound(board, x, y) || used[x][y] || word.charAt(index) != board[x][y]){
            return false;            
        }                
        
        // if so, continue checking next character in "word"!
        // 重要。只应该更新used[x][y]，不应该更新used[newRow][newColumn]，那是下一层的事情。
        used[x][y] = true;
        for(int i = 0; i < X_MOVES.length; i++){
            int newX = x + X_MOVES[i];
            int newY = y + Y_MOVES[i];
            if(containsWord(board, used, newX, newY, word, index + 1)){
                return true;
            }
        }
        // if all failed, erase this attempt and return!
        // 重要。如果失败，记得把used[x][y]改回来，因为也没用到。
        used[x][y] = false;
        return false;
    }
    
    private boolean inBound(char[][] board, int x, int y){
        return (x >= 0 && x < board.length 
                && y >= 0 && y < board[0].length);
    }
}