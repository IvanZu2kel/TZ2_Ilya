package com.company;

public enum MonthEnum {
    JANUARY("Январь",1),
    FEBRUARY("Февраль",2),
    MARCH("Март",3),
    APRIL("Апрель",4),
    MAY("Май",5),
    JUNE("Июнь",6),
    JULY("Июль",7),
    AUGUST("Август",8),
    SEPTEMBER("Сентябрь",9),
    OCTOBER("Октябрь",10),
    NOVEMBER("Ноябрь",11),
    DECEMBER("Декабрь",12);

    private String title;
    private Integer number;

    MonthEnum(String title, Integer number) {
        this.title = title;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }
}