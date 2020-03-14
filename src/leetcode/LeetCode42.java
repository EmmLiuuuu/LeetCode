package leetcode;

import java.util.LinkedList;

//https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
public class LeetCode42 {

    //按行计算
    public int trap4(int[] height) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int h : height) {
            if (h > max) {
                max = h;
            }
        }

        for (int i = 1; i <= max; i++) {
            //如果走了一遍都没有找到起点，那么直接break，避免扫描鹤立鸡群的情况
            boolean flag = true;
            boolean start = false;
            int temp = 0;
            for (int value : height) {
                if (start && value < i) {
                    temp += 1;
                }
                if (value >= i) {
                    sum += temp;
                    temp = 0;
                    start = true;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }

        return sum;
    }

    //暴力
    public int trap(int[] height) {
        int sum = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int left = 0;
            for (int j = 0; j <= i - 1; j++) {
                left = Math.max(left, height[j]);
            }

            int right = 0;
            for (int j = height.length - 1; j >= i + 1; j--) {
                right = Math.max(right, height[j]);
            }

            int min = Math.min(left, right);
            if (min > height[i]) {
                sum += min - height[i];
            }
        }

        return sum;
    }

    //dp
    public int trap1(int[] height) {
        int sum = 0;
        //将i左边最高的高度记录下来
        int[] left = new int[height.length];
        //将i右边最高的高度记录下来
        int[] right = new int[height.length];

        //它前边的墙的左边的最高高度和它前边的墙的高度选一个较大的，就是当前列左边最高的墙了
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i - 1], height[i - 1]);
        }

        //它后边的墙的右边的最高高度和它后边的墙的高度选一个较大的，就是当前列右边最高的墙了
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i + 1]);
        }

        //往两边最高的墙之间注水。正在求的列会存有 （木桶效应）两边最高的墙之间较矮的墙 - 当前列高度 的水
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(left[i], right[i]);
            if (min > height[i]) {
                sum += min - height[i];
            }
        }

        return sum;
    }

    /*
    双指针法
    height [ left - 1] 是可能成为 max_left 的变量， 同理，height [ right + 1 ] 是可能成为 right_max 的变量。
    只要保证 height [ left - 1 ] < height [ right + 1 ] ，那么 max_left 就一定小于 max_right。
     */
    public int trap2(int[] height) {
        int sum = 0;
        int maxLeft = 0;
        int maxRight = 0;
        int left = 1;
        int right = height.length - 2;

        for (int i = 1; i < height.length - 1; i++) {

            if (height[left - 1] < height[right + 1]) {
                maxLeft = Math.max(maxLeft, height[left - 1]);
                if (maxLeft > height[left]) {
                    sum += maxLeft - height[left];
                }
                ++left;
            } else {
                maxRight = Math.max(maxRight, height[right + 1]);
                if (maxRight > height[right]) {
                    sum += maxRight - height[right];
                }
                --right;
            }
        }

        return sum;
    }

    /*
    栈
    我们用栈保存每堵墙。

    当遍历墙的高度的时候，如果当前高度小于栈顶的墙高度，说明这里会有积水，我们将墙的高度的下标入栈。

    如果当前高度大于栈顶的墙的高度，说明之前的积水到这里停下，我们可以计算下有多少积水了。计算完，就把当前的墙继续入栈，作为新的积水的墙。

    总体的原则就是，

    1、当前高度小于等于栈顶高度，入栈，指针后移。

    2、当前高度大于栈顶高度，出栈，计算出当前墙和栈顶的墙之间水的多少，然后计算当前的高度和新栈的高度的关系，重复第 2 步。直到当前墙的高度不大于栈顶高度或者栈空，然后把当前墙入栈，指针后移。

    作者：windliang
    链接：https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int trap3(int[] height) {
        LinkedList<Integer> stack = new LinkedList<>();
        int current = 0;
        int sum = 0;
        while (current < height.length) {

            //如果current下标的墙高度大于栈顶下标墙高度，那么开始计算积水量，直至栈顶为空
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()];
                stack.pop();
                if (stack.isEmpty()) {
                    break;
                }

                int distance = current - stack.peek() - 1;
                int min = Math.min(height[stack.peek()], height[current]);
                sum += distance * (min - h);
            }

            stack.push(current);
            ++current;
        }

        return sum;
    }
}
