package leetcode;


import java.util.Scanner;

public class JosephusProblem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int m = scanner.nextInt();

        Node entrance = new Node(1);
        Node cur = entrance;
        Node start = s == 1 ? entrance : null;
        int count = 1;
        for (int i = 2; i <= n; i++) {
            cur.next = new Node(i);
            cur = cur.next;
            if (i == s) {
                start = cur;
            }
        }

        //闭合成环
        cur.next = entrance;
        cur = start;
        if (start != null) {
            do {
                Node prev = cur;
                cur = cur.next;
                count++;
                if (count == m) {
                    count = 1;
                    System.out.println(cur.val);
                    prev.next = cur.next;
                    cur.next = null;
                    cur = prev.next;
                    n--;
                }
            } while (n > 0);
        }
    }

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
