package com.company;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadYearReport {


    final static String ENCODING_cp1251 = "windows-1251";

    ArrayList<String> readInform(Integer numberMonth) {
        ArrayList<String> content = new ArrayList<>();
        try {
            Path read = YearReport.getYearReport().get(numberMonth);
            File csvFile = new File(String.valueOf(read));
            try (BufferedReader csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), ENCODING_cp1251))) {
                while (csvReader.ready()) {
                    content.add(csvReader.readLine());
                }
            }
        } catch (IOException e) {
            System.out.println("По указному пути файлы не найдены");
        }
        return content;
    }

    void sumYearReport(Integer year) {
        Integer sumYear = 0;
        Integer badSumYear = 0;
        ArrayList<String> contentYear = readInform(year);
        for (int i = 0; i < contentYear.size(); i++) {
            String[] countertenor = contentYear.get(i).split(",");
            if (countertenor[2].equalsIgnoreCase("false")) {
                int profit = Integer.parseInt(countertenor[1]);
                sumYear += profit;
            } else if (countertenor[2].equalsIgnoreCase("true")){
                int profit = Integer.parseInt(countertenor[1]);
                badSumYear += profit;
            } else {
                continue;
            }
        }
        System.out.printf("Средний расход за %s год составил %s.%n", year, badSumYear / 12);
        System.out.printf("Средний доход за %s год составил %s.%n", year, sumYear / 12);
    }

    void staticYearForMonth(Integer year) {
        HashMap<Integer, Integer> monthcontent = new HashMap<>();
        ArrayList<String> contentYear = readInform(year);
        Integer sumMonth = 0;
        for (int i = 1; i < contentYear.size(); i++) {
            String[] countertenor = contentYear.get(i).split(",");
            Integer numberMonth = Integer.parseInt(countertenor[0]);
            if (countertenor[2].equalsIgnoreCase("false")) {
                if (monthcontent.containsKey(numberMonth)) {
                    Integer sumM = monthcontent.get(numberMonth) + Integer.parseInt(countertenor[1]);
                    monthcontent.put(numberMonth, sumM);
                } else if (countertenor[2].equalsIgnoreCase("false")){
                    monthcontent.put(numberMonth, Integer.parseInt(countertenor[1]));
                } else continue;
            } else if (countertenor[2].equalsIgnoreCase("true")){
                if (monthcontent.containsKey(numberMonth)) {
                    Integer sumM = monthcontent.get(numberMonth) - Integer.parseInt(countertenor[1]);
                    monthcontent.put(numberMonth, sumM);
                } else {
                    monthcontent.put(numberMonth, -Integer.parseInt(countertenor[1]));
                }
            } else{
                continue;
            }
        }
        for (Integer reportMonth : monthcontent.keySet()) {
            System.out.printf("В %s месяце прибыль составила %s.%n", MonthEnum.values()[reportMonth - 1].getTitle(), monthcontent.get(reportMonth));
        }
    }

    void checkMonthYear(Integer year) {
        int a = 1;
        ArrayList<String> contentYear = readInform(year);
        ArrayList <Integer> getMonthCheck = ReadMonthReport.sumReport();
        for (int i = 1; i < contentYear.size(); i++) {
            String[] countertenor = contentYear.get(i).split(",");
            if (countertenor[2].equalsIgnoreCase("false")) {
                if (getMonthCheck.get(i-1) == Integer.parseInt(countertenor[1])) {
                    System.out.printf("Данные по доходам за %s совпадают.%n",MonthEnum.values()[ Integer.parseInt(countertenor[0]) - 1].getTitle());
                } else {
                    System.out.printf("Доходы за %s из годового отчета %s и месячного %s не совпадают.%n", MonthEnum.values()[ Integer.parseInt(countertenor[0]) - 1].getTitle(), Integer.parseInt(countertenor[1]), getMonthCheck.get(i-1));
                }
            } else {
                if (getMonthCheck.get(i-1) == Integer.parseInt(countertenor[1])) {
                    System.out.printf("Данные по расходам за %s совпадают.%n",MonthEnum.values()[ Integer.parseInt(countertenor[0]) - 1].getTitle());
                } else {
                    System.out.printf("Расходы за %s из годового отчета %s и месячного %s не совпадают.%n", MonthEnum.values()[Integer.parseInt(countertenor[0]) - 1].getTitle(),  Integer.parseInt(countertenor[1]), getMonthCheck.get(i));
                }
            }
        }
    }
}

