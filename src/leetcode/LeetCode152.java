package leetcode;

public class LeetCode152 {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[] {-2,3,-4}));
    }

    //保证负数有变成正数的可能
    //imax表示以当前节点为终结节点的最大连续子序列乘积 imin表示以当前节点为终结节点的最小连续子序列乘积
    public static int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int iMax = 1, iMin = 1;

        int pro1, pro2;
        for (int num : nums) {
            pro1 = num * iMax;
            pro2 = num * iMin;

            iMax = Math.max(num, Math.max(pro1, pro2));
            iMin = Math.min(num, Math.min(pro1, pro2));

            max = Math.max(max, Math.max(iMax, iMin));
        }

        return max;
    }

    //imax表示以当前节点为终结节点的最大连续子序列乘积 imin表示以当前节点为终结节点的最小连续子序列乘积
    public static int maxProduct1(int[] nums) {
        int iMax = 1, iMin = 1, max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num < 0) {
                int temp = iMax;
                iMax = iMin;
                iMin = temp;
            }

            iMax = Math.max(iMax, iMax * num);
            iMin = Math.min(iMin, iMin * num);

            max = Math.max(iMax, max);
        }
        return max;
    }

    //https://leetcode-cn.com/problems/maximum-product-subarray/solution/zi-chuan-wen-ti-yong-dp-by-jeromememory/
    class Solution {
        public int maxProduct(int[] nums) {
            //dp_max[i] = Math.max(nums[i-1],dp_max[i-1]*nums[i-1])
            int[] dp_max = new int[nums.length + 1];
            int[] dp_min = new int[nums.length + 1];
            if (nums.length == 0) return 0;
            int max = Integer.MIN_VALUE;
            // 由于存在负数，所以需要维护两个数组
            // dp_max[i] 指的是以第 i 个数结尾的 乘积最大 的连续子序列
            // dp_min[i] 指的是以第 i 个数结尾的 乘积最小 的连续子序列
            dp_max[0] = 1;
            dp_min[0] = 1;
            for (int i = 1; i <= nums.length; i++) {
                // 如果数组的数是负数，那么会导致 max 变成 min，min 变成 max
                // 故需要交换dp
                if (nums[i - 1] < 0) {
                    int temp = dp_min[i - 1];
                    dp_min[i - 1] = dp_max[i - 1];
                    dp_max[i - 1] = temp;
                }
                dp_min[i] = Math.min(nums[i - 1], dp_min[i - 1] * nums[i - 1]);
                dp_max[i] = Math.max(nums[i - 1], dp_max[i - 1] * nums[i - 1]);
                max = Math.max(max, dp_max[i]);
            }
            return max;
        }
    }
}
