package algorithm;

import java.util.LinkedList;

public class LeetCode_Interview59II {

    public static void main(String[] args) {
        MaxQueue queue = new MaxQueue();
        System.out.println(queue.pop_front());
        System.out.println(queue.max_value());
        queue.push_back(46);
        System.out.println(queue.max_value());
        System.out.println(queue.pop_front());
        queue.push_back(868);
        System.out.println(queue.max_value());
        queue.push_back(666);
        queue.push_back(999);
        queue.push_back(666);
        System.out.println(queue.max_value());
        System.out.println(queue.pop_front());
        System.out.println(queue.pop_front());
        System.out.println(queue.max_value());
        System.out.println(queue.pop_front());
        System.out.println(queue.pop_front());
        System.out.println(queue.max_value());
    }

    static class MaxQueue {

        private final LinkedList<Integer> queue = new LinkedList<>();
        private final LinkedList<Integer> maxQueue = new LinkedList<>();

        public MaxQueue() {

        }

        public int max_value() {
            if (queue.isEmpty()) {
                return -1;
            }
            return maxQueue.getFirst();
        }

        public void push_back(int value) {
            queue.addLast(value);
            if (!maxQueue.isEmpty() && maxQueue.getLast() < value) {
                maxQueue.removeLast();
            }
            maxQueue.addLast(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }
            int value = queue.removeFirst();
            if (value == maxQueue.getFirst()) {
                maxQueue.removeFirst();
            }
            return value;
        }
    }
}
