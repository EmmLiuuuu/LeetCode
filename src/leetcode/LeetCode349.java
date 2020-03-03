package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode349 {

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int n : nums1) {
            set1.add(n);
        }
        for (int n : nums2) {
            set2.add(n);
        }
        set1.retainAll(set2);
        int[] result = new int[set1.size()];
        int i = 0;
        for (int n : set1) {
            result[i++] = n;
        }
        return result;
    }
}
