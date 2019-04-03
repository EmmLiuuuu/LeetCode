package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class NioServer {

    //类似于io多路复用中的select
    public static void main(String[] args) throws IOException, InterruptedException {
        Selector selector = Selector.open();

        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(8080));//监听本地8080端口
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_ACCEPT);//监听是否接收到一个tcp连接

        boolean flag = false;
        while (true) {
            int ready = selector.select();

            if (ready == 0) {//如果没有事件发生
                continue;
            }

            if (ready < 0) {//如果连接中断
                break;
            }

            Set<SelectionKey> keys = selector.selectedKeys();//遍历所有事件

            for (SelectionKey key : keys) {
                if (key.isAcceptable()) {
                    SocketChannel client = channel.accept();//接收到一个连接
                    client.configureBlocking(false);//非阻塞
                    client.register(selector, SelectionKey.OP_READ);//接收到一个连接不代表数据已经发送（可读），注册selector，监听是否数据已经可读
                    keys.remove(key);//移除已处理的事件
                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel)key.channel();
                    ByteBuffer read = ByteBuffer.allocateDirect(1024);
                    int num;
                    StringBuilder builder = new StringBuilder();
                    byte[] bytes = new byte[1024];//读取接收到的数据
                    while ((num = client.read(read)) > 0) {
                        read.flip();
                        read.get(bytes, 0, num);
                        builder.append(new String(bytes, 0, num, StandardCharsets.UTF_8));
                        read.clear();
                    }

                    System.out.println("收到client发送的：" + builder.toString());

                    if (builder.toString().equals("关闭连接吧")) {
                        flag = true;
                        client.close();
                        break;
                    }

                    Thread.sleep(2000);//做一个延迟测试

                    read.put("Server已经接收到数据了！".getBytes(StandardCharsets.UTF_8));
                    read.flip();

//                    client.configureBlocking(false);//非阻塞
                    client.write(read);//服务端回应，阻塞

                    Thread.sleep(1000);

//                    client.close();//等数据发送完成后关闭通道,懒得写带资源的try了


                    keys.remove(key);
                }
            }

            if (flag) {
                break;
            }
        }

        channel.close();
        System.out.println("finish");
    }
}
