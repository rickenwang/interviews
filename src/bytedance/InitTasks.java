package bytedance;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class InitTasks {







    static class InitTaskManager {

        private TreeMap<Integer, InitTask> mainTasks = new TreeMap<>();

        private TreeMap<Integer, InitTask> otherTasks = new TreeMap<>();

        private Map<String, Long> mainTaskConsumeTime = new HashMap<>();


        public InitTaskManager addInitTask(InitTask initTask) {

            checkTask(initTask);

            return this;
        }

        // 主线程调用
        public void start() {


        }


        public void reportConsumeTime() {


        }

        private void checkTask(InitTask initTask) {



        }

        private void runTask(InitTask initTask) {



        }


    }







    static class InitTask implements Runnable {

        private int name;

        private int priority;

        private int main;


        @Override
        public void run() {

        }
    }

}
