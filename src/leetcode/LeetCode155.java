package leetcode;

import java.util.LinkedList;

public class LeetCode155 {

    private int min = Integer.MAX_VALUE;
    private LinkedList<Integer> stack = new LinkedList<Integer>();
    //简单做法，另外一种聪明做法是在push数据的时候把当前最小值也push进去，保存最小值状态，每次操作都是两个两个操作

    public void push(int x) {
        stack.push(x);
        if (x < min) {
            min = x;
        }
    }

    public void pop() {
        int temp = stack.pop();
        if (temp == min) {
            min = Integer.MAX_VALUE;
            for (int i : stack) {
                if (i < min) {
                    min = i;
                }
            }
        }
    }

    public int top() {
        return stack.getFirst();
    }

    public int getMin() {
        return min;
    }
}
