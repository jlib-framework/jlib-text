/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * Copyright (c) 2006-2008 Igor Akkerman
 * 
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.opensource.org/licenses/cpl1.0.php
 */

package org.jlib.core.calendar;

import java.util.Calendar;

/**
 * Time interval in days, hours, minutes and seconds. Adding the interval to a
 * calendar adds all values to the calendar's current date.
 * 
 * @author Igor Akkerman
 */
public class TimeInterval {

    /**
     * Creates a new TimeInterval.
     */
    public TimeInterval() {
        super();
    }

    /**
     * number of days
     */
    public int days;

    /**
     * number of hours
     */
    public int hours;

    /**
     * number of minutes
     */
    public int minutes;

    /**
     * number of seconds
     */
    public int seconds;

    /**
     * Adds this TimeInterval to the date of the specified calendar. The
     * specified calendar is left unchanged within the operation. A new
     * {@code Calendar} instance with the new date is returned.
     * 
     * @param calendar
     *        Calendar to add this TimeInterval to; the specified Calendar is
     *        left unchanged during the operation
     * @return new Calendar with the new date
     */
    public Calendar addTo(Calendar calendar) {
        calendar = (Calendar) calendar.clone();
        calendar.add(Calendar.DAY_OF_MONTH, days);
        calendar.add(Calendar.HOUR, hours);
        calendar.add(Calendar.MINUTE, minutes);
        calendar.add(Calendar.SECOND, seconds);
        return calendar;
    }

    /**
     * Returns a String representation of this TimeInterval.
     * 
     * @return String containing all values of this TimeInterval
     */
    public String toString() {
        return "TimeInterval<" + "days=" + days + ", " + "hours=" + hours + ", " + "minutes=" + minutes + ", "
               + "seconds=" + seconds + ">";
    }
}
