package algorithm;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int[] nums = new int[random.nextInt(100)];
            for (int k = 0; k < nums.length; k++) {
                nums[k] = random.nextInt(100);
            }
            HeapSort sort = new HeapSort();
            sort.sort(nums);
            System.out.println(Arrays.toString(nums));
        }
    }

    private void buildHeap(int[] nums, int length) {
        for (int i = 0; i < length; i++) {
            //先检查子节点是否比父节点大，是的话就交换
            if ((2 * i + 1) < length && nums[2 * i + 1] > nums[i]) {
                swap(nums, 2 * i + 1, i);
            }
            if ((2 * i + 2) < length && nums[2 * i + 2] > nums[i]) {
                swap(nums, 2 * i + 2, i);
            }
            //继续检查，直至调整为大根堆
            int tmp = i;
            while (tmp > 0 && (nums[(tmp - 1) / 2] < nums[tmp])) {
                swap(nums, (tmp - 1) / 2, tmp);
                //tmp指向父节点
                tmp = (tmp - 1) / 2;
            }
        }
    }

    private void adjustHeap(int[] nums, int length) {
        int heap = 0;
        int pre = -1;
        //当堆顶元素置换的时候，其实原本左右子树也是个子堆，只需要对受到影响的子堆进行调整就行了
        while (heap != pre) {
            pre = heap;
            int left = 2 * heap + 1;
            int right = 2 * heap + 2;

            if (left < length && nums[left] > nums[heap]) {
                heap = left;
            }
            if (right < length && nums[right] > nums[heap]) {
                heap = right;
            }

            swap(nums, pre, heap);
        }
    }

    //递归形式
    private void adjustHeap(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            adjustHeap(arr, largest, len);
        }
    }

    public void sort(int[] nums) {
        int length = nums.length;
        //先建堆
        buildHeap(nums, length);
        while (length > 0) {
            //再交换堆顶元素在数组最后的位置
            swap(nums, 0, --length);
            //调整子树
            adjustHeap(nums, length);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
