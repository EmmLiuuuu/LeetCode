package leetcode;

import java.util.LinkedList;
import java.util.ListIterator;

public class LeetCode225 {

    class MyStack {

        private final LinkedList<Integer> queue = new LinkedList<>();
        private final LinkedList<Integer> queue1 = new LinkedList<>();

        /**
         * Initialize your data structure here.
         */
        public MyStack() {

        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            if (queue.isEmpty()) {
                queue.addLast(x);
                ListIterator<Integer> iterator = queue1.listIterator();
                while (iterator.hasNext()) {
                    queue.addLast(iterator.next());
                    iterator.remove();
                }
            } else {
                queue1.addLast(x);
                ListIterator<Integer> iterator = queue.listIterator();
                while (iterator.hasNext()) {
                    queue1.addLast(iterator.next());
                    iterator.remove();
                }
            }
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            if (queue1.isEmpty()) {
                return queue.removeFirst();
            } else {
                return queue1.removeFirst();
            }
        }

        /**
         * Get the top element.
         */
        public int top() {
            if (queue1.isEmpty()) {
                return queue.getFirst();
            } else {
                return queue1.getFirst();
            }
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return queue.isEmpty() && queue1.isEmpty();
        }
    }
}
