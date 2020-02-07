package leetcode;

public class LeetCode45 {


    //maxPosition为在[0, n]下标内可以跳跃到达的最大可达位置，end为上一次跳跃能到达的最远位置
    //当遍历指针到达end时，需要更新end的值为本次能跳跃到的最远处，并且step+=1，当end超过数组终点时，说明已经以最少的跳跃次数到达了终点
    public int jump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int step = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) {
                end = maxPosition;
                step++;
            }
        }

        return step;
    }

    //https://leetcode-cn.com/problems/jump-game-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-10/
    //先指定要找的位置，从头开始遍历，找到能跳到的最远的位置，不断更新要找的位置，直至到达数组起点
    public int jump1(int[] nums) {
        int position = nums.length - 1; //要找的位置
        int steps = 0;
        while (position != 0) { //是否到了第 0 个位置
            for (int i = 0; i < position; i++) {
                if (nums[i] >= position - i) {
                    position = i; //更新要找的位置
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
}
