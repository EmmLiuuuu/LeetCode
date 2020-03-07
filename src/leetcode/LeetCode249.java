package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 * <p>
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 * <p>
 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
 * Return:
 * <p>
 * [
 * ["abc","bcd","xyz"],
 * ["az","ba"],
 * ["acef"],
 * ["a","z"]
 * ]
 */
public class LeetCode249 {

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>(strings.length);
        for (String str : strings) {
            if (str.length() == 1) {
                map.computeIfAbsent(null, key -> new ArrayList<>(8)).add(str);
                continue;
            }
            StringBuilder builder = new StringBuilder();
            builder.append(str.length()).append('-');
            for (int i = 0; i < str.length() - 1; i++) {
                //计算单词间的间隔，只要间隔一致，那么一定符合题意
                builder.append((str.charAt(i + 1) - 'a') % 26 - (str.charAt(i) - 'a') % 26);
            }
            map.computeIfAbsent(builder.toString(), key -> new ArrayList<>(8)).add(str);
        }
        return new ArrayList<>(map.values());
    }

}
