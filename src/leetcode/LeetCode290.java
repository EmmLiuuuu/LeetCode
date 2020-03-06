package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode290 {

    public boolean wordPattern(String pattern, String str) {
        String[] splits = str.split(" ");
        if (splits.length != pattern.length()) {
            return false;
        }
        String[] pattern2Str = new String[26];
        Set<String> str2Pattern = new HashSet<>();

        for (int i = 0; i < pattern.length(); i++) {
            int c = pattern.charAt(i) - 'a';
            if (pattern2Str[c] == null) {
                if (str2Pattern.contains(splits[i])) {
                    return false;
                }
                str2Pattern.add(splits[i]);
                pattern2Str[c] = splits[i];
            } else {
                if (!splits[i].equals(pattern2Str[c])) {
                    return false;
                }
            }
        }
        return true;
    }
}
