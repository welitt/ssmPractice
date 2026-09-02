package JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerImpl implements InvocationHandler {
    private Object obj;
    public InvocationHandlerImpl(ChuBanShe chuBanShe) {
        this.obj = chuBanShe;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(obj, args);
        if(method.getName().equals("sellBook")) {
            addCnt();
        } else if (method.getName().equals("buyPaper")) {
            quickBuyPaper();
        }

        System.out.println(method.getName());
        return null;
    }

    private void quickBuyPaper() {
        System.out.println("快点快点去买纸");
    }

    public void addCnt(){
        System.out.println("我卖出100本");
    }

}
