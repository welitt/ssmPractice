import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class Test01 {
    @Test
    public void test1(){
        int p = 4;
        //            cap: 00000000 00000000 00000000 00001111
        //     ~cap表示取反: 11111111 11111111 11111111 11110000
        int cap = (1 << 4 )- 1;
        System.out.println(1 << 4);
        System.out.println(Integer.toString(~cap,2));
        // 00000000 00000000 00000000 01000000
        // 11111111 11111111 11111111 11110000
        // 作用是不要低4位，永远大于2^4 - 1，取得高位
        System.out.println(1 << 6 & ~cap);
        // 00000000 00000000 00000000 01000000
        // 00000000 00000000 00000000 00001111
        // 作用是只要低4位,永远小于2^4 - 1，取得低位
        System.out.println(1 << 6 & cap);

        System.out.println(12 & ~cap); // 0
        System.out.println(12 & cap);  // 12

        System.out.println(22 & ~cap); // 16
        System.out.println(22 & cap); // 6

        // 作用把负数拆成比它小的负数，和正数相加
        //    原码：10000000 00000000 00000000 00010110
        //         11111111 11111111 11111111 11101001
        //    补码：11111111 11111111 11111111 11101010
        //   ~cap：11111111 11111111 11111111 11110000
        // res补码：11111111 11111111 11111111 11100000
        //         10000000 00000000 00000000 00100000
        System.out.println(-22 & ~cap); // -32
        //   cap ：00000000 00000000 00000000 00001111
        // res补码：00000000 00000000 00000000 00001010
        System.out.println(-22 & cap); // 10

        System.out.println(-67 & ~cap); // -80
        System.out.println(-67 & cap);  // 13

        // 前
        //    原码：10100000 00000000 00000000 00000000
        //    补码：11100000 00000000 00000000 00000000
        //后
        //    源码：00011111 11111111 11111111 11111111
        // res补码：00000000 00000000 00000000 00000000
        System.out.println((-1 << 29) & ((1 << 29) - 1));
    }


    /*
    最长公共子序列
     X=<x1, x2, …, xm>
     Y=<y1, y2, …, yn>
     最长公共子序列 Z=<z1, z2, …, zk>
     */
    @Test
    public void test2(){
        String str1 = "BDCABA";
        String str2 = "ABCBDAB";
        int[][] dp = new int[str1.length()+1][str2.length()+1];
        dp[0][0] = 0;
        for(int i=1;i<=str1.length();i++){
            for(int j=1;j<=str2.length();j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[str1.length()][str2.length()]);
    }

    @Test
    public void test3(){
        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY   = (1 << COUNT_BITS) - 1; // 0、29个1

        // runState is stored in the high-order bits
        int RUNNING    = -1 << COUNT_BITS; // 负的 1、29个0

        AtomicInteger ctl = new AtomicInteger((RUNNING | 0));
        System.out.println(ctl.get() & ~CAPACITY); // ~cpacity = 1、29个0
    }

    /**
     * ~表示取反、x << y 表示将x向左移动y位、-x << y 表示将x向左移动y位后对应的负数，如：128对应-128
     * 创建线程池的时候就会创建一个workQueue
     * excute一个Runnable command的过程
     * 1.如果ctl小于核心线程池：
     *  1）判断(wc >= CAPACITY ||
     *                     wc >= (core ? corePoolSize : maximumPoolSize)),通过CompareAndSet将ctl+1；
     *  2）新建Work对象，加入所有HashSet<Worker> workers（包含池内所有工作线程的set）中，加入成功后启动此新建的Work对象线程；
     *      2.1）工作线程获取自己的任务，自己的任务为空则：从BlockingQueue<Runnable> workQueue（起到持有任务并移交给工作线程的作用）中获取任务
     *
     * 2.if (isRunning(c) && workQueue.offer(command)) // 如果ctl小于0，尝试向workQueue中插入元素Runnable command
     *  1）再次获取ctl
     *  2）if (ctl>=0 && 从workQueue中移除此command成功) ，那么 reject(command)
     *  3）else if(ctl ==0 )，那么走addWorker(firstTask null, 核心线程false)流程
     *
     * 3.else if (!addWorker(command, false)) // 添加非核心线程失败 ， 那么reject(command)
     */

}
