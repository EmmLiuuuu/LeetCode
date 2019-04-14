package aio;

import java.io.IOException;
import java.nio.channels.AsynchronousServerSocketChannel;

public class AioServer {

    public static void main(String[] args) throws IOException {
        int port = 8081;

        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException ignored) {

            }
        }

        AsyncTimeServerHandler timeServerHandler = new AsyncTimeServerHandler(port);
        new Thread(timeServerHandler, "AIO_AsyncTimeServerHandler-001").start();
    }
}
