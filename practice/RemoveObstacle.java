/**
 * Lot robot needs to remove obstacle.
 *
 * Robot must start from top-left corner.
 * Robot cannot enter trenches.
 * Robot can only  enter flat area.
 * Robot needs to find the one and only one obstacle.
 *
 * Trenches: represented by 1.
 * Flat area: represented by 0.
 * Obstacle: represented by 9.
 *
 * {
 * {1, 0, 0},
 * {1, 0, 0},
 * {1, 9, 1}
 * }
 *
 * Return 3 because the total distance to 9 is 3 steps!
 */

import java.util.Queue;
import java.util.LinkedList;

public class RemoveObstacle {

    private static final int[] ROW_MOVES = {-1, 1, 0, 0};
    private static final int[] COL_MOVES = {0, 0, -1, 1};

    public int removeObstacle(int numRows, int numCols, int[][] lot) {
        if (lot == null || lot.length == 0 || lot[0].length == 0) {
            return -1;
        }

        // use bfs to traverse the matrix level by level.
        Queue<Node> nodesToVisit = new LinkedList<>();
        nodesToVisit.offer(new Node(0, 0));

        // use a matrix to store "visited/unvisited" state for each node.
        boolean[][] visited = new boolean[numRows][numCols];

        int steps = 0;

        // if meet 9, return current number of steps, else check next level.
        while (!nodesToVisit.isEmpty()) {

            // to avoid repeatedly count step, nodes in same level have same
            // "step" from the origin. Use this var to ensure this!
            int sizeOfCurrLevel = nodesToVisit.size();

            for (int currCount = 0; currCount < sizeOfCurrLevel; currCount++) {
                // get current node
                Node current = nodesToVisit.poll();
                visited[current.row][current.col] = true;

                // if 9, return current steps
                if (lot[current.row][current.col] == 9) {
                    return steps;
                }

                // else, visit later if its in bound and not visited yet
                for (int i = 0; i < ROW_MOVES.length; i++) {
                    int newRow = current.row + ROW_MOVES[i];
                    int newCol = current.col + COL_MOVES[i];
                    Node neighbor = new Node(newRow, newCol);

                    if (inBound(numRows, numCols, neighbor)
                            && lot[newRow][newCol] != 0
                            && !visited[newRow][newCol]) {
                        nodesToVisit.offer(neighbor);
                    }
                }
            }

            // nodes in this level have been visited, add one more step!
            steps++;
        }

        // end of traversal, return -1;
        return -1;
    }

    private boolean inBound(int numRows, int numCols, Node node) {
        return node.row >= 0 && node.row < numRows
                && node.col >= 0 && node.col < numCols;
    }


    private static class Node {
        int row;
        int col;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        RemoveObstacle solution = new RemoveObstacle();
        int[][] lot = {
                {1, 1, 1, 1},
                {1, 0, 1, 1},
                {0, 1, 0, 1},
                {1, 9, 0, 1},
                {0, 1, 1, 1}
        };
        System.out.println(solution.removeObstacle(5, 4, lot));
    }

}
