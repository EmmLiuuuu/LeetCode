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
        reverseKGroup(head, 3);
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
}
