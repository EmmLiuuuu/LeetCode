package leetcode;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * <p>
 * Example 1:
 * <p>
 * Input:  "69"
 * Output: true
 * Example 2:
 * <p>
 * Input:  "88"
 * Output: true
 * Example 3:
 * <p>
 * Input:  "962"
 * Output: false
 */
public class LeetCode246 {

    public boolean isStrobogrammatic(String num) {
        int i = 0, j = num.length() - 1;
        while (i <= j) {
            if (num.charAt(i) == num.charAt(j)) {
                if (num.charAt(i) != '1' || num.charAt(i) != '8' || num.charAt(i) != '0') {
                    return false;
                }
            } else {
                if ((num.charAt(i) != '6' || num.charAt(j) != '9') && (num.charAt(i) != '9' || num.charAt(j) != '6')) {
                    return false;
                }
            }
            i++;
            j--;
        }
        return true;
    }
}
