package leetcode;

public class LeetCode152 {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[] {-2,3,-4}));
    }

    //保证负数有变成正数的可能
    public static int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int temp1 = 1, temp2 = 1;

        int pro1, pro2;
        for (int num : nums) {
            pro1 = num * temp1;
            pro2 = num * temp2;

            temp1 = Math.max(num, Math.max(pro1, pro2));
            temp2 = Math.min(num, Math.min(pro1, pro2));

            max = Math.max(max, Math.max(temp1, temp2));
        }

        return max;
    }
}
