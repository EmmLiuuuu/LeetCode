package aio;

public class TimeClient {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new AsyncTimeClientHandler("localhost", 8081), "AIO-AsyncTimeClientHandler-00" + i).start();
        }
    }
}
