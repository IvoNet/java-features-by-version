package java16;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterPeriodOfDayDemo {
    public static void main(String[] args) {
        System.out.println(DateTimeFormatter.ofPattern("B")
                                            .format(LocalTime.now()));

        //in the afternoon
    }
}
