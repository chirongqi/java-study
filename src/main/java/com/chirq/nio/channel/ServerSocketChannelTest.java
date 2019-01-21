package com.chirq.nio.channel;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * <b>类名</b>：ServerSocketChannelTest<br>
 * <p>
 * <b>标题</b>：TODO
 * </p>
 * <p>
 * <b>公司</b>：weimob.com
 * </p>
 *
 * @author <font color='blue'>rongqi.chi</font>
 * @date 18:39
 */
public class ServerSocketChannelTest {

    public static void main(String[] args) throws Exception{
        blockingServiceSocket();
    }

    /**
     * 阻塞式socket服务监听
     */
    public static void blockingServiceSocket() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();

            //do something with socketChannel...
        }
    }

    /**
     * 非阻塞式socket服务监听
     */
    public static void notlockingServiceSocket() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                //do something with socketChannel...

            }
        }
    }

}
