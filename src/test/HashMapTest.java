package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class HashMapTest {


    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();


        HashMap<String, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.put(""+i, "" + i);
        }

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<String, String> entry = iterator.next();
            if (entry.getKey().equals("5")) {
                map.remove("5");
            }
        }
    }
}
