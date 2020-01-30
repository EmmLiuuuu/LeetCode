package leetcode;

import util.PrintArray;

public class LeetCode189 {

    /**
     * 解法1：环形处理
     * 一开始我尝试以下标0为起始位置对数组进行处理，发现无法cover (nums.length % k == 0 && k != 1)的情况
     * 看了题解后，加多一层for循环，每个位置的数据都检查并处理
     *
     * @param nums 传入的数组
     * @param k    偏移多少位
     */
    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (nums.length == k || nums.length <= 1 || k == 0) {
            return;
        }

        int count = 0;
        for (int i = 0; count < nums.length; i++) {
            int index = i;
            int prev = nums[i];
            do {
                int next = (index + k) % (nums.length);
                int tmp = nums[next];
                nums[next] = prev;
                prev = tmp;
                index = next;
                count++;
            } while (index != i);
        }
    }

    /**
     * 解法2：反转
     * 先将整个数组翻转，然后翻转前k个数字，再翻转剩下的数字
     *
     * @param nums 传入的数组
     * @param k    偏移多少位
     */
    public static void rotate1(int[] nums, int k) {
        k = k % nums.length;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        rotate1(nums, 3);
        PrintArray.print(nums, nums.length);
    }
}
