package leetcode;
import leetcode.LeetCode160.ListNode;

public class LeetCode24 {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        System.out.println(swapPairs(head));
    }

    public static ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

//        ListNode swap = new ListNode(-1);
//        ListNode q = swap;
//
//        ListNode p = head;
//
//        while (p != null && p.next != null) {
//            ListNode temp = p;
//            q.next = p.next;
//
//            p = p.next.next;
//            q = q.next;
//            q.next = temp;
//            q = q.next;
//        }
//
//        return swap.next;

//        ListNode p = head;
//        ListNode k = head.next;
//
//        while (p != null && p.next != null) {
//            int temp = p.val;
//            p.val = k.val;
//            k.val = temp;
//
//            p = p.next.next;
//            k = p == null ? null : p.next;
//        }
//
//        return head;//取巧

        ListNode swap = head;

        ListNode p = head.next;

        ListNode q = head.next.next;

        swap.next = q;
        p.next = swap;

        head = p;

        while (swap.next != null) {
            ListNode temp = swap;
            swap = swap.next;
            p = swap.next;
            q = swap.next == null ? null : swap.next.next;

            swap.next = q;
            if (p != null) {
                p.next = swap;
                temp.next = p;
            }
        }

        return head;
    }
}
