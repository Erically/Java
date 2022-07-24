package com.jimore.thread;

// 创建线程
public class NewThread {

    // 创建线程的第一种方法
    static class ExtendThread extends Thread{
        @Override
        public void run(){
            System.out.println("extend thread.");
            return;
        }
    }

    // 创建线程的第二种方法
    static class ImplementThread implements Runnable{
        @Override
        public void run(){
            System.out.println("runnable thread.");
        }
    }

    public static void main(String[] args) {
        new ExtendThread().start();
        new Thread(new ImplementThread()).start();

        // 创建线程的第三种方法
        new Thread(()->{
            System.out.println("lambda");
        }).start();

    }

}
