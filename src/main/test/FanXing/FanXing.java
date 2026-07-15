package FanXing;

import java.util.ArrayList;
import java.util.List;

public class FanXing {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        l1.add("aaa");
        l2.add(1);

        System.out.println(l1.getClass());
        System.out.println(l2.getClass());
        System.out.println(l1.getClass().equals(l2.getClass()));

        PaoLu<String> paolu = new PaoLu<String>();
        System.out.println(paolu.getT());
    }
}
