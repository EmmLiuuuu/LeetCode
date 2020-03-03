package leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class LeetCode350 {

    //输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>(nums1.length);
        for (int n : nums1) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int index = 0;
        for (int n : nums2) {
            Integer c = map.get(n);
            if (c != null) {
                if (c > 0) {
                    nums1[index++] = n;
                    map.put(n, c - 1);
                }
            }
        }
        return Arrays.copyOfRange(nums1, 0, index);
    }
}
