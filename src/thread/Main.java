package thread;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        DaoService daoService = new DaoService();
        Dao dao = new Dao(daoService);
        Dao dao1 = new Dao(daoService);

        Thread thread = new Thread(dao);
        thread.start();
//        thread.join();

        new Thread(dao1).start();


        thread.join();
    }
}
