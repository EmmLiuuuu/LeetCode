package leetcode;

public class LeetCode275 {

    //还是想像成直方图，取面积最大的正方形

    public static int hIndex(int[] citations) {
        int h = 0;
        int left = 0;
        int right = citations.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int min = Math.min(citations[mid], citations.length);
            if (min == citations.length - mid) {
                return citations[mid];
            } else if (min < citations.length - mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return h;
    }

    public static void main(String[] args) {
        System.out.println(hIndex(new int[]{0, 1, 3, 5, 6}));
    }
}
