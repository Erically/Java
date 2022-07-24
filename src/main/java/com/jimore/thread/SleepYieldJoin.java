package com.jimore.thread;

// 1.sleep 线程让出执行时间给其他线程执行,sleep完线程也是进入就绪状态
// 2.yield 线程暂时退出执行的cpu，进入线程就绪队列，
// 和其他队列里的线程一起等待被调度到cpu执行，当原来的线程再次被调度回cpu时，会接着线程上次暂停的地方继续往下执行
// 3.join 在A线程中调用B线程的join方法，让B线程过来先执行一会儿，执行完了，A线程继续执行，
// B线程的执行就是一个插入的过程，通常用于等待另一个线程的结束
// 4.CPU只负责从内存读取指令并执行，并不知道多线程的概念
public class SleepYieldJoin {
    public static void main(String[] args) throws InterruptedException {
//        testSleepFuc();
        // 父线程退出，子线程会被继续执行，不用将主线程进行睡眠，与Golang的协程不同
        // Thread.sleep(8*1000);

//        testYieldFuc();

        testJoinFuc();


    }

    private static void testSleepFuc(){

        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println( "t1->"+i+": Sleep 5s.");
            }

        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("t2->"+i);
            }
        });

        t1.start();
        t2.start();
    }

    private static void testYieldFuc(){

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("---t1->"+i+": yield before");
                Thread.currentThread().yield(); // 由于线程会重新入就绪队列，线程下面的内容会在线程再次被执行的时候被执行
                System.out.println("---t1->"+i+": yield after");
            }

        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("t2->"+i);
            }
        });

        t1.start();
        t2.start();
    }

    private static void testJoinFuc(){
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("t1->"+i+": join");
            }

        });

        Thread t2 = new Thread(()->{
            try {
                t1.join(); // t1执行完之后，才会执行t2
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("t2->"+i);
            }
        });

        t1.start();
        t2.start();

    }

}
