package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LeetCode118 {

    /*
        生成杨辉三角
     */

    private static final List<Integer> one = Collections.singletonList(1);
    private static final List<Integer> two = Arrays.asList(1, 1);

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        if (numRows == 1) {
            result.add(one);
            return result;
        } else if (numRows == 2) {
            result.add(one);
            result.add(two);
            return result;
        } else if (numRows == 0) {
            return result;
        }

        result.add(one);
        result.add(two);
        for (int i = 2; i < numRows; i++) {
            List<Integer> rowList = new ArrayList<>(i + 1);
            rowList.add(1);

            List<Integer> lastList = result.get(i - 1);
            for (int j = 1; j < i; j++) {
                rowList.add(lastList.get(j - 1) + lastList.get(j));
            }
            rowList.add(1);
            result.add(rowList);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(generate(5));
    }
}
