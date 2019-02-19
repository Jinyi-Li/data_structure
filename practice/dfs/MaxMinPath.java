/**
 * DFS version solution.
 *
 * Maximum min-value-in-path.
 */
public class MaxMinPath {
    private int min;
    private int max;
    private int rowLen;
    private int colLen;

    public int maxMinPath(int[][] matrix) {
        rowLen = matrix.length;
        colLen = matrix[0].length;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        dfsHelper(matrix, min, 0, 0);
        return max;
    }

    private void dfsHelper(int[][] matrix, int min, int i, int j) {
        // Out of bound checking.
        if(i >= rowLen || j >= colLen) {
            return;
        }

        min = Math.min(min, matrix[i][j]);
        // Reach bottom-right node, update current maximum min-value in path.
        if((i == rowLen - 1) && (j == colLen - 1)) {
            max = Math.max(max, min);
            return;
        }

        // Go right.
        dfsHelper(matrix, min, i, j + 1);
        // Go down.
        dfsHelper(matrix, min, i + 1, j);
    }

    public static void main(String[] args) {
        int[][] arr = {
                {5, 4, 5},
                {3, 1, 2},
                {9, 7, 8}
        };
        MaxMinPath p = new MaxMinPath();
        System.out.println(p.maxMinPath(arr));
    }
}
