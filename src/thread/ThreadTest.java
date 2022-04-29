package thread;

import utils.Utils;

public class ThreadTest {

    private static boolean sign = false;
    private static final Object lock = new Object();


    public static void main(String[] args) {

        // testWaitAndNotifyAll();
    }

    private static void testInterrupt() {

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 100000; i++) {
                    Utils.log(i);
                }

                if (Thread.interrupted()) {
                    return;
                }
            }
        });
        thread.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 强制杀死线程，相当于关电源
        thread.stop();

        // 添加一个中断标记，实际要通过 if (Thread.interrupted())
        // 来判断是否需要主动退出线程
        thread.interrupt();
    }



}
