package classloader;

// javac -d . LoaderTest.java // 以带包名的方式来编译 java 文件
// java classloader.LoaderTest  // 执行带包名的 class 文件

import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoaderTest {

    public static void main(String[] args) {
        System.out.println("I'm a loader");
        BlockingQueue<String> taskQueue = new LinkedBlockingDeque<>(10);

        Executor executor = Executors.newFixedThreadPool(3);
        executor.execute(new Runnable() {
            @Override
            public void run() {

                int i = 0;
                while (true) {
                    try {
                        taskQueue.put("task" + i++);
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("get " + taskQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
