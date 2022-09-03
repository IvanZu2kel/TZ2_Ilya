package com.company;

import java.nio.file.Path;
import java.util.HashMap;

public class MonthReport {
    private static Integer year = null;
    private static final HashMap<Integer,Path> monthReport = new HashMap<>();

    public MonthReport(Integer year, HashMap<Integer, Path> monthReport) {
        MonthReport.year = year;
    }

    public static HashMap<Integer, Path> getMonthReport() {

        return monthReport;
    }

    public static void setMonthReport(Integer years, Integer numberMonth, Path monthReports) {
        year= years;
        monthReport.put(numberMonth,monthReports);
    }
    public static Integer getYearOfMonthReport(){
        return year;
    }
}
