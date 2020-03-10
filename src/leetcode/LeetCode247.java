package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Find all strobogrammatic numbers that are of length = n.
 * <p>
 * For example,
 * Given n = 2, return ["11","69","88","96"].
 * <p>
 * Hint:
 * <p>
 * Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
 */
public class LeetCode247 {

    private final List<String> N1 = Arrays.asList(String.valueOf(0), String.valueOf(1), String.valueOf(8));
    private final List<String> EMPTY = Collections.singletonList("");

    public static void main(String[] args) {
        System.out.println(new LeetCode247().findStrobogrammatic(2));
        System.out.println(new LeetCode247().findStrobogrammatic(3));
        System.out.println(new LeetCode247().findStrobogrammatic(4));
        System.out.println(new LeetCode247().findStrobogrammatic(5));
    }

    public List<String> findStrobogrammatic(int n) {
        return findStrobogrammatic(n, n);
    }

    public List<String> findStrobogrammatic(int n, int m) {
        if (n == 0) {
            return EMPTY;
        }
        if (n == 1) {
            return N1;
        }
        List<String> list = findStrobogrammatic(n - 2, m);

        List<String> result = new ArrayList<>(list.size() * 5 + 4);
        for (String s : list) {
            //注意，最外层不得加0，0123不是数字
            if (n != m) {
                result.add("0" + s + "0");
            }
            result.add("1" + s + "1");
            result.add("8" + s + "8");
            result.add("6" + s + "9");
            result.add("9" + s + "6");
        }
        return result;
    }
}
