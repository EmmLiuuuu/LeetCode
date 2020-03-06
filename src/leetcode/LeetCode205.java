package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode205 {

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            Character mapChar = map.get(sChar);
            if (mapChar != null) {
                if (!mapChar.equals(tChar)) {
                    return false;
                }
            } else {
                if (map.containsValue(tChar)) {
                    return false;
                }
                map.put(sChar, tChar);
            }
        }
        return true;
    }

    //映射
    public boolean isIsomorphic1(String s, String t) {
        int n = s.length();
        int[] mapS = new int[128];
        int[] mapT = new int[128];
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            //当前的映射值是否相同
            if (mapS[c1] != mapT[c2]) {
                return false;
            } else {
                //是否已经修改过，修改过就不需要再处理
                if (mapS[c1] == 0) {
                    mapS[c1] = i + 1;
                    mapT[c2] = i + 1;
                }
            }
        }
        return true;
    }
}
