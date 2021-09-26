
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateTimeAPIDemo {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate = " + localDate);

        System.out.println("LocalDate.of(); = " + LocalDate.of(2021, 9, 28));
        System.out.println("LocalDate.parse() = " + LocalDate.parse("2021-09-28"));

        System.out.println("Tomorrow = " + LocalDate.now().plusDays(1));

        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        System.out.println("previousMonthSameDay = " + previousMonthSameDay);

        DayOfWeek dow = LocalDate.parse("2016-06-12").getDayOfWeek();
        System.out.println("DayOfWeek = " + dow);

//        etc
    }
}
