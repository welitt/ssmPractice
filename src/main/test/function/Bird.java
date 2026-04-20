package function;

public class Bird implements Fly {
    @Override
    public void fly(String name) {
        System.out.println(String.format("我吃谷子，我是%s，我会飞",name));
    }
}
