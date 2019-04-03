package leetcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JUC {

    private static int x = 0, y = 0;
    private static int a = 0, b =0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;

        for(;;) {
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            CountDownLatch latch = new CountDownLatch(1);

            Thread one = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }
                a = 1;
                x = b;
            });

            Thread other = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException ignored) {
                }
                b = 1;
                y = a;
            });
            one.start();other.start();
            latch.countDown();
            one.join();other.join();

            String result = "第" + i + "次 (" + x + "," + y + "）";
            if(x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {
                System.out.println(result);
//                System.out.println(x + " " + y);
            }
        }

        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        Lock read = reentrantReadWriteLock.readLock();

        Thread g = new Thread(() -> {
            read.lock();
            try {
                while (true) {
                    System.out.println("111");
                }
            } finally {
                read.unlock();
            }

        });

        g.start();

        Thread h = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("interrupt~");
                g.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        h.start();
    }
}
