package leetcode;

import java.util.Arrays;

public class LeetCode27 {

    /**
     * https://leetcode-cn.com/problems/remove-element/
     */
    static class Solution {
        public int removeElement(int[] nums, int val) {
            int length = nums.length;
            for (int i = 0; i < length; i++) {
                if (nums[i] == val) {

                    for (int j = i; j < length - 1 && j + 1 != nums.length; j++) {
                        nums[j] = nums[j + 1];
                    }

                    --length;
                    --i;
                }
            }

            return length;
        }

        public int removeElement_better(int[] nums, int val) {
            int j = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != val) {
                    nums[j++] = nums[i];
                }
            }
            return j;
        }

        public int better1(int[] nums, int val) {
            int i = 0;
            int n = nums.length;
            while (i < n) {
                if (nums[i] == val) {
                    nums[i] = nums[n - 1];
                    // reduce array size by one
                    n--;
                } else {
                    i++;
                }
            }
            return n;

            /**
             *             作者：LeetCode
             *             链接：https://leetcode-cn.com/problems/remove-element/solution/yi-chu-yuan-su-by-leetcode/
             *             来源：力扣（LeetCode）
             *             著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
             */
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {3,2,2,3};
        System.out.println(new Solution().removeElement(array, 3));

        System.out.println(Arrays.toString(array));
    }
}
