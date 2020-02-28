package leetcode;

import java.util.Arrays;

public class LeetCode238 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 0})));
    }

    //answer[i]的左边所有数的乘积 * answer[i]的右边所有数的乘积即为当前位置的答案
    public static int[] productExceptSelf(int[] nums) {

        // The length of the input array
        int length = nums.length;

        // Final answer array to be returned
        int[] answer = new int[length];

        // answer[i] contains the product of all the elements to the left
        // Note: for the element at index '0', there are no elements to the left,
        // so the answer[0] would be 1
        answer[0] = 1;
        //此时，answer[i]为i的左边所有数乘积
        for (int i = 1; i < length; i++) {

            // answer[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiplying it with nums[i - 1] would give the product of all
            // elements to the left of index 'i'
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R contains the product of all the elements to the right
        // R是当前位置右边所有数的乘积
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so the R would be 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {

            // For the index 'i', R would contain the
            // product of all elements to the right. We update R accordingly
            answer[i] = answer[i] * R;
            R *= nums[i];
        }

        return answer;
    }

    public static int[] productExceptSelf1(int[] nums) {
        int[] l = new int[nums.length];
        int[] r = new int[nums.length];
        int[] result = new int[nums.length];
        l[0] = r[nums.length - 1] = 1;
        for (int i = 1; i < nums.length; i++) {
            l[i] = l[i - 1] * nums[i - 1];
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            r[i] = r[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            result[i] = l[i] * r[i];
        }

        return result;
    }
}
