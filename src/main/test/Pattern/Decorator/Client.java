package Pattern.Decorator;

/**
 * 装饰器模式 透明模式
 */
public class Client {

    public static void main(String[] args) {
        Decorator decorator = new FlyModel(new Car());
        decorator.run();
        Decorator decorator2 = new RobotModel(decorator);
        decorator2.run();
    }
}
