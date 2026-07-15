package ShiLiHua;

public class Cat implements Furry{
    private static String name = "猫";

    protected int fat = 2;

    static {
        System.out.println("我是静态"+ name);
    }

    {
        System.out.println("猫的初始代码块是喵喵喵");
    }

    public Cat() {
        System.out.println("猫初始化了，体重"+ fat);
    }

    @Override
    public void getColor() {
        System.out.println("Any Color");
    }

    @Override
    public String getTixing() {
        return "无形";
    }
}
