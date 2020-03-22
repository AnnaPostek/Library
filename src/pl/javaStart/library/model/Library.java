package pl.javaStart.library.model;

import pl.javaStart.library.exception.PublicationAlreadyExistExceptions;
import pl.javaStart.library.exception.UserAlreadyExistException;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {

    private Map<String, Publication> publications = new HashMap<>();
    private Map<String, LibraryUser> users = new HashMap<>();

    public Map<String, Publication> getPublications() {
        return publications;
    }

    public Map<String, LibraryUser> getUsers() {
        return users;
    }

    public Collection<Publication> getSortedPublication(Comparator<Publication> comparator) {
        List<Publication> list = new ArrayList<>(publications.values());
        list.sort(comparator);
        return list;
    }

    public void addPublication(Publication publication) {
        if (publications.containsKey(publication.getTitle())) {
            throw new PublicationAlreadyExistExceptions("Publikacja o takim tytule już istnieje");
        }
        publications.put(publication.getTitle(), publication);
    }

    public void addUser(LibraryUser user) {
        if (users.containsKey(user.getPesel())) {
            throw new UserAlreadyExistException("Użytkownik o podanym nr Pesel już istnieje");
        }
        users.put(user.getPesel(), user);
    }

    public Collection<LibraryUser> getSortedUser(Comparator<LibraryUser> comparator) {
        ArrayList<LibraryUser> list = new ArrayList<>(users.values());
        list.sort(comparator);
        return list;
    }
    public boolean removePublication(Publication publication) {
        if (publications.containsValue(publication)) {
            publications.remove(publication.getTitle());
            return true;
        } else {
            return false;
        }

    }
}
