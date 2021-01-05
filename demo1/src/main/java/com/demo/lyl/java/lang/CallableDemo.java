package com.demo.lyl.java.lang;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName CallableDemo
 * @Description TODO
 * @Author liyulong
 * @Date 2020/12/25 17:46
 * @Version 1.0
 **/
public class CallableDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int i= 0;
        for (;i<100;i++){
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
        return i;
    }

    public static void main(String[] args) {

        CallableDemo cd = new CallableDemo();
        FutureTask<Integer> ft = new FutureTask<>(cd);
        for (int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+" 的循环变量i的值"+i);
            if(i==20){
                new Thread(ft,"有返回值的线程").start();
            }
        }
        try {
            System.out.println("子线程的返回值："+ft.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
