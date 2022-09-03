package com.company;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class ReadMonthReport {
    String badProfit;
    Integer badprofit;

    final static String ENCODING_cp1251 = "windows-1251";


    static ArrayList<String> readInform(Integer numberMonth) {
        ArrayList<String> content = new ArrayList<>();
        int a = 0;
        try {
            Path read = MonthReport.getMonthReport().get(numberMonth);
            File csvFile = new File(String.valueOf(read));
            try (BufferedReader csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile)))) {
                while (csvReader.ready()) {
                    content.add(csvReader.readLine());
                }
            }
        } catch (IOException e) {
            a++;
        }
        return content;
    }


    void morePriorityReport(Integer numberOfmonth) {
        int moreProfit = 0;
        String nameProfit = null;
        badProfit = null;
        badprofit = 0;
        ArrayList<String> contentMonth = readInform(numberOfmonth);
        for (int i = 1; i < contentMonth.size(); i++) {
            String[] content;
            content = contentMonth.get(i).split(",");
            if (content[1].equalsIgnoreCase("false")) {
                int profit = Integer.parseInt(content[2]) * Integer.parseInt(content[3]);
                if (profit > moreProfit) {
                    moreProfit = profit;
                    nameProfit = content[0];
                }
            } else {
                int profit = Integer.parseInt(content[2]) * Integer.parseInt(content[3]);
                if (profit > badprofit) {
                    badprofit = profit;
                    badProfit = content[0];
                }
            }
        }
        System.out.printf("Самый прибыльный товар за %s %s принес прибыль %s.%n", MonthEnum.values()[numberOfmonth - 1].getTitle(), moreProfit, nameProfit);
        System.out.printf("Самый не прибыльный товар за %s %s принес убытки на %s.%n", MonthEnum.values()[numberOfmonth - 1].getTitle(), badProfit, badprofit);
    }

    static ArrayList<Integer> sumReport() {
        ArrayList<Integer> sumMonth = new ArrayList<>();
        for (int i = 1; i <= MonthReport.getMonthReport().size(); i++) {
        Integer[] sum = sumMonthReport(i);
        sumMonth.add(sum [0]);
        sumMonth.add(sum[1]);
        }
        return sumMonth;
    }


    static Integer[] sumMonthReport(Integer numberMonth) {
        Integer sumMonth = 0;
        Integer badSumMoth = 0;
        ArrayList<String> contentMonth = readInform(numberMonth);
        for (int i = 1; i < contentMonth.size(); i++) {
            String[] countertenor = contentMonth.get(i).split(",");
            if (countertenor[1].equalsIgnoreCase("false")) {
                int profit = Integer.parseInt(countertenor[2]) * Integer.parseInt(countertenor[3]);
                sumMonth += profit;
            } else if (countertenor[1].equalsIgnoreCase("true")) {
                int profit = Integer.parseInt(countertenor[2]) * Integer.parseInt(countertenor[3]);
                badSumMoth += profit;
            } else {
                continue;
            }
        }
        Integer [] summMonth ={sumMonth,badSumMoth};
        return  summMonth;
    }
}

