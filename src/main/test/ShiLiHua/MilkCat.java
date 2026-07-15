package ShiLiHua;

public class MilkCat extends Cat{
    private static String name = "奶牛猫";

    private int courage = 100;

    static {
        System.out.println("我是静态"+ name);
    }

    {
        System.out.println("奶牛猫的初始代码块是发神经");
    }

    public MilkCat() {
        System.out.println("奶牛猫初始化了，体重"+ super.fat + "，勇气值"+courage);
    }

    @Override
    public void getColor() {
        System.out.println("Black and White");
    }

    public static void main(String[] args) {
        MilkCat cat1 = new MilkCat();
        MilkCat cat2 = new MilkCat();
        cat1.getColor();
        cat2.getColor();
        System.out.println(cat2.getTixing());
        Cat cat3 = new Cat();
        cat3.getColor();
        System.out.println(cat3.getTixing());
    }
}
