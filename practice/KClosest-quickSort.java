import java.util.Arrays;


/*
 *  求top k问题，很自然想到用PriorityQueue，时间复杂度为O(nlogn).
 *  
 *  如果用减治法 (Reduce and Conquer) + 快速排序，则可以将时间复杂度降至O(n).
 *
 *  核心思想：
 *
 *  只需要找top k个点，所以不需要将整个数组排序，甚至top k个点之间也不需要排序。
 *  快速排序的核心在于partition之后，会将整个数组分为左右两部分，左边都比pivot小，右边都比pivot大。
 *  所以只需要让partiiton找到k这个位置就好了。
 */
public class Test {
    public int[] kClosest(int[] points, int k) {
        int[] result = new int[k];

        int left = 0;
        int right = points.length - 1;
        quickSort(points, left, right, k);

        for (int i = 0; i < k; i++) {
            result[i] = points[i];
        }
        return result;
    }

    private void quickSort(int[] points, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        double pivotVal = points[right];
        int partitionIndex = partition(points, left, right, pivotVal);

        /*
         *  If [-----partition---k------], I need to sort right half.
         *  If [-----k---partition------],, I need to sort left half. 
         */
        if (partitionIndex <= k) {
            quickSort(points, partitionIndex + 1, right, k);
        }else{
            quickSort(points, left, partitionIndex - 1, k);
        }
    }

    private int partition(int[] points, int left, int right, double pivotVal) {
        if (left > right) {
            return left;
        }
        int rightIndex = right;
        while (left < right) {
            while (left < right && points[left] <= pivotVal) {
                left++;
            }
            while (left < right && points[right] >= pivotVal) {
                right--;
            }
            if (left < right) {
                swap(points, left, right);
                left++;
                right--;
            }
        }
        swap(points, left, rightIndex);
        return left;
    }

    private void swap(int[] points, int i, int j) {
        int tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    public static void main(String[] args) {
        Test t = new Test();
        int[] points = {1,7,3,5,2,4,8};
        int[] res = t.kClosest(points, 4);
        System.out.println(Arrays.toString(res));
    }
}
