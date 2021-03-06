package interviews;

public class LeetCode2_ByteDance {

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            next = null;
        }
    }
    public static void main(String[] args) {
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(9);
        head.next.next.next = new Node(9);
        head.next.next.next.next = new Node(9);

        Node head2 = new Node(0);
        head2.next = new Node(1);

        head.next = reverse1(head.next);
        print(head);
        reverse(head2);

        for (Node p = head.next, p1 = head2.next; p != null && p1 != null; p = p.next, p1 = p1.next) {
            p.value = p.value + p1.value;
            Node temp = p;
            while (temp.value >= 10) {
                temp.value -= 10;
                temp = temp.next;
                temp.value += 1;
            }
        }

        print(head);
        reverse(head);
        print(head);

    }

    private static void reverse2(Node head) {
        if (head == null || head.next == null) {
            return;
        }

        Node p = head.next;
        Node q;
        head.next = null;

        while (p != null) {
            q = p;
            p = p.next;
            q.next = head.next;
            head.next = q;
        }
    }

    //head是头指针，head.next是第一个元素，不断地往head，head.next中间插入链表元素，即可达到逆置的效果
    private static void reverse(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        Node p = head.next;
        Node q;
        head.next = null;
        while (p != null) {
            q = p;
            p = p.next;
            q.next = head.next;
            head.next = q;
        }
    }

    private static Node reverse1(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node q;
        Node temp = new Node(-1);
        Node p = head;

        while (p != null) {
            q = p;
            p = p.next;
            q.next = temp.next;
            temp.next = q;
        }

        return temp.next;

    }

    private static Node reverse3(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node cur = head;
        Node pre = null;

        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void print(Node head) {
        Node p = head.next;
        while (p != null) {
            System.out.println(p.value);
            p = p.next;
        }
    }
}
