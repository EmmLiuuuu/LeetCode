package nio;

public class TimeClient {

    public static void main(String[] args) {
        int port = 8081;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException ignored) {

            }
        }

        new Thread(new TimeClientHandle("localhost", port), "TimeClient-001").start();
    }
}
