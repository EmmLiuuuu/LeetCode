package interviews;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Netease2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] list = new int[n];

        for (int i = 0; i < n; i++) {
            list[i] = scanner.nextInt();
        }
        Arrays.sort(list);

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (gcd(gcd(list[i], list[j]), list[k]) == 1) {
                        count++;
                    }
                }
            }
        }

        System.out.print(count);
    }

    private static int gcd(int a, int b) {
        while (a != 0) {
            int temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }
}
