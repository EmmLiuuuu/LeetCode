package leetcode;

import java.util.TreeMap;

public class LeetCode352 {

    //https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/solution/treemap-lian-biao-java-by-henrylee4/
    class SummaryRanges {

        private TreeMap<Integer, Node> treeMap;
        private Node dummy;
        private int count;

        /**
         * Initialize your data structure here.
         */
        public SummaryRanges() {
            treeMap = new TreeMap<>();
            dummy = new Node(-2, -2, null);
            treeMap.put(-2, dummy);
            count = 0;
        }

        public void addNum(int val) {
            Node pre = treeMap.lowerEntry(val).getValue();
            Node cur = pre.next;
            if (cur != null && cur.left <= val) {
                return;
            }
            boolean isNull = cur == null;
            if (isNull) {
                cur = new Node(val + 2, val + 2, null);
            }
            if (pre.right + 1 == val) {
                if (val + 1 == cur.left) {
                    treeMap.remove(pre.right);
                    treeMap.remove(cur.right);
                    pre.right = cur.right;
                    pre.next = cur.next;
                    treeMap.put(cur.right, pre);
                    count--;
                } else {
                    treeMap.remove(pre.right);
                    pre.right++;
                    treeMap.put(pre.right, pre);
                }
            } else if (val + 1 == cur.left) {
                cur.left--;
            } else {
                Node node = new Node(val, val, isNull ? null : cur);
                treeMap.put(val, node);
                count++;
                pre.next = node;
            }
        }

        public int[][] getIntervals() {
            int[][] res = new int[count][2];
            Node node = dummy.next;
            int id = 0;
            while (node != null) {
                res[id++] = new int[]{node.left, node.right};
                node = node.next;
            }
            return res;
        }

        private class Node {

            int left, right;
            Node next;

            public Node(int left, int right, Node next) {
                this.left = left;
                this.right = right;
                this.next = next;
            }
        }
    }

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
}
