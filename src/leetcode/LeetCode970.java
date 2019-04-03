package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCode970 {

    public static List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>(bound);

        for (int i = 0; i < 18; i++) {
            int powX = (int) Math.pow(x, i);
            if (powX >= bound) {
                break;
            }
            for (int j = 0; j < 18; j++) {
                int temp = powX + ((int) Math.pow(y, j));
                if (temp > bound) {
                    break;
                }
                set.add(temp);
            }
        }
        return set.parallelStream().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(powerfulIntegers(4, 40, 100));
    }
}
