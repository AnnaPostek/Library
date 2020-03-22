package pl.javaStart.library.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class User implements Serializable, CsvConvertible {
    private String name;
    private String surname;
    private String Pesel;

    public User(String name, String surname, String pesel) {
        this.name = name;
        this.surname = surname;
        Pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return Pesel;
    }

    public void setPesel(String pesel) {
        Pesel = pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(Pesel, user.Pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, Pesel);
    }

    @Override
    public String toString() {
        return name + " " + surname + " - " + Pesel;
    }

}
