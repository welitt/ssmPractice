package OrderThread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 让线程执行有顺序
 */
public class ThreadPark {
    /**
     * 有卡住的风险
     * 变量a的作用就是让线程1和线程3有序执行
     * @throws InterruptedException
     */
    public void park123() throws InterruptedException {
        String a = "";
        Thread thread1 =  new Thread(() ->{
            while(true){
                synchronized (a){
                    LockSupport.park();
                    System.out.println("1");
                    a.notify();
                    try {
                        a.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread thread2 =  new Thread(() ->{
            while(true){
                System.out.println("2");
                LockSupport.unpark(thread1);
                LockSupport.park();
            }
        });

        Thread thread3 =  new Thread(() ->{
            while(true){
                synchronized (a){
                    System.out.println("3");
                    LockSupport.unpark(thread2);
                    a.notify();
                    try {
                        a.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        thread2.start();
        Thread.sleep(3000);
        thread1.start();
        thread3.start();
    }

    /**
     * 测试通过
     * @throws InterruptedException
     */
    public void blockingQueue() throws InterruptedException {
        LinkedBlockingQueue<Integer> queue1 = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Integer> queue2 = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Integer> queue3 = new LinkedBlockingQueue<>();
        Thread thread1 =  new Thread(() ->{
            while(true){
                try {
                    Integer i = queue1.take();
                    System.out.println(i);
                    queue3.add(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 =  new Thread(() ->{
            while(true){
                try {
                    Integer i = queue2.take();
                    System.out.println(i);
                    queue1.add(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread3 =  new Thread(() ->{
            while(true){
                try {
                    Integer i = queue3.take();
                    System.out.println(i);
                    queue2.add(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        queue3.add(3);
        thread2.start();
        thread1.start();
        thread3.start();
    }

    /**
     * 说明在持有l之后再park根本不会释放l
     */
    public void parkWhetherReleseLock(){
        String l = "";
        Thread t1 = new Thread(() -> {
                synchronized (l){
                    System.out.println("t1有锁了");
                    LockSupport.park();
                    System.out.println("t1睡大觉刚醒");
                }
        });

        Thread t2 = new Thread(() -> {
                synchronized (l){
                    System.out.println("t2有锁了");
                    LockSupport.park();
                    System.out.println("t2睡大觉刚醒");
                }
        });
        t1.start();
        t2.start();
    }

    public static void main(String[] args) throws InterruptedException {
        new ThreadPark().blockingQueue();
    }
}
