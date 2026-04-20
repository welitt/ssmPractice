package function;

public class TestFly {
    public static void main(String[] args) {
        FlyThing<Fly,String> flyThing = new FlyThing<>(new Bird());
        flyThing.aFlyThing("鸟",Fly::fly);

        FlyThing<Fly,String> flyThing2 = new FlyThing<>(new AirPlane());
        flyThing2.aFlyThing("飞机",Fly::fly);
    }
}
