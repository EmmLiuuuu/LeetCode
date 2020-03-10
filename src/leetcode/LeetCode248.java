package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 * <p>
 * For example,
 * Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
 * <p>
 * Note:
 * Because the range might be a large number, the low and high numbers are represented as string.
 */
public class LeetCode248 {

    public int strobogrammaticInRange(String low, String high) {
        LeetCode247 leetCode247 = new LeetCode247();
        List<String> list = new ArrayList<>(high.length() - low.length());
        for (int i = low.length(); i <= high.length(); i++) {
            list.addAll(leetCode247.findStrobogrammatic(i));
        }
        int count = 0;
        for (String s : list) {
            if (s.compareTo(low) >= 0 && s.compareTo(high) <= 0) {
                count++;
            }
        }
        return count;
    }
}
