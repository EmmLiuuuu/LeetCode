package leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private final static Object flag = new Object();

    private volatile boolean f = false;

    interface B {
        static void fun() {

        }
    }


    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }

    public static void main(String[] args) {

//        ReentrantLock reentrantLock = new ReentrantLock();

//        reentrantLock.lock();

        synchronized (Main.class) {
            System.out.println(Thread.holdsLock(Thread.currentThread()));
        }

//        reentrantLock.unlock();


        CountDownLatch countDownLatch = new CountDownLatch(2);


        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 12, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(512), new ThreadPoolExecutor.AbortPolicy());

        long start = System.currentTimeMillis();

        executor.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }

            System.out.println("over 1");
        });

        executor.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }

            System.out.println("over 2");
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long end = System.currentTimeMillis();

        System.out.println(((end - start) / 1000) + "s");

        System.out.println("main over");

        executor.shutdown();


//
//
//        String a = "ab";
//        String b = "ab";
//
//        System.out.println(a == b);
//
////        a.intern();
//
//        String c = "ab";
//
//        System.out.println(a == b);
//
//        System.out.println(a == c);
//
//        System.out.println(b == c);
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        try (reader) {
//            String string = reader.readLine();
//            System.out.println(string);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Date date = new Date();
//
//        Calendar calendar = Calendar.getInstance();
//
//        calendar.setTime(date);
//        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));

        CyclicBarrier barrier = new CyclicBarrier(2);



    }

    public void fun() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized (flag) {
                for (int i = 0; i <= 100; i+=2) {
                    flag.notify();
                    System.out.println(i);
                    try {
                        if (i == 100) {
                            break;
                        }
                        flag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (flag) {
                for (int i = 1; i <= 99; i += 2) {

                    flag.notify();
                    System.out.println(i);
                    try {
                        if (i == 99) {
                            break;
                        }
                        flag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

//        thread1.start();

//        thread2.start();

        Thread thread = new Thread(() -> {
            synchronized (flag) {
                for (int i = 0; i <= 100; i+=2) {
                    if (!f) {
                        System.out.println("produce:" + i);
                        f = true;
                        flag.notify();
                    } else {
                        try {
//                            System.out.println("produce:" + i);
                            flag.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            synchronized (flag) {
                for (int i = 1; i <= 99; i+=2) {
                    if (!f) {
                        try {
//                            System.out.println("consume:" + i);
                            flag.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("consume:" + i);
                        f = false;
                        flag.notify();
                    }
                }
            }
        });

//        thread.start();
//        thread3.start();

        CountDownLatch countDownLatch = new CountDownLatch(10);
        CyclicBarrier barrier = new CyclicBarrier(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(12, 12, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20), t -> new Thread(t, "CountDown-test-%d"), new ThreadPoolExecutor.AbortPolicy());


        Future future = threadPoolExecutor.submit(() -> {
            System.out.println(111);
        });

//        future.get();

        future.cancel(true);
//        for (int i = 0; i < 10; i++) {
//            threadPoolExecutor.execute(() -> {
//                System.out.println("thread wait: " + countDownLatch.getCount());
//
//                try {
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("i am here :" + countDownLatch.getCount());
//            });
//        }

//        for (int i = 0; i < 10; i++) {
//            threadPoolExecutor.execute(() -> {
//                System.out.println("wait");
//
//                try {
//                    barrier.await();
//                } catch (InterruptedException | BrokenBarrierException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("start");
//            });
//        }

        Phaser phaser = new Phaser();

//        phaser.register();
//        for (int i = 0; i < 10; i++) {
//            phaser.register();
//            threadPoolExecutor.execute(() -> {
//                System.out.println("wait");
//
//                phaser.arriveAndAwaitAdvance();
//
//                System.out.println("run");
//            });
//        }


        Phaser root = new Phaser(5);

        Phaser n1 = new Phaser(root, 5);
        Phaser n2 = new Phaser(root, 5);

        Phaser m1 = new Phaser(n1, 5);
        Phaser m2 = new Phaser(n1, 5);
        Phaser m3 = new Phaser(n1, 5);

        Phaser m4 = new Phaser(n2, 5);

        Phaser m5 = new Phaser(n2);

        System.out.println("n2 parties: " + n2.getRegisteredParties());
        m5.register();
        System.out.println("n2 parties: " + n2.getRegisteredParties());

        m5.arriveAndDeregister();
        System.out.println("n2 parties: " + n2.getRegisteredParties());




        Thread.sleep(2000);
//        phaser.arriveAndAwaitAdvance();
//        System.out.println("main count down");
//        countDownLatch.countDown();
        System.out.println("exit");
        threadPoolExecutor.shutdown();
    }

}
