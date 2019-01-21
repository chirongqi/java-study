package com.chirq.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * <b>类名</b>：ClientSocketChannelTest<br>
 * <p>
 * <b>标题</b>：TODO
 * </p>
 * <p>
 * <b>公司</b>：weimob.com
 * </p>
 *
 * @author <font color='blue'>rongqi.chi</font>
 * @date 18:14
 */
public class ClientSocketChannelTest {

    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        try {
             socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 6488));

            ByteBuffer writeBuffer = ByteBuffer.allocate(48);
            writeBuffer.clear();
            writeBuffer.put("hello".getBytes());
            writeBuffer.flip();
            while(writeBuffer.hasRemaining()) {
                socketChannel.write(writeBuffer);
            }


            ByteBuffer redBuffer = ByteBuffer.allocate(48);
            socketChannel.read(redBuffer);
            System.out.println(redBuffer.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
