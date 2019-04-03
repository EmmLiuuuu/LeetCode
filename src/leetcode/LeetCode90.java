package leetcode;

import java.util.*;

public class LeetCode90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int k = 0; k <= nums.length; k++){
            backtrack(res, new ArrayList<>(), nums, 0, k);
        }
        return res;
    }
    private void backtrack(List<List<Integer>> res, List<Integer> temp, int[] nums, int start, int k){
        if(k == 0){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = start; i < nums.length; i++){
            if(i != start && nums[i] == nums[i-1]) continue;
            temp.add(nums[i]);
            backtrack(res, temp, nums, i+1, --k);
            temp.remove(temp.size()-1);
        }
    }
}
