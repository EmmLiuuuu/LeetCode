package leetcode;

public class LeetCode88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        int index = 0;
        for (int i = 0; i < m && index < n; i++) {
            if (nums1[i] > nums2[index]) {
                for (int j = m; j >= i + 1; j--) {
                    nums1[j] = nums1[j - 1];
                }
                m++;
                nums1[i] = nums2[index++];
            }
        }

        for (int i = m; i < nums1.length; i++) {
            nums1[i] = nums2[index++];
        }
    }
}
