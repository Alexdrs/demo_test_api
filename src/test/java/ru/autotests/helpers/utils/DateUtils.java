/**
 * @author Alexandr.Drasikov
 */
package ru.autotests.helpers.utils;

import java.util.Calendar;

/**
 * Вспомогательный класс для работы с датами
 */
public class DateUtils {

    public static String getTimeForLog(long timeStart, long timeFinish) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeFinish - timeStart);
        int minutes = (int) (((timeFinish - timeStart) / 1000) / 60);// cal.get(Calendar.MINUTE)
        int seconds = cal.get(Calendar.SECOND);//(int) ((testTime / 1000) % 60);
        int milliseconds = cal.get(Calendar.MILLISECOND);//(int) (testTime % 1000);
        return String.format("%dm %ds %dms", minutes, seconds, milliseconds);
    }
}
