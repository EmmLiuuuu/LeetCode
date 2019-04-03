package proxy;

public class TestServiceProxy implements TestService {

    private TestService testService;

    public TestServiceProxy(TestService testService) {
        this.testService = testService;
    }

    public void proxy() {
        System.out.println("before fun call");
    }

    public void proxy1() {
        System.out.println("after fun call");
    }

    @Override
    public void doSomething1() {
        proxy();

        testService.doSomething1();

        proxy1();

    }

    @Override
    public void doSomeThing2() {

        proxy();

        testService.doSomeThing2();

        proxy1();

    }
}
