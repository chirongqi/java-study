package com.chirq.mina;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaTimeClient {
    private static final int PORT = 6488;

    public static void main(String[] args) throws Exception {

        ExecutorService pool = Executors.newCachedThreadPool();
        ClientT t = new ClientT();
        for (int j = 0; j < 50; j++) {
            pool.execute(new Thread(t));
        }
        pool.shutdown();

        TimeClientHandler clientHandler = new TimeClientHandler();
        IoConnector connector = new NioSocketConnector();
        // 设置处理类
        connector.setHandler(clientHandler);
        ConnectFuture connFuture = connector.connect(new InetSocketAddress("localhost", PORT));
        connFuture.awaitUninterruptibly();
        IoSession session = connFuture.getSession();
        clientHandler.sessionOpened(session);
        System.out.println("TCP 客户端启动");

        for (int j = 0; j < 2; j++) { // 发送两遍
            byte[] bts = new byte[20];
            for (int i = 0; i < 20; i++) {
                bts[i] = (byte) i;
            }
            IoBuffer buffer = IoBuffer.allocate(20);
            // 自动扩容
            buffer.setAutoExpand(true);
            // 自动收缩
            buffer.setAutoShrink(true);
            buffer.put(bts);
            // buffer.putString("客户机请求服务端。。。", "UTF-8");
            // buffer.putObject("去屎 ");
            buffer.flip();
            // session.write(buffer);
            session.write("你好");
            // Thread.sleep(5000);
        }
        // 关闭会话，待所有线程处理结束后
        connector.dispose(true);

    }

    static class ClientT implements Runnable {
        @Override
        public void run() {
            TimeClientHandler clientHandler = new TimeClientHandler();
            IoConnector connector = new NioSocketConnector();
            // 设置处理类
            connector.setHandler(clientHandler);
            ConnectFuture connFuture = connector.connect(new InetSocketAddress("localhost", PORT));
            connFuture.awaitUninterruptibly();
            IoSession session = connFuture.getSession();
            try {
                clientHandler.sessionOpened(session);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("TCP 客户端启动");

            for (int j = 0; j < 500; j++) { // 发送两遍
                byte[] bts = new byte[20];
                for (int i = 0; i < 20; i++) {
                    bts[i] = (byte) i;
                }
                IoBuffer buffer = IoBuffer.allocate(20);
                // 自动扩容
                buffer.setAutoExpand(true);
                // 自动收缩
                buffer.setAutoShrink(true);
                buffer.put(bts);
                // buffer.putString("客户机请求服务端。。。", "UTF-8");
                // buffer.putObject("去屎 ");
                buffer.flip();
                session.write(buffer);
                // Thread.sleep(5000);
            }
            // 关闭会话，待所有线程处理结束后
            connector.dispose(true);

        }

    }
}
