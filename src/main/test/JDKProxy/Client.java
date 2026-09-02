package JDKProxy;

import java.lang.reflect.Proxy;

/**
 * 在项目的vmOptions中添加-Dsun.misc.ProxyGenerator.saveGeneratedFiles=true，可以保存生成的Proxy代理对象
 */
public class Client {
    public static void main(String[] args) {
        InvocationHandlerImpl handler = new InvocationHandlerImpl(new TuShuGuan());
        Object o = Proxy.newProxyInstance(TuShuGuan.class.getClassLoader(), new Class[]{ChuBanShe.class}, handler);
        ChuBanShe o1 = (ChuBanShe) o;
        o1.sellBook();
        o1.buyPaper();
    }
}
