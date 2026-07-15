package Pattern.Decorator;

public class Car implements Component{

    @Override
    public void run() {
        System.out.println("我是一辆车");
    }
}
