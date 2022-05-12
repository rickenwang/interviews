package 基础知识.多线程;

public class 两个线程交替打印 {

    public static void main(String[] args) {

        两个线程交替打印 test = new 两个线程交替打印();

        new Thread(new PrintRunnable("线程1:", test, 0)).start();
        new Thread(new PrintRunnable("线程2:", test, 1)).start();

    }



    static class PrintRunnable implements Runnable {

        private String name;
        private Object lock;
        private int delta = 0;
        private int count = 0;

        public PrintRunnable(String name, Object lock, int delta) {
            this.name = name;
            this.lock = lock;
            this.delta = delta;
        }

        @Override
        public void run() {

            while (count <= 50) {


                synchronized (lock) {

                    System.out.println(name + (2*count+delta));
                    lock.notifyAll();

                    if (count < 50) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
                count++;
            }

        }
    }


}
