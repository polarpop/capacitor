package com.infosys.capacitor.utils;

import java.text.SimpleDateFormat;
import java.util.*;


public class CapacitorDateUtils {
  /**
   * A static member that returns an array of strings with all the days in the week.
   *
   * @return An array of strings that contain every date of the current week.
   */
  static public List<String> getThisWeek() {
    List<String> dates = new ArrayList<>();
    Calendar now = Calendar.getInstance();

    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    String[] days = new String[7];
    int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2;

    now.add(Calendar.DAY_OF_MONTH, delta);

    for (int i = 0; i<7;i++) {
      dates.add(format.format(now.getTime()));
      now.add(Calendar.DAY_OF_MONTH, 1);
    }
    return dates;
  }
}