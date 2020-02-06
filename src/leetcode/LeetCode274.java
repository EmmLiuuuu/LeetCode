package leetcode;

import java.util.Arrays;

public class LeetCode274 {

    //解题思路，先排序，然后找到最大的正方形

    /*
    普通排序
    https://leetcode-cn.com/problems/h-index/solution/hzhi-shu-by-leetcode/
    我们想象一个直方图，其中 xx 轴表示文章，yy 轴表示每篇文章的引用次数。如果将这些文章按照引用次数降序排序并在直方图上进行表示，那么直方图上的最大的正方形的边长 hh 就是我们所要求的 hh。
     */
    public static int hIndex(int[] citations) {
        Arrays.sort(citations);

        int i = 0;
        while ((i < citations.length && citations[citations.length - 1 - i] > i)) {
            i++;
        }

        return i;
    }

    //利用计数排序的思想
    //把数值超过数组长度的设置值为数组长度
    public static int hIndex1(int[] citations) {
        int[] countArray = new int[citations.length + 1];

        //计数
        for (int num :
                citations) {
            countArray[Math.min(num, citations.length)] += 1;
        }

        int h = citations.length;
        //根据计数结果找正方形边界，逆过来找
        for (int sum = countArray[h]; h > sum; sum += countArray[h]) {
            --h;
        }

        return h;
    }

    public static void main(String[] args) {
        System.out.println(hIndex(new int[]{100}));
    }
}
