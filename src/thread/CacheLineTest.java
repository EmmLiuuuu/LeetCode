package thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;

public class CacheLineTest {

    private final static int LENGTH = 16 * 1024 * 1024 * 6;
    private final static long[] testData = new long[LENGTH];
    private final static int[] RESULT = new int[6];
    private final static CountDownLatch countDownLatch = new CountDownLatch(6);
    private final static Function<Integer, Void> FUNCTION = id -> {
        try {
            RESULT[id] = 0;
            int chunkSize = LENGTH / 6;
            int start = id * chunkSize;
            int end = Math.min(start + chunkSize, LENGTH);
//            int c = 0;
            for (int i = start; i < end; i++) {
                if (testData[i] % 2 != 0) {
                    RESULT[id]++;
//                    c++;
                }
            }
//            RESULT[id] = c;
            return null;
        } finally {
            countDownLatch.countDown();
        }
    };

    static {
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            testData[i] = random.nextInt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            new Thread(() -> FUNCTION.apply(finalI)).start();
        }
        countDownLatch.await();
        System.out.println(String.format("Time Usage: %sms", System.currentTimeMillis() - start));

        int sum = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < LENGTH; i++) {
            if (testData[i] % 2 != 0) {
                sum++;
            }
        }
        System.out.println(String.format("Time Usage: %sms", System.currentTimeMillis() - start));
    }
}
