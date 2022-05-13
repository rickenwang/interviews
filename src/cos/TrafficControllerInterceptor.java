package cos;

import java.util.concurrent.Semaphore;

public class TrafficControllerInterceptor {




    static class AggressiveTrafficStrategy extends TrafficStrategy {
        AggressiveTrafficStrategy(int maxConcurrent) {
            super(maxConcurrent, maxConcurrent);
        }
    }

    static class ModerateTrafficStrategy extends TrafficStrategy {
        ModerateTrafficStrategy(int maxConcurrent) {
            super(1, maxConcurrent);
        }
    }

    static class TrafficStrategy {

        private final int maxConcurrent;

        private int concurrent;

        private ResizableSemaphore controller;

        private final int POOR_NETWORK_SPEED = 100 * 1024;
        private final int STRONG_NETWORK_SPEED = 400 * 1024;

        TrafficStrategy(int concurrent, int maxConcurrent) {
            this.maxConcurrent = maxConcurrent;
            this.concurrent = concurrent;
        }

        synchronized public void reportSpeed(long speed) {

            if (speed < POOR_NETWORK_SPEED && concurrent > 1) {
                adaptConcurrentAndRelease(concurrent - 1);
            } else if (speed > STRONG_NETWORK_SPEED && concurrent < maxConcurrent) {
                adaptConcurrentAndRelease(concurrent + 1);
            }

        }

        public void waitForPermit() {
            try {
                controller.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        private void adaptConcurrentAndRelease(int expect) {

            int delta = concurrent - expect;

            if (delta > 0) {
                controller.release(delta + 1);
            } else if (delta < 0) {
                controller.reducePermits(-delta);
                controller.release();
            } else {
                controller.release();
            }
        }
    }


    static class ResizableSemaphore extends Semaphore {

        public ResizableSemaphore(int permits) {
            super(permits);
        }

        @Override
        protected void reducePermits(int reduction) {
            super.reducePermits(reduction);
        }
    }
}
