package algorithm;

import java.util.LinkedList;

public class FindFirstBiggerNum {

    public int[] findFirstBiggerNum(int[] nums) {
        //stack存的是索引
        LinkedList<Integer> stack = new LinkedList<>();
        int i = 0;
        //res存储结果
        int[] res = new int[nums.length];
        while (i < nums.length) {
            //当前数比栈顶元素小时，入栈
            if (stack.isEmpty() || nums[i] <= nums[stack.getLast()]) {
                stack.addLast(i++);
            } else {
                //当找到比栈顶元素大的情况，移除栈顶并赋值（找到了第一个比栈顶元素大的元素）
                res[stack.removeLast()] = nums[i];
            }
        }
        //当栈不为空时，全部赋值为-1，找不到第一个比他大的元素
        while (!stack.isEmpty()) {
            res[stack.removeLast()] = -1;
        }
        return res;
    }
}
