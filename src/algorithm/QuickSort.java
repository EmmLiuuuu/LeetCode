package algorithm;

import java.util.LinkedList;

public class QuickSort {

    public int partition(int[] array, int left, int right) {
        int temp = array[left];

        return swapAndFindPivot(array, left, right, temp);
    }

    public void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right);
            quickSort(array, left, pivot - 1);
            quickSort(array, pivot + 1, right);
        }
    }

    public void quickSort1(int[] array, int left, int right) {
        if (left < right) {
            LinkedList<Integer> stack = new LinkedList<>();
            stack.push(left);
            stack.push(right);

            while (!stack.isEmpty()) {
                int r = stack.pop();
                int l = stack.pop();

                int pivot = partitionMedianOfThree(array, l, r);
                if (l < r) {
                    if (pivot + 1 < r) {
                        stack.push(pivot + 1);
                        stack.push(r);
                    }

                    if (pivot - 1 > l) {
                        stack.push(l);
                        stack.push(pivot - 1);
                    }
                }
            }
        }
    }

    public int partitionMedianOfThree(int[] array, int left, int right) {
        int mid = left + (right - left) / 2;
        int temp = array[left];
        if (array[left] > array[right]) {
            array[left] = array[right];
            array[right] = temp;
        }
        temp = array[right];
        if (array[right] < array[mid]) {
            array[right] = array[mid];
            array[mid] = temp;
        }
        temp = array[left];
        if (array[left] < array[mid]) {
            array[left] = array[mid];
            array[mid] = temp;
        }

        temp = array[left];
        return swapAndFindPivot(array, left, right, temp);
    }

    private int swapAndFindPivot(int[] array, int left, int right, int temp) {
        while (left < right) {

            //这个代码段的意思是右边找到比temp小的，左边找到比temp大的，然后交换
            {
                while (left < right && array[right] >= temp) {
                    right--;
                }
                array[left] = array[right];

                while (left < right && array[left] <= temp) {
                    left++;
                }
                array[right] = array[left];
            }
        }

        array[left] = temp;
        return left;
    }
}
