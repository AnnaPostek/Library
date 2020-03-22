package pl.javaStart.library.io;

import pl.javaStart.library.model.Book;
import pl.javaStart.library.model.LibraryUser;
import pl.javaStart.library.model.Magazin;

import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);
    private ConsolePrinter consolePrinter;

    public DataReader(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void close() {
        sc.close();
    }

    public Book readAndCreateBook() {
        consolePrinter.printLine("Wprowadź nową książkę");
        consolePrinter.printLine("Tytuł");
        String title = sc.nextLine();
        consolePrinter.printLine("Autor");
        String author = sc.nextLine();
        consolePrinter.printLine("Rok wydania");
        int year = getInt();
        consolePrinter.printLine("Liczba str");
        int pages = getInt();
        consolePrinter.printLine("Publisher");
        String publisher = sc.nextLine();
        consolePrinter.printLine("ISBN");
        String ISBN = sc.nextLine();
        return new Book(year, title, publisher, author, pages, ISBN);
    }

    public Magazin readAndCreateMagazine() {
        consolePrinter.printLine("Wprowadź nowy magazyn");
        consolePrinter.printLine("Tytuł");
        String title = sc.nextLine();
        consolePrinter.printLine("Wydawca");
        String publisher = sc.nextLine();
        consolePrinter.printLine("Język");
        String language = sc.nextLine();
        consolePrinter.printLine("Rok wydania");
        int year = getInt();
        consolePrinter.printLine("Miesiąc wydania:");
        int month = getInt();
        consolePrinter.printLine("Dzień wydania:");
        int day = getInt();
        return new Magazin(year, title, publisher, month, day, language);
    }

    public LibraryUser createLibraryUser() {
        consolePrinter.printLine("Wprowadź nowego użytkownika");
        consolePrinter.printLine("Imię");
        String name = sc.nextLine();
        consolePrinter.printLine("Nazwisko");
        String surname = sc.nextLine();
        consolePrinter.printLine("Pesel");
        String pesel = sc.nextLine();
        return new LibraryUser(name, surname, pesel);
    }

    public int getInt() {
        try {
            return sc.nextInt();
        } finally {
            sc.nextLine();
        }
    }

    public String getString() {
        return sc.nextLine();
    }
}
