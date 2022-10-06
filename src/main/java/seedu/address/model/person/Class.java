package seedu.address.model.person;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a Person's class date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassString(String)}
 */
public class Class {

    public static final String MESSAGE_CONSTRAINTS = "Class can take any string"
            + " in the format of 'yyyy-MM-dd 0000-2300'";
    public static final String INVALID_DATETIME_ERROR_MESSAGE = "Date should be a valid date";
    public static final String INVALID_TIME_ERROR_MESSAGE = "Time should be in the range of 0000 - 2359";
    public static final String VALIDATION_DATETIME_REGEX = "[0-9]{4}[-][0-9]{2}[-][0-9]{2}";
    public static final String VALIDATION_TIME_REGEX = "[0-9]{4}";
    public static final String VALIDATION_CLASS_REGEX = VALIDATION_DATETIME_REGEX
            + "[ ]" + VALIDATION_TIME_REGEX + "[-]" + VALIDATION_TIME_REGEX;

    public final LocalDate date;
    public final LocalTime startTime;
    public final LocalTime endTime;

    public final String classDateTime; //User input
    public final String classToString; //formatted date

    public Class(String classDateTime) {
        this.date = null;
        this.startTime = null;
        this.endTime = null;
        this.classDateTime = "";
        this.classToString = "";
    }

    /**
     * Constructs a {@code Class}.
     *
     * @param date
     * @param startTime
     * @param endTime
     */
    public Class(LocalDate date, LocalTime startTime, LocalTime endTime, String classDateTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classToString = convertToString(date, startTime, endTime);
        this.classDateTime = classDateTime;
    }

    public static String convertToString(LocalDate date, LocalTime startTime, LocalTime endTime) {
        return convertToDateString(date) + " " + convertToTimeString(startTime, endTime);
    }

    public static String convertToDateString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    public static String convertToTimeString(LocalTime startTime, LocalTime endTime) {
        String time = "";
        int startHour = startTime.getHour();
        int startMin = startTime.getMinute();
        int endHour = endTime.getHour();
        int endMin = endTime.getMinute();
        if (startHour > 12) {
            time += startHour + "." + startMin + "PM";
        } else {
            time += startHour + "." + startMin + "AM";
        }
        time += "-";
        if (endHour > 12) {
            time += endHour + "." + endMin + "PM";
        } else {
            time += endHour + "." + endMin + "AM";
        }
        return time;
    }

    /**
     * Validates whether classDatetime is valid.
     * @param classDatetime the string to be validated.
     * @return true if a given string fits the format of 'yyyy-MM-dd 0000-2359'
     */
    public static boolean isValidClassString(String classDatetime) {
        if (!classDatetime.matches(VALIDATION_CLASS_REGEX)) {
            return false;
        }

        String datetimeStr = classDatetime.substring(0, 10);
        String startTimeStr = classDatetime.substring(11, 15);
        String endTimeStr = classDatetime.substring(16);

        return isValidDatetimeString(datetimeStr) && isValidTimeString(startTimeStr) && isValidTimeString(endTimeStr);
    }

    /**
     * Helper method to validate {@code date}.
     */
    private static boolean isValidDatetimeString(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeException de) {
            // text cannot be parsed
            return false;
        }
        return true;
    }

    /**
     * Helper method to validate {@code time}.
     */
    private static boolean isValidTimeString(String time) {
        Integer hour = Integer.valueOf(time.substring(0, 2));
        Integer minute = Integer.valueOf(time.substring(2));
        try {
            LocalTime.of(hour, minute);
        } catch (DateTimeException de) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Class // instanceof handles nulls
                && classToString.equals(((Class) other).classToString)); // state check
    }

}