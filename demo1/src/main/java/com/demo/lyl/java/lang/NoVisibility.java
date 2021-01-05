package com.demo.lyl.java.lang;

/**
 * @ClassName NoVisibility
 * @Description TODO
 * @Author liyulong
 * @Date 2020/12/30 15:21
 * @Version 1.0
 **/
public class NoVisibility {
    private static boolean ready;
    private static int number;
    private static class ReaderThread implements Runnable{

        @Override
        public void run() {
            while(!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new Thread(new ReaderThread()).start();
        number = 42;
        ready = true;
    }

}
