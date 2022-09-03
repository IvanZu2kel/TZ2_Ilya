package com.company;

import java.nio.file.Path;
import java.util.HashMap;

public class YearReport {
    private static HashMap<Integer, Path> yearReport = new HashMap<>();

    public YearReport() {
    }

    public static void setMonthReport(Integer numberMonth, Path reportYear) {
        yearReport.put(numberMonth,reportYear);
    }

    public static HashMap<Integer, Path> getYearReport() {
        return yearReport;
    }
}
