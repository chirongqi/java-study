package com.chirq.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableDemo {
    public static void main(String[] args) {
        SellTicket str = new SellTicket();

        Thread tr1 = new Thread(str, "窗口1");
        Thread tr2 = new Thread(str, "窗口2");
        Thread tr3 = new Thread(str, "窗口3");

        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(tr1);
        pool.execute(tr2);
        pool.execute(tr3);
        //
        // tr1.start();
        // tr2.start();
        // tr3.start();
        pool.shutdown();
    }
}
