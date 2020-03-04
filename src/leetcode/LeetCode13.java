package leetcode;

import java.util.HashMap;

public class LeetCode13 {

    public int romanToInt(String s) {
        int result = 0;
        HashMap<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("IV", 4);
                put("IX", 9);
                put("XL", 40);
                put("XC", 90);
                put("CD", 400);
                put("CM", 900);
            }
        };
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>() {
            {
                put("I", 1);
                put("V", 5);
                put("X", 10);
                put("L", 50);
                put("C", 100);
                put("D", 500);
                put("M", 1000);
            }
        };
        for (String key :
                map.keySet()) {
            if (s.contains(key)) {
                result += map.get(key);
                s = s.replace(key, "");
            }
        }

        for (int i = 0; i < s.length(); i++)
            result += hashMap.get(String.valueOf(s.charAt(i)));
        return result;
    }
}
