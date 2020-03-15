package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: "code"
 * Output: false
 * Example 2:
 * <p>
 * Input: "aab"
 * Output: true
 * Example 3:
 * <p>
 * Input: "carerac"
 * Output: true
 * Hint:
 * <p>
 * Consider the palindromes of odd vs even length. What difference do you notice?
 * Count the frequency of each character.
 * If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
 */
public class LeetCode266 {

    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (!set.add(s.charAt(i))) {
                set.remove(s.charAt(i));
            }
        }
        return set.size() <= 1;
    }
}
