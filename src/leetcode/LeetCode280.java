package leetcode;

import java.util.Arrays;

public class LeetCode280 {

    /*
    Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

    For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
    ————————————————
    版权声明：本文为CSDN博主「小榕流光」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/qq508618087/article/details/50871819
     */

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 5, 6};
        new LeetCode280().wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    //只需要知道奇数位置的数比两边大，偶数位置的数比两边小，发现不符合的情况直接交换
    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i & 1) == 0) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
