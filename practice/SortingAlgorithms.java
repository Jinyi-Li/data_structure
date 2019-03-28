import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Sort {

    /*  Simple sort */

    public static void bubbleSort(int[] data) {
        for (int end = data.length - 1; end > 0; end--) {
            for (int idx = 0; idx < end; idx++) {
                if (data[idx] > data[idx + 1]) {
                    swap(data, idx, idx + 1);
                }
            }
        }
    }

    public static void selectionSort(int[] data) {
        for (int start = 0; start < data.length - 1; start++) {
            int min = start;
            for (int idx = start + 1; idx < data.length; idx++) {
                if (data[idx] < data[min]) {
                    min = idx;
                }
            }
            swap(data, start, min);
        }

    }

    public static void insertionSort(int[] data) {
        for (int partition = 0; partition < data.length - 1; partition++) {
            int tmp = data[partition];
            int idx = partition;
            while (idx > 0 && data[idx - 1] > tmp) {
                data[idx] = data[idx - 1];
                idx--;
            }
            data[idx] = tmp;
        }
    }

    /* Advanced sort */

    public static void quickSort(int[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private static void quickSort(int[] data, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotVal = data[right];
        int partition = partition(data, left, right, pivotVal);
        quickSort(data, left, partition - 1);
        quickSort(data, partition + 1, right);
    }

    private static int partition(int[] data, int left, int right, int pivotVal) {
        int rightBound = right;
        while (left < right) {
            while (left < right && data[left] <= pivotVal) {
                left++;
            }
            while (left < right && data[right] >= pivotVal) {
                right--;
            }
            if (left < right) {
                swap(data, left, right);
            }
        }
        swap(data, left, rightBound);
        return left;
    }

    public static void mergeSort(int[] data) {
        int[] tmp = new int[data.length];
        mergeSort(data, tmp, 0, data.length - 1);
    }

    private static void mergeSort(int[] data, int[] tmp, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(data, tmp, left, mid);
        mergeSort(data, tmp, mid + 1, right);
        merge(data, tmp, left, mid, right);
    }

    private static void merge(int[] data, int[] tmp, int leftStart, int leftEnd, int rightEnd) {
        int rightStart = leftEnd + 1;

        int idxA = leftStart;
        int idxB = rightStart;
        int idx = leftStart;

        while (idxA <= leftEnd && idxB <= rightEnd) {
            if (data[idxA] < data[idxB]) {
                tmp[idx] = data[idxA];
                idxA++;
            } else {
                tmp[idx] = data[idxB];
                idxB++;
            }
            idx++;
        }
        while (idxA <= leftEnd) {
            tmp[idx++] = data[idxA++];
        }
        while (idxB <= rightEnd) {
            tmp[idx++] = data[idxB++];
        }

        System.arraycopy(tmp, leftStart, data, leftStart, rightEnd - leftStart + 1);
    }

    /* Others */

    public static void heapSort(int[] data) {
        int[] heapArray = new int[data.length];
        int currentSize = 0;

        for (int num : data) {
            offer(heapArray, num, currentSize);
            currentSize++;
        }

        for (int i = 0; i < data.length; i++) {
            currentSize--;
            data[i] = poll(heapArray, currentSize);
        }
    }
    private static void offer(int[] heapArray, int number, int currentSize) {
        heapArray[currentSize] = number;
        int idx = currentSize;
        int parent = (idx - 1) / 2;
        while (idx > 0 && heapArray[parent] > number) {
            // move down parent in order to heapify up the "heapArray[idx]".
            heapArray[idx] = heapArray[parent];
            idx = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[idx] = number;
    }
    private static int poll(int[] heapArray, int currentSize) {
        int res = heapArray[0];

        heapArray[0] = heapArray[currentSize];
        heapArray[currentSize] = 0;
        int fakeTop = heapArray[0];
        int smallerChild;
        int parent = 0;
        while (parent < currentSize / 2) {
            int leftChild = parent * 2 + 1;
            int rightChild = leftChild + 1; // it can be out of bound
            if (rightChild < currentSize && heapArray[rightChild] < heapArray[leftChild]) {
                smallerChild = rightChild;
            } else {
                smallerChild = leftChild;
            }
            if (heapArray[smallerChild] > fakeTop) {
                break;
            } else {
                // move up the largeChild in order to place the fake "top" downwards.
                heapArray[parent] = heapArray[smallerChild];
                parent = smallerChild;
            }
        }
        heapArray[parent] = fakeTop;

        return res;
    }


    private static void swap(int[] data, int x, int y) {
        int tmp = data[x];
        data[x] = data[y];
        data[y] = tmp;
        HashMap<Integer, Integer> set = new HashMap<>();
        set.get(1);
    }

    private static void printArray(int[] data) {
        System.out.println(Arrays.toString(data));
    }

    public static void main(String[] args) {
        int[] data = {4, 2, 6, 9, 0, 3, 1, 5, 8, 7};
        mergeSort(data);
        printArray(data);
    }
}