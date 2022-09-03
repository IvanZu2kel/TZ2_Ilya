package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ReadFile {
    static ReadYearReport readYearReport = new ReadYearReport();
    ReadMonthReport readMonthReport = new ReadMonthReport();
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public Boolean Flag = true;



    void setDirectoryRead(String s) throws IOException {
        Path directory = Paths.get(s);
        if (!Files.isDirectory(directory)) {
            System.out.println(directory.toString() + " - не папка.");
        } else {
            Files.walkFileTree(directory, new MyFileVisitor());
        }
    }

    public void printMenu() {
        System.out.println("Что вы хотите получить? ");
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию о всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Выход");
    }
    public  int command(){
        int command = 0;
        try {
            command = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return command;
    }


    static class MyFileVisitor extends SimpleFileVisitor<Path> {

        public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
            if (path.toString().contains("m.") && path.toString().endsWith(".csv")) {
                String[] year = path.getFileName().toString().split("\\.", 3);
                int numberMonth = Integer.parseInt(year[1]) % 100;
                int years = Integer.parseInt(year[1]) / 100;
                MonthReport.setMonthReport(years,numberMonth,path);
            } else if (path.toString().contains("y.") && path.toString().endsWith(".csv")) {
                String[] year = path.getFileName().toString().split("\\.", 3);
                int years = Integer.parseInt(year[1]);
                YearReport.setMonthReport(years,path);
            }
            return FileVisitResult.CONTINUE;
        }
    }
    void addReport(){
        System.out.println("Введите путь к папке(хоть приблизительный =))");
        try {
        String directory = reader.readLine();
            setDirectoryRead(directory);
        } catch (IOException e) {
            System.out.println("Указанный путь оказался не верен");
        }
        System.out.println("Отчеты добавлены");
    }
    void yearCheck(){
        Integer year = null;
        System.out.printf("Введите № года.В формате (YYYY).%n");
        try {
            year = Integer.valueOf(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        readYearReport.checkMonthYear(year);
    }

    void monthReport(){
        for (int i = 1; i <= MonthReport.getMonthReport().size(); i++) {
            System.out.println(MonthEnum.values()[i-1].getTitle());
            readMonthReport.morePriorityReport(i);
        }
    }

    void yearReport(){
        System.out.println("Введите год за который хотите получить отчет");
        try {
            Integer yearSearch = Integer.parseInt(reader.readLine());
            System.out.println("За год " + yearSearch);
            readYearReport.staticYearForMonth(yearSearch);
            readYearReport.sumYearReport(yearSearch);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void isExit(){
        Flag = false;
    }
}
