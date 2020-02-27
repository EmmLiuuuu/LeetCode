package leetcode;

import java.util.LinkedList;

public class LeetCode239 {

    public static void main(String[] args) {
        new LeetCode239().maxSlidingWindow1(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }

        int[] maxes = new int[nums.length - k + 1];

        int i, j;
        int maxPos = -1;

        for (i = 0; i <= nums.length - k; ++i) {
            // Ending index of the current window
            j = i + k - 1;

            // new element >= max of last window
            // that means new element is max in the two windows
            // here using >= to make maxPos stay in the windows for a longer time
            if (maxPos != -1 && nums[j] >= nums[maxPos]) {
                maxPos = j;
                maxes[i] = nums[maxPos];
            }
            // new element < max of last window
            // AND the max of last window is also in this window
            // => it means the max of the last window is still the max of this window
            else if (i <= maxPos) {
                maxes[i] = nums[maxPos];
            }
            // new element < max of last window
            // AND the max of last window is not in this window
            // So we do not know which element is the max in this window, we have to scan the window to find it
            else {
                int maxWindow = Integer.MIN_VALUE;
                int maxPosWindow = 0;
                for (int z = i; z <= j; ++z) {
                    if (nums[z] > maxWindow) {
                        maxPosWindow = z;
                        maxWindow = nums[z];
                    }
                }
                maxPos = maxPosWindow;
                maxes[i] = nums[maxPos];
            }
        }
        return maxes;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        result[0] = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            clean(nums, queue, i, k);
            result[0] = Math.max(result[0], nums[i]);
            queue.addLast(i);
        }

        int index = 1;
        for (int i = k; i < nums.length; i++) {
            clean(nums, queue, i, k);
            queue.addLast(i);
            result[index++] = nums[queue.getFirst()];
        }
        return result;
    }

    public void clean(int[] nums, LinkedList<Integer> queue, int i, int k) {
        if (!queue.isEmpty() && queue.getFirst() == i - k) {
            queue.removeFirst();
        }
        while (!queue.isEmpty() && nums[i] > nums[queue.getLast()]) {
            queue.removeLast();
        }
    }

}
