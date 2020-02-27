package leetcode;

public class LeetCode209 {

    public static void main(String[] args) {
        System.out.println(new LeetCode209().minSubArrayLen(7, new int[]{1, -100, 3, 2, 3, 4}));
    }

    //[2,3,1,2,4,3]  7
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums[0] >= s) {
            return 1;
        }
        int i = 0;
        int j = 1;

        int minLength = Integer.MAX_VALUE;
        int count = nums[i];
        while (j <= nums.length) {
            if (count < s) {
                if (j == nums.length) {
                    break;
                }
                if (nums[j] >= s) {
                    return 1;
                }
                if (nums[j] <= 0) {
                    j++;
                    i = j;
                    count = 0;
                    continue;
                }
                count += nums[j];
                j++;
            } else {
                minLength = Math.min(minLength, j - i);
                if (minLength == 1) {
                    return minLength;
                }
                count -= nums[i++];
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
