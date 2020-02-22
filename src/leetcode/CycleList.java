package leetcode;

public class CycleList {

    //https://blog.csdn.net/qq_36781505/article/details/91401474
    public static LeetCode160.ListNode EntryNodeOfLoop(LeetCode160.ListNode pHead) {
        LeetCode160.ListNode fast = pHead;
        LeetCode160.ListNode slow = pHead;
        //确认单链表是否有环
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                break;
        }
        if (fast == null || fast.next == null)
            return null;
        //找到环的起点
        fast = pHead;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
