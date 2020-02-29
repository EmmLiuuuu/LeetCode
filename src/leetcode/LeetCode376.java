package leetcode;

public class LeetCode376 {

    public static void main(String[] args) {
        System.out.println(new LeetCode376().wiggleMaxLength2(new int[]{1, 7, 4, 9, 2, 5}));
    }

    /**
     * up[i]存储结尾为第i个元素，最后一个为上升的摆动子序列最大长度
     * down[i]存储结尾为第i个元素，最后一个为下降的摆动子序列最大长度
     * 当 j < i, nums[i] > nums[j] 时，up[i] = max(up[i], down[j] + 1)
     * 当 j < i, nums[i] < nums[j] 时，down[i] = max(down[i], up[j] + 1)
     *
     * @param nums 摆动序列
     * @return 最长子序列的长度
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                }
            }
        }

        return 1 + Math.max(up[nums.length - 1], down[nums.length - 1]);
    }

    /**
     * 算法
     * <p>
     * 数组中的任何元素都对应下面三种可能状态中的一种：
     * <p>
     * 上升的位置，意味着 nums[i] > nums[i - 1]
     * 下降的位置，意味着 nums[i] < nums[i - 1]
     * 相同的位置，意味着 nums[i] == nums[i - 1]
     * 更新的过程如下：
     * <p>
     * 如果 nums[i] > nums[i−1] ，意味着这里在摆动上升，前一个数字肯定处于下降的位置。
     * 所以 up[i] = down[i-1] + 1， down[i] 与 down[i−1] 保持相同。
     * <p>
     * 如果 nums[i] < nums[i−1] ，意味着这里在摆动下降，前一个数字肯定处于上升的位置。
     * 所以 down[i] = up[i-1] + 1， up[i] 与 up[i−1] 保持不变。
     * <p>
     * 如果 nums[i] == nums[i−1] ，意味着这个元素不会改变任何东西因为它没有摆动。
     * 所以 down[i] 与 up[i] 与 down[i−1] 和 up[i−1] 都分别保持不变。
     * <p>
     * 最后，我们可以将 up[length-1] 和 down[length-1] 中的较大值作为问题的答案，其中 length 是给定数组中的元素数目。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/wiggle-subsequence/solution/bai-dong-xu-lie-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * up[i] 表示从0到i，最后一个为上升的摆动子序列的最大值
     * down[i] 表示从0到i，最后一个为下降的摆动子序列的最大值
     * up[i] 的值为down[i - 1] + 1
     * down[i] 的值为up[i - 1] + 1
     *
     * @param nums 摆动序列
     * @return 最长子序列的长度
     */
    public int wiggleMaxLength1(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = down[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            //当为上升时
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                //下降
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                //相等
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }

        return Math.max(up[nums.length - 1], down[nums.length - 1]);
    }

    public int wiggleMaxLength2(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int up = 1;
        int down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
            //当相等时，down，up不变，相当于
            //up[i] = up[i - 1];
            //down[i] = down[i - 1];
        }

        return Math.max(up, down);
    }

    /**
     * 子序列不要求连续（不是连续子串）
     * 找交替上升下降的子序列，相当于只要符合条件的时候+1即可
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength3(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int prevdiff = nums[1] - nums[0];
        int count = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < nums.length; i++) {
            //当前状态
            int diff = nums[i] - nums[i - 1];
            //符合条件
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                //+1
                count++;
                //更新前一个状态
                prevdiff = diff;
            }
        }
        return count;
    }
}
