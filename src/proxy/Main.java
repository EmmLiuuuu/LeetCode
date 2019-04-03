package proxy;

public class Main {

    public static void main(String[] args) {
        TestService impl = new TestServiceImpl();

        TestServiceProxy proxy = new TestServiceProxy(impl);

        TestService bean = (TestService) proxy;

        bean.doSomething1();

    }
}
