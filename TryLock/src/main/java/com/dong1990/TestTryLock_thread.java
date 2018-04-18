package com.dong1990;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestTryLock_thread implements Runnable{

    private static Lock locks = new ReentrantLock();

    public void run() {
        try {
            if(locks.tryLock(4, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getName()+"-->");
                Thread.sleep(6000);//
            }else{
                System.out.println(Thread.currentThread().getName()+" time out ");
            }
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }finally {
            locks.unlock();//会抛出锁对象的异常，因为没有获取锁在unlock的时候出异常，可以先判断一下是否存在在执行。
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestTryLock_thread test =new TestTryLock_thread();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();
        t2.start();
        t1.join();
        System.out.println("over");
    }

}
