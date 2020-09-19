package org.narussia.justfortoday.data;

public class Dairy {
    private String date;
    private int day;
    private int month;
    private String title;
    private String baseText;
    private String dayText;
    private String justForToday;

    public Dairy() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBaseText() {
        return baseText;
    }

    public void setBaseText(String baseText) {
        this.baseText = baseText;
    }

    public String getDayText() {
        return dayText;
    }

    public void setDayText(String dayText) {
        this.dayText = dayText;
    }

    public String getJustForToday() {
        return justForToday;
    }

    public void setJustForToday(String justForToday) {
        this.justForToday = justForToday;
    }
}
