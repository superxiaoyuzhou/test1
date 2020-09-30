package com.piter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Demo1 {


    public static void main(String[] args) throws Exception {

        File file = new File(Demo1.class.getClassLoader().getResource("nio-data.txt").toURI());
        RandomAccessFile aFile = new RandomAccessFile(file, "rw");
        FileChannel inChannel = aFile.getChannel();
        //直接内存中分配
        ByteBuffer buf = ByteBuffer.allocate(1024);

        int bytesRead = inChannel.read(buf);

        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);

            buf.flip();

            while(buf.hasRemaining()){
                System.out.println((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }

        buf.put("123".getBytes());
        buf.flip();
        while(buf.hasRemaining()){
            System.out.println((char) buf.get());
        }
        aFile.close();
        //注意 buf.flip() 的调用，首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据
    }
}
