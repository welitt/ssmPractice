package function;

public class AirPlane implements Fly {
    @Override
    public void fly(String name) {
        System.out.println(String.format("我消耗燃油，我是%s，我会飞",name));
    }
}
