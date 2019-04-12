package decorator;

public class Test {
    public static void main(String[] args) {
        //首先把实现类new出来
        People component = new XiaoMing();

        //把具体装饰者new出来
        People component1 = new DecoratorA(component);
        People component2 = new DecoratorB(component1);
        component2.method();
    }
}
