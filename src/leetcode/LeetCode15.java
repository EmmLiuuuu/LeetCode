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
                } else if(nums[start] + nums[i] + nums[j] > 0) {
                    j--;
                } else {
                    i++;
                }
            }
        }
        return result.parallelStream().collect(Collectors.toList());
    }
}
