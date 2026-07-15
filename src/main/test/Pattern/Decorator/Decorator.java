package Pattern.Decorator;

public abstract class Decorator implements Component{
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void run() {
        component.run();
    }

}
