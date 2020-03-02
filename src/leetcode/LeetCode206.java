package leetcode;

public class LeetCode206 {


    //头插法
    public ListNode reverseList(ListNode head) {
        ListNode temp = new ListNode(-1);
        temp.next = head;

        ListNode p = temp.next;
        temp.next = null;
        ListNode q;
        while (p != null) {
            q = p;
            p = p.next;
            q.next = temp.next;
            temp.next = q;
        }

        return temp.next;
    }

    //原地翻滚
    public ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    //递归
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = reverseList2(head.next);
        //此时 head 以后的部分已经逆转： head -> a <- b <- c <- d <- p，需要将head.next.next（a.next）指向head才行
        head.next.next = head;
        //                ->
        //调整指向后：head <- a <- b <- c <- d <- p，存在head，a的环，需要解环，head->next = null
        head.next = null;
        return p;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
