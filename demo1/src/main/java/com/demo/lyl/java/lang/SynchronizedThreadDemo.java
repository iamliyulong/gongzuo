package com.demo.lyl.java.lang;

/**
 * @ClassName SynchronizedThreadDemo
 * @Description TODO
 * @Author liyulong
 * @Date 2020/12/26 17:09
 * @Version 1.0
 **/
public class SynchronizedThreadDemo {

    class Bank {
        private int account = 100;

        private int getAccount() {
            return account;
        }

        /**
         * @param [money]
         * @return void
         * @Author liyulong
         * @Description 同步方法
         * @Date 17:13 2020/12/26
         **/
        public synchronized void save(int money) {
            account += money;
        }

        /**
         * @param [money]
         * @return void
         * @Author liyulong
         * @Description 同步代码块
         * @Date 17:13 2020/12/26
         **/
        public void save1(int money) {
            synchronized (this) {
                account += money;
            }
        }
    }

    class SynchronizedThread implements Runnable {

        private Bank bank;

        SynchronizedThread(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
//                bank.save(10);
                bank.save(10);
                System.out.println(i+" "+Thread.currentThread().getName() + " 账户余额：" + bank.getAccount());
            }
        }
    }

    public void useThread() {
        Bank bank = new Bank();
        SynchronizedThread st1 = new SynchronizedThread(bank);
        System.out.println("线程1");
        Thread t1 = new Thread(st1);
        t1.start();
        SynchronizedThread st2 = new SynchronizedThread(bank);
        System.out.println("线程2");
        Thread t2 = new Thread(st1);
        t2.start();
    }

    public static void main(String[] args) {
        SynchronizedThreadDemo std = new SynchronizedThreadDemo();
        std.useThread();
    }

}
