package Xianchengchi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * new ThreadPoolExecutor.AbortPolicy() 等待队列满了，直接抛异常，不要了
 * new ThreadPoolExecutor.CallerRunsPolicy() 等待队列满了，被拒绝的新线程会在发起execute的线程中执行,在这里就是main线程
 * new ThreadPoolExecutor.DiscardOldestPolicy() 会把等待队列中最老的踢走，再把新的线程补充到队列末端；
 * new ThreadPoolExecutor.DiscardPolicy() 等待队列满了，再加新线程直接不要了
 */
public class Executer2 {
    private int present = 10;
    private final Object obj = new Object();
    ExecutorService executorService = new ThreadPoolExecutor(3,3,10,
            TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(2),new ThreadPoolExecutor.CallerRunsPolicy());

    private void startLockyDraw(){
        Thread thread1 = new Thread(() -> {
            System.out.println("1我开始排队");
            synchronized (obj) {
                if (present > 0) {
                    present--;
                    System.out.println(Thread.currentThread().getName() + "：我抢到礼物了！！！");
                } else {
                    System.out.println("啥也没有");
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("2我开始排队");
            synchronized (obj) {
                if (present > 0) {
                    present--;
                    System.out.println(Thread.currentThread().getName() + "：我抢到礼物了！！！");
                } else {
                    System.out.println("啥也没有");
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread3 = new Thread(() -> {
            System.out.println("3我开始排队");
            synchronized (obj) {
                if (present > 0) {
                    present--;
                    System.out.println(Thread.currentThread().getName() + "：我抢到礼物了！！！");
                } else {
                    System.out.println("啥也没有");
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread4 = new Thread(() -> {
            System.out.println("4我开始排队");
            synchronized (obj) {
                if (present > 0) {
                    present--;
                    System.out.println(Thread.currentThread().getName() + "：我抢到礼物了！！！");
                } else {
                    System.out.println("啥也没有");
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread5 = new Thread(() -> {
            System.out.println("5我开始排队");
            synchronized (obj) {
                if (present > 0) {
                    present--;
                    System.out.println(Thread.currentThread().getName() + "：我抢到礼物了！！！");
                } else {
                    System.out.println("啥也没有");
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread6 = new Thread(() -> {
            System.out.println("6我开始排队");
            synchronized (obj) {
                if (present > 0) {
                    present--;
                    System.out.println(Thread.currentThread().getName() + "：我抢到礼物了！！！");
                } else {
                    System.out.println("啥也没有");
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread7 = new Thread(() -> {
            System.out.println("7我开始排队");
            synchronized (obj) {
                if (present > 0) {
                    present--;
                    System.out.println(Thread.currentThread().getName() + "：我抢到礼物了！！！");
                } else {
                    System.out.println("啥也没有");
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread8 = new Thread(() -> {
            System.out.println("8我开始排队");
            synchronized (obj) {
                if (present > 0) {
                    present--;
                    System.out.println(Thread.currentThread().getName() + "：我抢到礼物了！！！");
                } else {
                    System.out.println("啥也没有");
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.execute(thread3);
        executorService.execute(thread4);
        executorService.execute(thread5);
        executorService.execute(thread6);
        executorService.execute(thread7);
        executorService.execute(thread8);
    }

    public static void main(String[] args) {
        Executer2 executer1 = new Executer2();
        executer1.startLockyDraw();
    }
}
