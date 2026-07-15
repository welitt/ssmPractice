package Pattern.Decorator;

public class RobotModel extends Decorator{
    public RobotModel(Component component) {
        super(component);
    }

    @Override
    public void run() {
        super.run();
        say();
    }

    public void say(){
        System.out.println("say something~ quick");
    }
}
