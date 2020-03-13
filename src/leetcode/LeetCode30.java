package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * barfoothefoobarman
 * ["foo","bar"]
 */
public class LeetCode30 {

    public static void main(String[] args) {
        System.out.println(new LeetCode30().findSubstring("barfoofoobar", new String[]{"bar", "foo"}));
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        int one_word = words[0].length();
        int word_num = words.length;
        //记录words内所有字符的个数
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < one_word; i++) {
            int left = i, right = i, count = 0;
            //tmp_map记录的是所有按照one_word长度截取的子字符串个数
            HashMap<String, Integer> tmp_map = new HashMap<>();
            while (right + one_word <= s.length()) {
                //截取子字符串
                String w = s.substring(right, right + one_word);
                //right往前走
                right += one_word;
                //如果不存在的话可以直接跳过了
                if (!map.containsKey(w)) {
                    count = 0;
                    //左指针也要往前走
                    left = right;
                    //清空临时map
                    tmp_map.clear();
                } else {
                    //如果存在的话，存入w
                    tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                    //记录当前匹配的数量
                    count++;
                    //如果有重复的情况，滑动窗口的左边开始往右边移动
                    while (tmp_map.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
                        //取左边子串
                        String t_w = s.substring(left, left + one_word);
                        //把重复的计数-1
                        count--;
                        //因为不在窗口内了，需要将计数值-1
                        tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                        //窗口左侧往前移
                        left += one_word;
                    }
                    //如果全部找到了，记录左侧位置
                    if (count == word_num) {
                        res.add(left);
                    }
                }
            }
        }
        return res;
    }

    //hashMap暴力
    public List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        int one_word = words[0].length();
        int word_num = words.length;
        int all_len = one_word * word_num;
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < s.length() - all_len + 1; i++) {
            String tmp = s.substring(i, i + all_len);
            HashMap<String, Integer> tmp_map = new HashMap<>();
            for (int j = 0; j < all_len; j += one_word) {
                String w = tmp.substring(j, j + one_word);
                tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
            }
            if (map.equals(tmp_map)) {
                res.add(i);
            }
        }
        return res;
    }
}
