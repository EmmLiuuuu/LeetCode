package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode325 {

    /**
     * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
     * <p>
     * Example 1:
     * <p>
     * Given nums = [1, -1, 5, -2, 3], k = 3,
     * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
     * <p>
     * Example 2:
     * <p>
     * Given nums = [-2, -1, 2, 1], k = 1,
     * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
     * <p>
     * Follow Up:
     * Can you do it in O(n) time?
     * ————————————————
     * 版权声明：本文为CSDN博主「小榕流光」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/qq508618087/article/details/50811818
     */

    //暴力
    public int maxSubArrayLen(List<Integer> nums, int k) {
        int maxLength = 0;
        for (int i = 0; i < nums.size() - 1; i++) {
            int j = i + 1;
            int count = nums.get(i);
            while (j < nums.size()) {
                if (count == k) {
                    maxLength = Math.max(maxLength, j - i);
                }
                count += nums.get(j++);
            }
        }
        return maxLength;
    }

    /**
     * 解法：循环数组，用一个变量 sum 记录到目前为止所有数组的和，如果等于k则更新maxLength，再用一个 map 记录累加和的index，
     * 技巧：因为是求最长数组，所以只记录第一次的index，以后出现的位置靠后，就不记录了。如果sum在hashmap 中，
     * 表示当前位置去掉hashmap中记录的sum - k的 index 的差等于k,  用两个index的差更新max_len。
     * <p>
     * 首先假设这么一个场景，从下标为0到下标为100，和sum = 2000，假设我们要求的目标k=800，
     * 那么我们只要知道从0开始，最早出现的下标i，使得0到i之间的和为1200，这样就能保证i+1到1000的和为800，同时还能保证这个长度是以1000结尾的最大的长度
     * 建立一个Map，key存放的是和，value存放的是第一次出现该和的下标，后面如果再出现直接跳过。
     * <p>
     * 总结
     * 很多类似的变形题，比方说，一个数组中有奇数有偶数，求奇数和偶数个数相等的最长子数组的长度，这个就把奇数看作1，把偶数看作-1，求和为0的最长子数组的长度；
     * 再比方说，一个数组中有0,1,2，求0,1,2个数相等的最长子数组的长度，也是一样的，0还是0，把1看成1，2看成-1，最终叶转换为求和为0的最长子数组的长度。
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxSubArrayLen1(List<Integer> nums, int k) {
        int maxLength = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>(nums.size());
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
            if (sum == k) {
                //如果相等的话，更新maxLength
                maxLength = Math.max(maxLength, i + 1);
            }

            //找到sum-k出现的位置
            Integer integer = map.get(sum - k);
            if (integer != null) {
                //那么从sum-k首次出现的位置开始到i的和必定为k，且最长
                maxLength = Math.max(maxLength, i - integer);
            }

            //只记录第一次出现的位置
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLength;
    }
}
