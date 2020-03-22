package pl.javaStart.library.io;

import pl.javaStart.library.model.Book;
import pl.javaStart.library.model.LibraryUser;
import pl.javaStart.library.model.Magazin;
import pl.javaStart.library.model.Publication;

import java.util.Collection;


public class ConsolePrinter {
    public void printBooks(Collection <Publication> publications) {
        int countBook = 0;
        for (Publication publication : publications) {
            if (publication instanceof Book) {
                printLine(publication.toString());
                countBook++;
            }
        }
        if (countBook == 0) {
            printLine("Nie ma w katalogu żadnych książek");
        }
    }
    public void printMagazin(Collection <Publication> publications) {
        int countMagazin = 0;
        for (Publication publication : publications) {
            if (publication instanceof Magazin) {
                printLine(publication.toString());
                countMagazin++;
            }
        }
        if (countMagazin == 0) {
            printLine("Nie ma w katalogu żadnych magazynów");
        }
    }
public void printLibraryUser (Collection<LibraryUser> user) {
    for (LibraryUser libraryUser : user) {
printLine(user.toString());

    }
}
    public void printLine(String text) {
        System.out.println(text.toUpperCase());
    }
}
