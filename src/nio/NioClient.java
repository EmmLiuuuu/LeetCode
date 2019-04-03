package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class NioClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Selector selector = Selector.open();
        SocketChannel client = SocketChannel.open();

        client.connect(new InetSocketAddress("localhost",8080));//连接本地8080

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);//创建堆外buffer
        byteBuffer.put("from client".getBytes(StandardCharsets.UTF_8));//往buffer写入
        byteBuffer.flip();//因为下面要开始读buffer的内容了，将buffer转换成读模式
        client.write(byteBuffer);//读buffer内容，往通道写入buffer的内容

        client.configureBlocking(false);//设置为非阻塞
        client.register(selector, SelectionKey.OP_READ);//监听通道中是否有消息可读

        long start = System.currentTimeMillis();//计时

        while (true) {
            int ready = selector.select();//
            if (ready == 0) {//如果没有事件发生
                continue;
            }

            if (ready < 0) {//如果连接中断
                break;
            }

            Set<SelectionKey> keys = selector.selectedKeys();//遍历事件

            for (SelectionKey key : keys) {
                if (key.isReadable()) {//如果该事件可读
                    SocketChannel channel = (SocketChannel) key.channel();//获取通道
                    ByteBuffer read = ByteBuffer.allocateDirect(1024);//分配堆外buffer
                    int num;
                    byte[] bytes = new byte[1024];//用来读取数据
                    StringBuilder builder = new StringBuilder();
                    while ((num = channel.read(read)) > 0) {//往buffer中写入信息
                        read.flip();//转换成读模式，limit = position， position = 0
                        read.get(bytes, 0, num);//bytes读取buffer中的信息
                        builder.append(new String(bytes, 0, num, StandardCharsets.UTF_8));
                        read.clear();//为下一次信息写入buffer做好准备：position = 0，limit = capacity
                    }

                    System.out.println("收到server的回应：" + builder.toString());
                    System.out.println("耗时：" + (System.currentTimeMillis() - start) / 1000.0 + "s");

                    read.put("关闭连接吧".getBytes(StandardCharsets.UTF_8));
                    read.flip();
//                    channel.configureBlocking(false); //非阻塞
                    channel.write(read);//阻塞发送信息
                    keys.remove(key);//移除该事件

                    Thread.sleep(1000);//等待信息发送完成
                    channel.close();
                    break;
                }
            }
            break;
        }
        client.close();
    }
}
