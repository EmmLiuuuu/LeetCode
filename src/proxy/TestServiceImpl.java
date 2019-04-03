package proxy;

public class TestServiceImpl implements TestService {
    @Override
    public void doSomething1() {
        System.out.println("Inner doSomeThing1");
        doSomeThing2();
    }

    @Override
    public void doSomeThing2() {
        System.out.println("Inner doSomeThing2");
    }
}
