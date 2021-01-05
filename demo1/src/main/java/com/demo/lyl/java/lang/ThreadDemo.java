package com.demo.lyl.java.lang;

/**
 * @ClassName ThreadDemo
 * @Description TODO
 * @Author liyulong
 * @Date 2020/12/25 13:41
 * @Version 1.0
 **/
public class ThreadDemo extends Thread {

    private Thread t;
    private String threadName;

    ThreadDemo(String threadName){
        this.threadName = threadName;
        System.out.println("creating "+threadName);
    }

    public void run(){
        System.out.println("Running "+threadName);
        for (int i=1;i<=4;i++){
            System.out.println("Thread "+threadName+", "+i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread "+threadName+" exiting");
    }

    public void start(){
        System.out.println("Thread "+threadName+" starting");
        if (t == null){
            t = new Thread(this,threadName);
            t.start();
        }
    }

    public static void main(String[] args) {
        ThreadDemo T1 = new ThreadDemo("Thread-1");
        T1.start();
        ThreadDemo T2 = new ThreadDemo("Thread-2");
        T2.start();
    }


}
