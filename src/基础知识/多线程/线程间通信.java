package 基础知识.多线程;

// java 通过 wait 和 notify/notifyAll 来进行线程间通信
//
public class 线程间通信 {

    private boolean isMeet = false;

    public static void main(String[] args) {

        //
        线程间通信 test = new 线程间通信();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.waitForCondition();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                    test.meetCondition();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 等待条件满足时执行任务
    private synchronized void waitForCondition() throws InterruptedException {

        while (!isMeet) {
            wait(); // 调用 wait() 后释放锁，并将当前线程挂起
        }

        System.out.println("条件满足，我执行完了");
    }

    private synchronized void meetCondition() {

        isMeet = true;
        System.out.println("修改满足条件");
        notifyAll(); // 释放锁，通知其他线程
    }
}
