package leetcode;

import java.util.*;

/**
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 * <p>
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 * <p>
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode295 {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-5);
        System.out.println(medianFinder.findMedian());
    }

    //量大，重复数很多的时候才会省空间和提速，如果重复数少且量少时会更慢
    static class Node {
        int val;
        int count = 1;

        public Node(int val) {
            this.val = val;
        }
    }

    //慢
    static class MedianFinder {

        LinkedList<Node> linkedList = new LinkedList<>();
        int sum = 0;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {

        }

        public void addNum(int num) {
            Node node = new Node(num);
            int index = Collections.binarySearch(linkedList, node, Comparator.comparingInt(k -> k.val));
            if (index < 0) {
                linkedList.add(-index - 1, node);
            } else {
                linkedList.get(index).count += 1;
            }
            sum++;
        }

        public double findMedian() {
            if (sum == 0) {
                return 0;
            }
            int mid = sum / 2 + 1;
            int count = 0;
            if (sum % 2 == 0) {
                ListIterator<Node> iterator = linkedList.listIterator();
                while (iterator.hasNext()) {
                    Node next = iterator.next();
                    if (count + next.count == mid - 1) {
                        int sum = iterator.next().val + next.val;
                        return ((double) sum) / 2;
                    } else if (count + next.count > mid - 1) {
                        return next.val;
                    }
                    count += next.count;
                }
            } else {
                for (Node node : linkedList) {
                    count += node.count;
                    if (count >= mid) {
                        return node.val;
                    }
                }
            }

            return 0;
        }
    }

    //用两个堆
    class MedianFinder1 {
        //最大堆，堆顶为最大值，放传入的数的较小的一半
        PriorityQueue<Integer> maxHeep = new PriorityQueue<>();
        //最小堆，堆顶为最小值，放传入的数的较大的一半
        PriorityQueue<Integer> minHeep = new PriorityQueue<>(Comparator.reverseOrder());

        public void addNum(int num) {
            //堆平衡
            /*
            先将数放入最大堆，取出当前最大堆的最大值
            再将最大值放入最小堆中，此时最小堆堆顶为更新后的最小值
            如果最大堆的size比较小，那么需要从最小堆中取出最小值放入最大堆
             */
            maxHeep.add(num);
            minHeep.add(maxHeep.poll());

            if (maxHeep.size() < minHeep.size()) {
                maxHeep.add(minHeep.poll());
            }
        }

        public double findMedian() {
            //最大堆有两种情况：一种是最大堆size比最小堆size大1，那么说明传入的数的数量为奇数，直接取中值：最大堆堆顶
            //一种是偶数的情况，需要取两个堆的堆顶/2
            return maxHeep.size() > minHeep.size() ? maxHeep.peek() : (maxHeep.peek() + minHeep.peek()) / 2.0;
        }
    }

    class MedianFinder2 {

        ArrayList<Integer> linkedList = new ArrayList<>();
        int sum = 0;

        /**
         * initialize your data structure here.
         */
        public MedianFinder2() {

        }

        public void addNum(int num) {

            int index = Collections.binarySearch(linkedList, num);
            if (index < 0) {
                linkedList.add(-index - 1, num);
            } else {
                linkedList.add(index, num);
            }
            sum++;
        }

        public double findMedian() {
            if (sum == 0) {
                return 0;
            }
            int mid = sum / 2;
            int count = 0;
            if (sum % 2 == 0) {
                return (linkedList.get(mid) + linkedList.get(mid - 1)) / 2.0;
            } else {
                return linkedList.get(mid);
            }
        }
    }
}
