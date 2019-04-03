package thread;

public class Dao implements Runnable {

    private DaoService daoService;

    public Dao(DaoService daoService) {
        this.daoService = daoService;
    }

    @Override
    public void run() {
        int i = 0;
//        for (; i < 50000000; i++) {
////            if (daoService.get() != 0) {
////                daoService.set();
////            } else {
////                break;
////            }
//            daoService.set();
//        }
        while (daoService.get() > 0) {
            daoService.set();
            i++;
        }
        System.out.println(i);
        System.out.println(daoService.get());
    }
}
