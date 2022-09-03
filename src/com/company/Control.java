package com.company;

public class Control {
    public static ReadFile readFile = new ReadFile();


    public static void control() {
        while (readFile.Flag) {
            readFile.printMenu();
            int command = readFile.command();
            switch (command) {
                case 1:
                case 2: {
                    readFile.addReport();
                    break;
                }
                case 3: {
                    readFile.yearCheck();
                    break;
                } case 4: {
                    readFile.monthReport();
                    break;
                } case 5: {
                    readFile.yearReport();
                    break;
                } case 6: {
                    readFile.isExit();
                    break;
                }
                default:
                    System.out.println("Выберите номер");
            }
        }
    }
}
