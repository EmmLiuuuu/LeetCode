package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode324 {

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void quickSelect(int[] nums, int start, int end, int n) {
        int temp = nums[start];
        int left = start, right = end;
        while (left < right) {
            while (left < right && nums[right] >= temp) {
                right--;
            }
            while (left < right && nums[left] <= temp) {
                left++;
            }
            swap(nums, left, right);
        }
        //将棋子插在合适的位置
        swap(nums, start, left);

        if (left - 1 >= n) {
            quickSelect(nums, start, left - 1, n);
        } else if (left + 1 <= n) {
            quickSelect(nums, left + 1, end, n);
        }
    }

    public static void wiggleSort1(int[] nums) {
        //找到中点
        quickSelect(nums, 0, nums.length - 1, nums.length / 2);
        int mid = nums[nums.length / 2];
        //three-way-partition,作用是将比mid小的数归于左边，比mid大的数归于右边
        //i，左边index，k，右边index，p当前遍历index
        int i = 0, j = 0, k = nums.length - 1;
        while (j < k) {
            if (nums[j] < mid) {
                swap(nums, i, j);
                i++;
                j++;
            } else if (nums[j] > mid) {
                swap(nums, j, k);
                k--;
            } else {
                j++;
            }
        }

        int midIndex = nums.length % 2 == 1 ? nums.length / 2 + 1 : nums.length / 2;
        List<Integer> tmp1 = new ArrayList<>();
        List<Integer> tmp2 = new ArrayList<>();
        for (i = 0; i < midIndex; i++) {
            tmp1.add(nums[i]);
        }
        for (i = midIndex; i < nums.length; i++) {
            tmp2.add(nums[i]);
        }
        for (i = 0; i < tmp1.size(); i++) {
            nums[i * 2] = tmp1.get(tmp1.size() - 1 - i);
        }
        for (i = 0; i < tmp2.size(); i++) {
            nums[i * 2 + 1] = tmp2.get(tmp2.size() - 1 - i);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 1, 1, 6, 4};
        wiggleSort1(nums);

    }

    /**
     * 先排序再穿插
     * O(nlogn)+O(n)=O(nlogn)
     * https://leetcode-cn.com/problems/wiggle-sort-ii/solution/javaxiang-xi-ti-jie-shuo-ming-by-heator/
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        //排序
        Arrays.sort(nums);
        int len = nums.length;
        int[] smaller = new int[len % 2 == 0 ? len / 2 : (len / 2 + 1)], bigger = new int[len / 2];
        //复制
        System.arraycopy(nums, 0, smaller, 0, smaller.length);
        System.arraycopy(nums, smaller.length, bigger, 0, len / 2);

        //穿插, 将smaller数组逆转，bigger数组逆转，从逆转位置开始穿插
        int i = 0;
        for (; i < len / 2; i++) {
            nums[2 * i] = smaller[smaller.length - 1 - i];
            nums[2 * i + 1] = bigger[len / 2 - 1 - i];
        }
        if (len % 2 != 0) {
            nums[2 * i] = smaller[smaller.length - 1 - i];
        }
    }
}
