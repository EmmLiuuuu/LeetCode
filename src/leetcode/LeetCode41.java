package leetcode;

/**
 * Hard
 */
public class LeetCode41 {

    /**
     * 解法1：
     * 利用桶排序，将值为n+1的数放置在数组下标为n的位置上，大于数组长度、0、小于0的数字直接抛弃，然后便利排序过后的数组，找到第一个不符合
     * 的下标，答案即为下标+1
     *
     * @param nums 传入待处理的数组
     * @return 答案
     */
    public static int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    /**
     * 解法2：
     * 索引作为哈希表
     * 剔除非正数，大于数组长度的数，将其置换为1
     * 检查剩下的数字，将对应下标位置的数字置为负数
     * 检查处理过后的数组，找到第一个 >0 的下标，结果即为下标
     * 其中，将值为数组length的数字标志位挪到下标为0处
     *
     * @param nums 传入待处理的数组
     * @return 结果
     */
    public static int firstMissingPositive1(int[] nums) {

        //计算1的个数
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            }
        }

        //如果没有1，那么直接返回1，1即为最小正数
        if (count == 0) {
            return 1;
        }

        //如果数组长度<2且里面的值为1，那么直接返回2
        if (nums.length < 2) {
            return 2;
        }

        //剔除步骤，将无用的数字全部置为1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) {
                nums[i] = 1;
            }
        }

        //将对应下标的数字置为负数
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i]) == nums.length) {
                nums[0] = -Math.abs(nums[0]);
            } else {
                nums[Math.abs(nums[i])] = -Math.abs(nums[Math.abs(nums[i])]);
            }
        }

        //从下标1开始检查，找到第一个大于零的下标，找到后直接返回下标数值
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }

        //最后检查数值n（与数组长度数值一致）是否存在，如果不存在，直接返回n
        if (nums[0] > 0) {
            return nums.length;
        }

        //如果存在，返回n+1
        return nums.length + 1;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive1(new int[]{3, 4, -1, 1}));
    }
}
