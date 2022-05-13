package bytedance;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TrafficControllerInterceptor implements Interceptor {

    TrafficStrategy trafficStrategy = new TrafficStrategy(1,2);

    @Override
    public Response intercept(Chain chain) throws IOException {

        trafficStrategy.waitForPermit();

        try {
            Request request = chain.request();
            return chain.proceed(request);
        } finally {
            trafficStrategy.reportSpeed(100);
        }
    }

    static class TrafficStrategy {

        // 最大并发数
        private final int maxConcurrent;

        static final int SINGLE_THREAD_SAFE_SPEED = 100; // 单请求安全速度 100KB/S
        static final long BOOST_MODE_DURATION = TimeUnit.SECONDS.toNanos(3);

        private ResizableSemaphore controller;

        // 当前的并发数
        private AtomicInteger concurrent;

        public TrafficStrategy(int concurrent, int maxConcurrent) {
            this.controller = new ResizableSemaphore(concurrent);
            this.concurrent = new AtomicInteger(concurrent);
            this.maxConcurrent = maxConcurrent;
        }

        public void reportSpeed(long speed) {

            if (speed > 2.4 * maxConcurrent && concurrent.get() < maxConcurrent) {

                adjustConcurrentAndRelease(concurrent.get() + 1, true);

            } else if (speed < 0.7 * SINGLE_THREAD_SAFE_SPEED && concurrent.get() > 1) {

                adjustConcurrentAndRelease(concurrent.get() - 1, true);
            }
        }

        public void waitForPermit() {

            try {
                controller.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        /**
         * 调整并发数
         * @param expect 期望值
         */
        private synchronized void adjustConcurrentAndRelease(int expect, boolean release) {

            int delta = expect - concurrent.get();
            if (delta > 0) {
                controller.release(release? delta + 1: delta);
            } else if (delta < 0) {
                controller.reducePermits(-delta);
                if (release) {
                    controller.release();
                }
            } else  {
                if (release) {
                    controller.release();
                }
            }

            concurrent.set(expect);
        }
    }

    private static class ResizableSemaphore extends Semaphore {

        ResizableSemaphore(int permit) {
            super(permit, true);
        }

        @Override
        protected void reducePermits(int reduction) {
            super.reducePermits(reduction);
        }
    }

}
