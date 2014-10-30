package jdk8.api;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

public class Demo2 {
    public static void main(String[] args) {
        // Get current date and time
        LocalDateTime date = LocalDateTime.now();
        System.out.println("Current date/time with built-in LocalDateTime class format: " + date);
        
        // Now use new DateTimeFormatter class
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");
        String strDate = date.format(format);
        System.out.println("Current date/time with custom format provided by DateTimeFormatter: " + strDate);

        // Just get Dates without time values and calculate payday
        format = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate today = LocalDate.now();
        LocalDate payday1 = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate payday2 = today.with(TemporalAdjusters.lastDayOfMonth()).minusDays(2);
        System.out.println("Pretend Payday is at the end of this month: " + payday1.format(format));
        System.out.println("Pretend Payday is at the end of this month, less two days: " + payday2.format(format));
        
        // What else can you do? Do some research and share with class. You have one hour!
        
        //until() / between()
        LocalDateTime later = date.plusHours(5).plusMinutes(20);
        System.out.println(date.until(later, ChronoUnit.HOURS));
        System.out.println(ChronoUnit.HOURS.between(date, later));
        
        //lastInMonth() demo
        LocalDate lastBusinessDay = today.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
        System.out.println(lastBusinessDay);
        
        //Find the difference between two dates in Hours and minutes
        LocalDateTime test1 = LocalDateTime.of(2013, Month.MARCH, 20, 13, 57);
        LocalDateTime test2 = LocalDateTime.of(2013, Month.MARCH, 20, 14, 21);
        hourMinuteDifference(test1, test2);
        
        //what time is it in berlin?
        ZonedDateTime datetime = ZonedDateTime.now();
        datetime = datetime.withZoneSameInstant(ZoneId.of("Europe/Berlin"));
        System.out.println(datetime);
        
        //date in future
        LocalDate buyDate = LocalDate.now();
        LocalDate payDate = buyDate.plus(15L, ChronoUnit.DAYS);
        System.out.println(payDate);
        
        
        Year nextYear = Year.now();
        boolean isLeap = nextYear.isLeap();
        
        while(!isLeap)
        {
            nextYear = nextYear.plusYears(1);
            isLeap = nextYear.isLeap();
        }
        System.out.println(nextYear);
    }
    
    private static void hourMinuteDifference(LocalDateTime a, LocalDateTime b)
    {
        final int MINUTES_PER_HOUR = 60;
        long hours = ChronoUnit.HOURS.between(a, b);
        long minutes = ChronoUnit.MINUTES.between(a, b) % MINUTES_PER_HOUR;
        System.out.println(hours + " hours" + (minutes != 0 ? ", " + minutes + " minutes" : ""));
    }
}
