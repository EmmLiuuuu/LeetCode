package leetcode;
import leetcode.LeetCode160.ListNode;

public class LeetCode25 {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        reverseKGroup1(head, 3);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || head.next == null) {
            return head;
        }

        int len = 0;

        ListNode p = head;
        while (p != null) {
            p = p.next;
            len++;
        }

        if (len < k) {
            return head;
        }

        ListNode root = new ListNode(-1);
        ListNode last = head;
        int i = k;
        while (i > 0) {
            last = last.next;
            i--;
        }
        head = reverse(head, root, k);
        len -= k;
        ListNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = last;

        while (len >= k) {
            ListNode pre = temp;
            temp = last;
            i = k;
            while (i > 0) {
                last = last.next;
                i--;
            }

            pre.next = reverse(temp, root, k);
            len -= k;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = last;
        }

        return head;

    }

    public static ListNode reverse(ListNode head, ListNode root, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        root.next = null;
        ListNode p = head;
        ListNode q;

        while (k > 0) {
            q = p;
            p = p.next;

            q.next = root.next;
            root.next = q;
            k--;
        }

        return root.next;
    }

    public static ListNode reverseKGroup1(ListNode head, int k) {
        //用一个临时变量作为头
        ListNode first = new ListNode(0);
        first.next = head;
        //pre，end指向临时变量
        ListNode pre = first, end = first;
        while (end.next != null) {
            //将end移动到第k个位置
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            //如果end为null，说明剩下的不足k个
            if (end == null) {
                break;
            }

            //用临时变量start指向真正的链表头
            ListNode start = pre.next;
            //此时 ：start
            // pre -> 1 -> 2 -> 3 -> null
            //       end
            //        4 -> 5 -> 6 -> null
            //第二轮
            //                  pre
            //                  end
            // first -> 3 -> 2-> 1 -> 4 -> 5 -> 6 -> null
            ListNode next = end.next;
            end.next = null;

            pre.next = reverse1(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return first.next;
    }

    public static ListNode reverse1(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
