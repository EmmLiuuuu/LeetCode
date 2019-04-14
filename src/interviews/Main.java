package interviews;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String temp;
        for (int i = 0; i < n; i++) {
            temp = in.nextLine();
        }
        temp = in.nextLine();

        System.out.println(new Random().nextInt(n + 1));
    }
}
