package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode442 {

    public static void main(String[] args) {
        System.out.println(findDuplicates(new int[] {4,3,2,7,8,2,3,1}));
    }

    public static List<Integer> findDuplicates(int[] nums) {

//        List<Integer> list = new ArrayList<>();
//        for (int i = 1; i <= nums.length; i++) {
//            while (nums[i - 1] != i) {
//                int temp = nums[nums[i - 1] - 1];
//                if (nums[nums[i - 1] - 1] == nums[i - 1]) {
//                    boolean flag = true;
//                    for (Integer n : list) {
//                        if (nums[i - 1] == n) {
//                            flag = false;
//                            break;
//                        }
//                    }
//                    if (flag) {
//                        list.add(nums[i - 1]);
//                    }
//                    break;
//                }
//                nums[nums[i - 1] - 1] = nums[i - 1];
//                nums[i - 1] = temp;
//            }
//        }
//        return list;

        List<Integer> list = new ArrayList<>(nums.length);
        int idx = 0;
        while (idx < nums.length) {
            if (nums[idx] == -1 || nums[idx] == idx + 1) {
                idx++;
            } else {
                if (nums[nums[idx] - 1] == nums[idx]) {
                    list.add(nums[idx]);
                    nums[idx] = -1;
                    idx++;
                } else {
                    int temp = nums[nums[idx] - 1];
                    nums[nums[idx] - 1] = nums[idx];
                    nums[idx] = temp;
                }
            }
        }
        return list;
    }
}
