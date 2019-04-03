package thread;

public class DaoService {

     volatile int a = 0;
     int b = 100000000;
    int c = 0;

    public void set() {
        this.b--;
//        a = b;
    }

    public int get() {
//        c = a;
        return this.b;
    }
}
