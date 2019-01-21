package com.chirq.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <b>类名</b>：FileChannelTest<br>
 * <p>
 * <b>标题</b>：TODO
 * </p>
 * <p>
 * <b>公司</b>：weimob.com
 * </p>
 *
 * @author <font color='blue'>rongqi.chi</font>
 * @date 17:39
 */
public class FileChannelTest {

    public static void main(String[] args) throws Exception {
        copyFile1();
        copyFile2();
    }

    public static void copyFile1() throws Exception {
        long start = System.currentTimeMillis();
        String infile = "D:\\软件\\CentOS-7-x86_64-DVD-1810.iso";
        String outfile = "D:\\软件\\copy1.iso";
        // 获取源文件和目标文件的输入输出流
        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);
        // 获取输入输出通道
        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();
        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            // clear方法重设缓冲区，使它可以接受读入的数据
            buffer.clear();
            // 从输入通道中将数据读到缓冲区
            int r = fcin.read(buffer);
            // read方法返回读取的字节数，可能为零，如果该通道已到达流的末尾，则返回-1
            if (r == -1) {
                break;
            }
            // flip方法让缓冲区可以将新读入的数据写入另一个通道
            buffer.flip();
            // 从输出通道中将数据写入缓冲区
            fcout.write(buffer);
        }

        fin.close();
        fout.close();
        long end = System.currentTimeMillis();
        System.out.println((end - start));
    }

    public static void copyFile2() throws Exception {
        long start = System.currentTimeMillis();
        String infile = "D:\\软件\\CentOS-7-x86_64-DVD-1810.iso";
        String outfile = "D:\\软件\\copy2.iso";
        // 获取源文件和目标文件的输入输出流
        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);
        // 获取输入输出通道
        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();
        // 创建缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(1024);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        ByteBuffer[] byteBufferArr = new ByteBuffer[]{buffer1, buffer2};
        while (true) {
            // clear方法重设缓冲区，使它可以接受读入的数据
            buffer1.clear();
            buffer2.clear();
            // 从输入通道中将数据读到缓冲区
            long r = fcin.read(byteBufferArr);
            // read方法返回读取的字节数，可能为零，如果该通道已到达流的末尾，则返回-1
            if (r == -1) {
                break;
            }
            // flip方法让缓冲区可以将新读入的数据写入另一个通道
            buffer1.flip();
            buffer2.flip();

            // 从输出通道中将数据写入缓冲区
            fcout.write(byteBufferArr);
        }

        fin.close();
        fout.close();
        long end = System.currentTimeMillis();
        System.out.println((end - start));
    }

    public static void copyFile3() throws Exception {
        String infile = "D:\\软件\\CentOS-7-x86_64-DVD-1810.iso";
        String outfile = "D:\\软件\\copy.iso";
        // 获取源文件和目标文件的输入输出流
        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);
        // 获取输入输出通道
        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();

        fcin.transferTo(0, fcin.size(), fcout);
        //fcout.transferFrom(fcin, 0, fcin.size());

        fin.close();
        fout.close();
    }

}
