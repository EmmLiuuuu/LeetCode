package leetcode;

import java.util.Arrays;

public class LeetCode88 {

    public static void main(String[] args) {
        new LeetCode88().merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            int pos = Arrays.binarySearch(nums1, 0, m, nums2[i]);
            if (pos < 0) {
                pos = -pos - 1;
            }
            if (pos < m) {
                System.arraycopy(nums1, pos, nums1, pos + 1, m - pos);
            }
            nums1[pos] = nums2[i];
            m++;
        }
    }
}
