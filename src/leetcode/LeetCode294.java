package leetcode;

import java.util.HashMap;

/**
 * You are playing the following Flip Game with your friend:
 * Given a string that contains only these two characters: + and -,
 * you and your friend take turns to flip two consecutive "++" into "--".
 * The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * <p>
 * Write a function to determine if the starting player can guarantee a win.
 * <p>
 * Example:
 * <p>
 * Input: s = "++++"
 * Output: true
 * Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 * Follow up:
 * Derive your algorithm's runtime complexity.
 */
public class LeetCode294 {

    private final HashMap<String, Boolean> map = new HashMap<>(8);

    public boolean canWin(String s) {
        //备忘录优化
        Boolean canWin = map.get(s);
        if (canWin != null) {
            return canWin;
        }
        for (int i = 1; i < s.length(); i++) {
            //当将当前位置的++改成--后，另外一名玩家赢不了，那就返回true
            if (s.charAt(i - 1) == '+' && s.charAt(i) == '+' &&
                    !canWin(s.substring(0, i - 1) + "--" + s.substring(i + 1))) {
                map.put(s, true);
                return true;
            }
        }
        map.put(s, false);
        return false;
    }
}
