package leetcode;

public class LeetCode278 {

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    //leetcode函数，为了不报错，加上这个东西
    private boolean isBadVersion(int n) {
        return true;
    }
}
