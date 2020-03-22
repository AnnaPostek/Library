package pl.javaStart.library.model;

import java.util.Objects;

public class Magazin extends Publication {
    public static final String TYPE = "Magazyn";
    private int month;
    private int day;
    private String language;

    public Magazin(int year, String title, String publisher, int month, int day, String language) {
        super(year, title, publisher);
        this.month = month;
        this.day = day;
        this.language = language;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    @Override
    public String toCsv() {
        return TYPE + ";" + getYear() + ";" + getTitle() + ";" + getPublisher() + ";" + month +";"+ day +";"+language;
    }
    @Override
    public String toString() {
        return super.toString() +
                "; " + month +
                "; " + day +
                "; " + language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magazin magazin = (Magazin) o;
        return month == magazin.month &&
                day == magazin.day &&
                Objects.equals(language, magazin.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), month, day, language);
    }

}
