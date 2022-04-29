package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {


    public static void log(String message) {
        System.out.println(String.format("%s: %s",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()), message));
    }

    public static void log(int message) {
        System.out.println(String.format("%s: %d",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()), message));
    }
}
