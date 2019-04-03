package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class NioTest {

    public static void main(String[] args) throws IOException {
//        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10);
//
//        FileInputStream inputStream = new FileInputStream("/Users/masakall/IdeaProjects/LeetCode/src/sql/LeetCodeSQL_177.sql");
//
//        FileChannel channel = inputStream.getChannel();
//
//        FileOutputStream outputStream = new FileOutputStream("/Users/masakall/IdeaProjects/LeetCode/src/sql/text.txt");
//        FileChannel out = outputStream.getChannel();
//
////        String s = "test";
////        byteBuffer.put(s.getBytes());
////
////        channel.write(byteBuffer);
//
//        while (channel.read(byteBuffer) > 0) {
////            System.out.print(byteBuffer);
//            byteBuffer.flip();
//            out.write(byteBuffer);
//            byteBuffer.clear();
//        }

//        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//        SocketChannel socketChannel = SocketChannel.open();
//        socketChannel.connect(new InetSocketAddress("http://www.javadoop.com", 80));
//        while (socketChannel.read(byteBuffer) > 0) {
//            System.out.print(new String(byteBuffer.array()));
//            byteBuffer.clear();
//        }  连接失败，不知道为什么

        String newData = "New String to write to file..."
                + System.currentTimeMillis();

        ByteBuffer buf = ByteBuffer.allocate(48);




        Selector selector = Selector.open();
        ByteBuffer getBuf = ByteBuffer.allocate(43);
        DatagramChannel get;
        get = DatagramChannel.open();
        get.configureBlocking(false);
        get.socket().bind(new InetSocketAddress("localhost",8080));
        get.register(selector, SelectionKey.OP_READ);




        //正确
        CompletableFuture.runAsync(() -> {
            DatagramChannel channel = null;
            try {
                channel = DatagramChannel.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
//                DatagramChannel channel = null;//too many open files
//                try {
//                    channel = DatagramChannel.open();//原因在这，每发一次就开一个文件
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                buf.clear();
                buf.put(("New String to write to file..."
                        + System.currentTimeMillis()).getBytes());
                buf.flip();
                int bytesSent = 0;
                try {
                    bytesSent = channel.send(buf, new InetSocketAddress("localhost", 8080));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                System.out.println(bytesSent);
            }
        });

        while (true) {
            int ready = selector.select();
            if (ready == 0) {
                continue;
            }
            Set<SelectionKey> keys = selector.selectedKeys();

            while (keys.iterator().hasNext()) {
                SelectionKey key = keys.iterator().next();
                if (key.isReadable()) {
                    get.receive(getBuf);
                    System.out.println(new String(getBuf.array()));
                }
                keys.remove(key);
            }
//            break;
        }


        //udp发送
//        DatagramChannel channel = null;
//        try {
//            channel = DatagramChannel.open();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        buf.clear();
//        buf.put(("New String to write to file..."
//                + System.currentTimeMillis()).getBytes());
//        buf.flip();
//        int bytesSent = 0;
//        try {
//            bytesSent = channel.send(buf, new InetSocketAddress("localhost", 8080));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        System.out.println(bytesSent);
//        //udp的接收
//        CompletableFuture.runAsync(() -> {
//            ByteBuffer getBuf = ByteBuffer.allocate(43);
//            DatagramChannel get = null;
//            try {
//                get = DatagramChannel.open();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                get.socket().bind(new InetSocketAddress("localhost",8080));
//            } catch (SocketException e) {
//                e.printStackTrace();
//            }
//            try {
//                get.receive(getBuf);//阻塞
//                System.out.println(new String(getBuf.array()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });






    }
}
