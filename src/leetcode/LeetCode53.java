package leetcode;

public class LeetCode53 {

    //https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode/
    public int maxSubArray(int[] nums) {
        int temp = 0;
        int max = Integer.MIN_VALUE;
        //贪心
        for (int n : nums) {
            temp += n;
            //记录当前最优解
            max = Math.max(temp, max);
            if (temp < 0) {
                //保持temp的最优
                temp = 0;
            }
        }

        return max;
    }

    //分治法
    public int maxSubArray1(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    //贪心2
    public int maxSubArray2(int[] nums) {
        int curSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //取当前步骤中的最优解
            curSum = Math.max(nums[i], curSum + nums[i]);
            //记录整体最优解
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }

    public int maxSubArray3(int[] nums) {
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > 0) {
                //修改数组跟踪当前位置的最大和。
                nums[i] += nums[i - 1];
            }
            //下一步是在知道当前位置的最大和后更新全局最大和
            maxSum = Math.max(maxSum, nums[i]);
        }

        return maxSum;
    }

    //分治法，将问题分解为3个子问题，计算左边最大值，计算右边最大值，计算中间最大值
    public int helper(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int mid = start + (end - start) / 2;
        int cross = cross(nums, start, end, mid);
        int left = helper(nums, start, mid);
        int right = helper(nums, mid + 1, end);

        return Math.max(cross, Math.max(left, right));
    }

    public int cross(int[] nums, int left, int right, int p) {
        if (left == right) {
            return nums[left];
        }
        int crossSum = 0;
        int leftMaxSum = Integer.MIN_VALUE;

        for (int i = p; i >= left; i--) {
            crossSum += nums[i];
            leftMaxSum = Math.max(leftMaxSum, crossSum);
        }

        crossSum = 0;
        int rightMaxSum = Integer.MIN_VALUE;
        for (int i = p + 1; i <= right; i++) {
            crossSum += nums[i];
            rightMaxSum = Math.max(rightMaxSum, crossSum);
        }

        return leftMaxSum + rightMaxSum;
    }
}
