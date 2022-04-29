package thread;

import jdk.nashorn.internal.ir.Block;
import utils.Utils;

// 如何通过 notify 和 wait 来实现两个线程交替打印基数和偶数
public class NotifyAndWait {

    private static boolean sign = false;
    private static final Object lock = new Object();
    int count = 0;
    ThreadLocal<Integer> tCount = new ThreadLocal<>();

    public static void main(String[] args) {

        NotifyAndWait notifyAndWait = new NotifyAndWait();

        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (notifyAndWait) {
                    int count = notifyAndWait.getCount();
                    System.out.println("ThreadOdd: " + count);

                    // 其他人快起床干活，我马上要睡觉并且释放锁了
                    notifyAndWait.notifyAll();

                    try {
                        // 好了，我睡了，锁也给你们吧
                        notifyAndWait.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, "ThreadOdd").start();



        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (notifyAndWait) {

                    int count = notifyAndWait.getCount();
                    System.out.println("ThreadEvent: " + count);

                    notifyAndWait.notifyAll();

//                    try {
//                        notifyAndWait.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }



            }
        }, "ThreadEven").start();

    }

    int getCount() {
        return count++;
    }


    // 线程A等待线程B给 sign 赋值，一旦赋值后，都打印线程结束
    private static void testWaitAndNotifyAll() {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (lock) {
                    Utils.log("threadA get monitor");
                    if (!sign) {
                        try {

                            Utils.log("threadA release monitor");
                            // wait 的意思是我先睡了，锁我也不要了，你们其他人自己看着办
                            lock.wait();

                            // 线程被唤醒，这里重新拿到了锁
                            Utils.log("threadA get monitor");
                            Thread.sleep(1000);
                            Utils.log("threadA sleep 1s complete");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // 这里最终释放了锁
                    Utils.log("threadA complete");
                }

            }
        });
        threadA.start();

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock) {
                    Utils.log("threadB get monitor");
                    sign = true;
                    Utils.log("threadB notify all");
                    lock.notifyAll();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Utils.log("threadB release monitor");
                }
                Utils.log("threadB complete");
            }
        });
        threadB.start();
    }
}
