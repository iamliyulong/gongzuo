package com.demo.lyl.java.lang;

/**
 * @ClassName RunnableDemo
 * @Description TODO
 * @Author liyulong
 * @Date 2020/12/24 15:33
 * @Version 1.0
 **/
public class RunnableDemo implements Runnable{

    private Thread t;
    private String threadName;

    RunnableDemo(String threadName) {
        this.threadName = threadName;
        System.out.println("Creating"+threadName);
    }

    @Override
    public void run() {
        System.out.println("Running"+threadName);
        try {
            for (int i = 1;i<=4;i++){
                System.out.println("Thread:"+threadName+", "+i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread"+threadName+" exiting");
    }

    public void start(){
        System.out.println("starting"+threadName);
        if (t == null){
            t = new Thread(this,threadName);
            t.start();
        }
    }

    public static void main(String[] args) {
        RunnableDemo R1 = new RunnableDemo("Thread-1");
        R1.start();
        RunnableDemo R2 = new RunnableDemo("Thread-2");
        R2.start();
    }

}
