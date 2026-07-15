package Pattern.Decorator;

public class FlyModel extends Decorator{

    public FlyModel(Component component) {
        super(component);
    }

    @Override
    public void run() {
        super.run();
        fly();
    }

    public void fly(){
        System.out.println("飞行......");
    }
}
