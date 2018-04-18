package com.dong1990;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestTryLock02_currentThread {

    public static void main(String[] args) {
        testTryLock();
    }
    private static void testTryLock() {
        final Lock lock = new ReentrantLock();

        new Thread() {
            @Override
            public void run() {
                String tName = Thread.currentThread().getName();

                if (lock.tryLock()) {
                    System.out.println(tName + "获取到锁！");
                } else {
                    System.out.println(tName + "获取不到锁！");
                    return;
                }

                try {

                    for (int i = 0; i < 5; i++) {
                        System.out.println(tName + ":" + i);
                    }

                    Thread.sleep(5000);
                } catch (Exception e) {
                    System.out.println(tName + "出错了！！！");
                } finally {
                    System.out.println(tName + "释放锁！！");
                    lock.unlock();
                }

            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                String tName = Thread.currentThread().getName();

                if (lock.tryLock()) {
                    System.out.println(tName + "获取到锁！");
                } else {
                    System.out.println(tName + "获取不到锁！");
                    return;
                }

                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(tName + ":" + i);
                    }

                } catch (Exception e) {
                    System.out.println(tName + "出错了！！！");
                } finally {
                    System.out.println(tName + "释放锁！！");
                    lock.unlock();
                }
            }
        }.start();
    }
}
