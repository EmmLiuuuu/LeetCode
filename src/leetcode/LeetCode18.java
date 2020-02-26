package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode18 {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return new ArrayList<>(0);
        }

        Arrays.sort(nums);
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {

            if (nums[i] + 3 * nums[i + 1] > target) {
                break;
            }

            if (nums[i] + 3 * nums[nums.length - 1] < target) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length; j++) {

                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[j] + nums[right] < target - nums[i]) {
                        left++;
                    } else if (nums[left] + nums[j] + nums[right] > target - nums[i]) {
                        right--;
                    } else {
                        List<Integer> temp = new ArrayList<>(4);
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        list.add(temp);
                        while (left < right && nums[left + 1] == nums[left]) {
                            left++;
                        }
                        while (left < right && nums[right - 1] == nums[right]) {
                            right--;
                        }

                        left++;
                        right--;
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        fourSum(new int[]{1, -2, -5, -4, -3, 3, 3, 5}, -11);
    }
}
