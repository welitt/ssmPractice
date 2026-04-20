package function;

import java.util.function.BiConsumer;

public class FlyThing<T,U> {
    private T instance;

    public T getInstance() {
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }

    public FlyThing(T instance) {
        this.instance = instance;
    }

    public void aFlyThing(U name, BiConsumer<T, U> consumer) {
        // 这里测试的是BiConsumer代表的方法参数只有一个时，第一个参数代表实现类
        consumer.accept(instance,name);
    }
}
