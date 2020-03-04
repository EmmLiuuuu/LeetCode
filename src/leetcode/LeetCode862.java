package leetcode;

import java.util.LinkedList;

public class LeetCode862 {

    public int shortestSubarray(int[] A, int K) {
        if (A.length == 0) {
            return -1;
        }
        //计算数组i位置的前缀和
        long[] sumArray = new long[A.length + 1];
        //i为0时，无前缀和，为0
        for (int i = 0; i < A.length; i++) {
            //累加计算前缀和
            sumArray[i + 1] = sumArray[i] + (long) A[i];
        }

        //最终答案为min(res, sumArray[y] - sumArray[x] >= k)
        int res = Integer.MAX_VALUE;
        LinkedList<Integer> queue = new LinkedList<>();
        //遍历前缀和
        for (int y = 0; y < sumArray.length; y++) {
            //将比当前位置前缀和大的下标删除(要想得出>=k的结果，肯定是与前面小的前缀和进行比较)
            while (!queue.isEmpty() && sumArray[y] <= sumArray[queue.getLast()]) {
                queue.removeLast();
            }
            //符合情况，计算结果
            while (!queue.isEmpty() && sumArray[y] - sumArray[queue.getFirst()] >= K) {
                //计算完成后删除，进行下一个结果的计算
                res = Math.min(res, y - queue.removeFirst());
            }
            //往双向队列中插入元素
            queue.addLast(y);
        }
        //如果res没有被计算过，返回-1
        return res > A.length ? -1 : res;
    }
}
