/* Plannr by Generic Name
 *
 * This file represents a CalendarUtil class which gets days in
 * a month and the month and year to display in a RecyclerView,
 * for activity_school.xml.
 */
package com.generic.plannr;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarUtil {
    public static LocalDate selectedDate;

    /**
     * Returns an ArrayList of the days in a month, from the selected date.
     *
     * @param date a LocalDate of a selected date.
     * @return an ArrayList of the days in a month from the selected date.
     */
    public static ArrayList<LocalDate> daysInMonthArray(LocalDate date) {
        ArrayList<LocalDate> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = CalendarUtil.selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add(null);
            } else {
                daysInMonthArray.add(LocalDate.of(selectedDate.getYear(), selectedDate.getMonth(), i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    /**
     * Returns the month and year of a selected date.
     *
     * @param date a LocalDate of a selected date.
     * @return a String of the month and year from the selected date.
     */
    public static String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }
}
