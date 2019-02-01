package practice.twopointers;

/*
Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.

Example
Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].
*/
public class MsortKColors {
    
    public static void main(String[] args) {
        Test t = new Test();
        int[] data = {3,2,2,1,4};
        t.quickSort(data);
        System.out.println(Arrays.toString(data));
    }

    public void quickSort(int[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private void quickSort(int[] data, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        int pivot = data[mid];

        while(left <= right){
            while(left <= right && data[left] <= pivot){
                left++;
            }
            while(left <= right && data[right] > pivot){
                right--;
            }
            if(left <= right){
                swap(data, left, right);
            }
        }

        quickSort(data, left, mid - 1);
        quickSort(data, mid + 1, right);
    }

    private void swap(int[] data, int num1, int num2) {
        int tmp = data[num1];
        data[num1] = data[num2];
        data[num2] = tmp;
    }
}