package practice.heap;

import java.util.Arrays;

/**
 * Use min-heap to find k points closest to the origin point.
 *
 * Implement the min-heap by myself.
 */
public class MkClosest {
    private Point[] heapArray;
    private int size;
    private Point origin;

    //[[4,6],[4,7],[4,4],[2,5],[1,1]]
    //[0,0]
    //3
    //rtn: [[1,1],[2,5],[4,4]]
    public static void main(String[] args){
        Point p1 = new Point(4,6);
        Point p2 = new Point(4,7);
        Point p3 = new Point(4,4);
        Point p4 = new Point(2,5);
        Point p5 = new Point(1,1);
        Point origin = new Point(0,0);
        MkClosest solver = new MkClosest();
        Point[] result = solver.kClosest(new Point[]{p1, p2, p3, p4, p5},
                origin, 3);
//        solver.printArray(result);
    }

    private void printArray(Point[] array){
        for(Point p : array){
            System.out.println(p.toString());
        }
    }

    public Point[] kClosest(Point[] points, Point origin, int k) {
        if (points == null || points.length == 0) {
            return points;
        }

        heapArray = new Point[points.length];
        this.origin = origin;
        size = 0;

        Point[] results = new Point[k];

        // O(n)
        heapify(points);

        // O(klogn)
        for (int i = 0; i < k; i++) {
            results[i] = pop();
        }

        return results;
    }

    private void heapify(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            insert(points[i]);
        }
    }

    private void insert(Point point) {
        heapArray[size] = point;
        percolateUp(size);
        size++;
        System.out.println(Arrays.toString(heapArray));
    }

    private Point pop() {
        Point node = heapArray[0];
        heapArray[0] = heapArray[size - 1];
        heapArray[size - 1] = null;
        System.out.println(Arrays.toString(heapArray));
//        percolateDown(0);
//        System.out.println(Arrays.toString(heapArray));
        size--;
        return node;
    }

    /******       Helper functions          *******/
    private void percolateUp(int index) {
        // tmply store bottom value.
        Point bottom = heapArray[index];
        int indexParent = (index - 1) / 2;
        while (index > 0 && compare(heapArray[indexParent], bottom) < 0) {
            heapArray[index] = heapArray[indexParent];
            index = indexParent;
            indexParent = (indexParent - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    private void percolateDown(int index) {
        Point top = heapArray[index];
        int largerCldIndex;
        while (index < size / 2) { // while not touch bottom
            int leftCldIndex = (index * 2) + 1;
            int rightCldIndex = leftCldIndex + 1;
            if (rightCldIndex < size &&
                    compare(heapArray[leftCldIndex], heapArray[rightCldIndex]) > 0) {
                largerCldIndex = leftCldIndex;
            }else{
                largerCldIndex = rightCldIndex;
            }
            // if it is greater than top, it means I've found the last position
            if(compare(heapArray[largerCldIndex], top) > 0){
                break;
            }
            heapArray[index] = heapArray[largerCldIndex];//current place
            // should be this larger child
            index = largerCldIndex;//move down
        }
        heapArray[index] = top;//last index should be the tmp top.
    }


    private int compare(Point a, Point b) {
        double da = getDistanceSqr(a, origin);
        double db = getDistanceSqr(b, origin);
        if (da == db) {
            return 0;
        } else if (da < db) {
            return 1;
        } else {
            return -1;
        }
    }


    private double getDistanceSqr(Point a, Point b) {
        return Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2);
    }

    private static class Point {
        private int x;
        private int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
        @Override
        public String toString(){
            return "[" + this.x + ", " + this.y + "]";
        }
    }
}