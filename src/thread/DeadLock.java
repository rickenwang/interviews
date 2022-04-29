package thread;

// 模拟死锁
public class DeadLock {

    int count = 0;
    final Object lock1 = new Object();
    final Object lock2 = new Object();

    public static void main(String[] args) {

        DeadLock deadLock = new DeadLock();

        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (deadLock.lock1) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (deadLock.lock2) {

                    }

                }

            }
        }, "ThreadOdd").start();



        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (deadLock.lock2) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (deadLock.lock1) {

                    }
                }
            }
        }, "ThreadEven").start();

    }

    int getCount() {
        return count++;
    }

}
