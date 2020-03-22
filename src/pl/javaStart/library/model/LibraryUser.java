package pl.javaStart.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LibraryUser extends User {
    private List<Publication> publicationHistory = new ArrayList<>();
    private List<Publication> borrowedPublication = new ArrayList<>();

    public LibraryUser(String name, String surname, String pesel) {
        super(name, surname, pesel);
    }

    public List<Publication> getPublicationHistory() {
        return publicationHistory;
    }

    public List<Publication> getBorrowedHistory() {
        return borrowedPublication;
    }

    public void addPublicationToHistory(Publication pub) {
        publicationHistory.add(pub);
    }

    public void borrowPublication(Publication pub) {
        borrowedPublication.add(pub);
    }

    public boolean returnPublication(Publication pub) {
        boolean result = false;
        if (borrowedPublication.contains(pub)) {
            borrowedPublication.remove(pub);
            addPublicationToHistory(pub);
            result = true;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryUser)) return false;
        if (!super.equals(o)) return false;
        LibraryUser that = (LibraryUser) o;
        return Objects.equals(getPublicationHistory(), that.getPublicationHistory()) &&
                Objects.equals(borrowedPublication, that.borrowedPublication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPublicationHistory(), borrowedPublication);
    }

    @Override
    public String toCsv() {
        return getName() + ";" + getSurname() + ";" + getPesel();
    }
}
