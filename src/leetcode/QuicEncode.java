package leetcode;

import java.util.Random;

public class QuicEncode {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int a = random.nextInt(10);
            int p = random.nextInt(10);
            while (p == 0) {
                p = random.nextInt(10);
            }
            int g = random.nextInt(10);

            int A = ((int)Math.pow(g, a)) % p;

            int b = random.nextInt(10);
            int B = ((int) Math.pow(g, b)) % p;

            System.out.println((((int) Math.pow(B, a)) % p) == (((int) Math.pow(A, b)) % p));
        }
    }
}
