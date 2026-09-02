package JDKProxy;

public class TuShuGuan implements ChuBanShe{
    @Override
    public void sellBook() {
        System.out.println("卖出一本设计模式");
    }

    @Override
    public void buyPaper() {
        System.out.println("买纸印刷");
    }
}
