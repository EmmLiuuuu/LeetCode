package leetcode;

public class MergeTwoSortList {

    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);

        Node node1 = new Node(2);
        node1.next = new Node(4);

        Node head = new Node(-1);
//        Node result = merge1(node, node1, head);

        Node result1 = merge(node, node1);
        System.out.println(1111);
    }

    static class Node {
        int value;
        Node next;
        Node(int value) {
            this.value = value;
        }
    }

    public static Node merge(Node list1, Node list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }
        Node p = list1, q = list2;
        Node head = new Node(-1);
        Node temp = head;
        while (p != null && q != null) {
            if (p.value < q.value) {
                Node pre = p;
                p = p.next;
                temp.next = pre;
            } else {
                Node pre = q;
                q = q.next;
                temp.next = pre;
            }
            temp = temp.next;
        }

        if (p == null) {
            temp.next = q;
        } else {
            temp.next = p;
        }

        return head.next;
    }

    public static Node merge1(Node list1, Node list2, Node head) {
        if (list1 == null || list2 == null) {
            return head.next = list1 == null ? list2 : list1;
        } else if (list1.value < list2.value) {
            head.next = list1;
            return merge1(list1.next, list2, head.next);
        } else {
            head.next = list2;
            return merge1(list1, list2.next, head.next);
        }
    }
}
