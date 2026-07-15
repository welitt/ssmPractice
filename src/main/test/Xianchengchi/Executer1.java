package Xianchengchi;

import java.util.concurrent.*;

public class Executer1 {
    private int present = 10;
    private final Object obj = new Object();
    ExecutorService executorService = new ThreadPoolExecutor(3,3,10,
            TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(3),new ThreadPoolExecutor.DiscardPolicy());

    private void startLockyDraw(){

        for(int i=0;i<100;i++){
            System.out.println(i);
            executorService.execute(new Thread(() -> {
                System.out.println("我开始排队");
                synchronized (obj){
                    if(present > 0){
                        present--;
                        System.out.println(Thread.currentThread().getName() + "：我抢到礼物了！！！");
                    }else{
                        System.out.println("啥也没有");
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }));
        }
    }

    public static void main(String[] args) {
        Executer1 executer1 = new Executer1();
        executer1.startLockyDraw();
    }
}
