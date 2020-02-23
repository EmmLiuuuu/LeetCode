package leetcode;

public class LeetCode4 {

    //用归并排序的思想合并两个数组，然后取中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] merge = new int[nums1.length + nums2.length];

        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                merge[k++] = nums1[i++];
            } else {
                merge[k++] = nums2[j++];
            }
        }

        if (i < nums1.length) {
            for (; i < nums1.length; i++) {
                merge[k++] = nums1[i];
            }
        } else if (j < nums2.length) {
            for (; j < nums2.length; j++) {
                merge[k++] = nums2[j];
            }
        }

        if (merge.length % 2 == 0) {
            return (double) (merge[merge.length / 2] + merge[merge.length / 2 - 1]) / 2;
        } else {
            return merge[merge.length / 2];
        }
    }
}
