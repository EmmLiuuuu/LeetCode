package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCode15 {

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        if (nums.length <= 2) {
            return new ArrayList<>(result);
        }
        Arrays.sort(nums);
        int end = nums.length;
        for(int start = 0; start < end - 2; start++) {
            if (nums[start] + nums[start+1] + nums[start + 2] > 0) break;
            if(nums[end - 1] + nums[end - 2] + nums[start] < 0) continue;

            int i = start + 1;
            int j = end - 1;
            while(i < j) {
                List<Integer> temp = new ArrayList<>();
                if (nums[start] + nums[i] + nums[j] == 0) {
                    temp.add(nums[start]);
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    result.add(temp);
                    i++;
                    j--;
                } else if (nums[start] + nums[i] + nums[j] > 0) {
                    j--;
                } else {
                    i++;
                }
            }
        }
        return result.parallelStream().collect(Collectors.toList());
    }

    public List<List<Integer>> threeSum1(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>(0);
        }

        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        if (nums[0] <= 0 && nums[nums.length - 1] >= 0) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    return result;
                }
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int left = i + 1;
                int right = nums.length - 1;

                while (left < right) {
                    int sum = nums[left] + nums[right] + nums[i];
                    if (sum > 0) {
                        right--;
                    } else if (sum < 0) {
                        left++;
                    } else {
                        List<Integer> temp = new ArrayList<>(3);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        temp.add(nums[i]);
                        result.add(temp);
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }

        return result;
    }
}
