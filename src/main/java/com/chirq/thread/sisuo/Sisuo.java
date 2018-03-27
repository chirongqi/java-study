package com.chirq.thread.sisuo;

/**
 * 
 * <p>
 * <b>标题</b>： 死锁
 * </p>
 */
public class Sisuo {

    public static String obj1 = "obj1";

    public static String obj2 = "obj2";

    public static void main(String[] args) {
        Thread a = new Thread(new ThreadObj1());
        Thread b = new Thread(new ThreadObj2());
        a.start();
        b.start();
    }
}

class ThreadObj1 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Lock1 running");
            while (true) {
                synchronized (Sisuo.obj1) {
                    System.out.println("111");
                    Thread.sleep(5000);// 获取obj1后先等一会儿，让Lock2有足够的时间锁住obj2
                    synchronized (Sisuo.obj2) {
                        System.out.println("222");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ThreadObj2 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Lock2 running");
            while (true) {
                synchronized (Sisuo.obj2) {
                    System.out.println("333");
                    Thread.sleep(5000);
                    synchronized (Sisuo.obj1) {
                        System.out.println("444");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
