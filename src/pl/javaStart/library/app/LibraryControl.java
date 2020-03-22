package pl.javaStart.library.app;

import pl.javaStart.library.exception.*;
import pl.javaStart.library.io.ConsolePrinter;
import pl.javaStart.library.io.DataReader;
import pl.javaStart.library.io.File.FileManager;
import pl.javaStart.library.io.File.FileManagerBuilder;
import pl.javaStart.library.model.*;
import pl.javaStart.library.model.comparator.AlphabeticalTitleComparator;
import pl.javaStart.library.model.comparator.DateComparator;

import java.util.Comparator;
import java.util.InputMismatchException;

public class LibraryControl {
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private Library library;
    private DataReader dataReader = new DataReader(consolePrinter);
    private FileManager fileManager;

    LibraryControl() {
        fileManager = new FileManagerBuilder(consolePrinter, dataReader).build();
        try {
            library = fileManager.importData();
            consolePrinter.printLine("Zaimportowano dane z pliku");
        } catch (DataImportExpection | InvalidDataException e) {
            consolePrinter.printLine(e.getMessage());
            consolePrinter.printLine("Zainicjalizowano nową bazę");
            library = new Library();
        }
    }

    void controlLoop() {
        Option option;
        do {
            PrintOption();
            option = getOption();
            switch (option) {
                case EXIT:
                    exit();
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case PRINT_BOOK:
                    printBook();
                    break;
                case PRINT_MAGAZINE:
                    printMagazin();
                    break;
                case DELETE_BOOK:
                    deleteBook();
                    break;
                case DELETE_MAGAZIN:
                    deleteMagazin();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case PRINT_USER:
                printUser();

                    break;
                default:
                    consolePrinter.printLine("nie ma takiej opcji, wprowadź ponownie");
            }
        } while (option != Option.EXIT);
    }


    private Option getOption() {
        boolean optionOK = false;
        Option option = null;
        while (!optionOK) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOK = true;
            } catch (NoSuchOptionException a) {
                consolePrinter.printLine(a.getMessage());
            } catch (InputMismatchException b) {
                consolePrinter.printLine("Nieprawidłowy zapis, proszę wybrać ponownie liczbę");
            }
        }
        return option;
    }

    private void addMagazine() {
        try {
            Magazin magazin = dataReader.readAndCreateMagazine();
            library.addPublication(magazin);
        } catch (InputMismatchException ime) {
            consolePrinter.printLine("Nie udało się utworzyć magazynu, niepoprawne dane.");
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            consolePrinter.printLine("Nie można dodać magazynu");
        }
    }

    private void deleteMagazin() {
        try{
        Magazin magazin = dataReader.readAndCreateMagazine();
        if (library.removePublication(magazin)) {
            consolePrinter.printLine("Magazyn został usunięty");
        } else {
            consolePrinter.printLine("Brak wskazanego magazynu");
        } } catch(InputMismatchException i) {
            consolePrinter.printLine("Nieprawidłowe dane");
        }
    }

    private void printMagazin() {
        consolePrinter.printMagazin(library.getSortedPublication(new AlphabeticalTitleComparator()));
    }

    public void PrintOption() {
        System.out.println("Wybierz opcję: ");
        for (Option value : Option.values()) {
            consolePrinter.printLine(value.toString());
        }
    }


    public void addBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addPublication(book);
        } catch (InputMismatchException ime) {
            consolePrinter.printLine("Nie udało się utworzyć książki, niepoprawne dane.");
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            consolePrinter.printLine("Nie można dodać więcej książek");
        }
    }

    private void deleteBook() {
        try{
        Book book = dataReader.readAndCreateBook();
        if(library.removePublication(book))  {
            consolePrinter.printLine("Książka została usunięta");
        } else{
            consolePrinter.printLine("Nie ma takiego magazynu");
        } } catch(InputMismatchException i) {
            consolePrinter.printLine("Nieprawidłowe dane");
        }
    }

    public void printBook() {
        consolePrinter.printBooks(library.getSortedPublication(new AlphabeticalTitleComparator()));
    }
    private void addUser() {
        try{
        LibraryUser libraryUser = dataReader.createLibraryUser();
        library.addUser(libraryUser); }
        catch(UserAlreadyExistException i) {
            consolePrinter.printLine(i.getMessage());
        }
    }

    private void printUser() {
        consolePrinter.printLibraryUser(library.getSortedUser(new Comparator<LibraryUser>() {
            @Override
            public int compare(LibraryUser p1, LibraryUser p2) {
                return p1.getSurname().compareToIgnoreCase(p2.getSurname());
            }
        }));
    }

    public void exit() {
        try {
            fileManager.exportData(library);
            System.out.println("Eksport danych do pliku zakończony powodzeniem");
        } catch (DataExportException d) {
            consolePrinter.printLine(d.getMessage());
        }
        System.out.println("Koniec programu");
        dataReader.close();
    }

    private enum Option {
        EXIT(0, "Wyjście z programu"),
        ADD_BOOK(1, "dodanie nowej książki"),
        ADD_MAGAZINE(2, "dodanie nowego magazynu"),
        PRINT_BOOK(3, "Wyświetl dostępne książki"),
        PRINT_MAGAZINE(4, "Wyświetl dostępne magazyny"),
        DELETE_BOOK(5, "Usuń książkę"),
        DELETE_MAGAZIN(6, "Usuń magazyn"),
        ADD_USER(7, "Dodawanie użytkowników"),
        PRINT_USER(8, "Wyświetl użytkowników");

        private int value;
        private String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option createFromInt(int value) throws NoSuchOptionException {
            try {
                return Option.values()[value];
            } catch (ArrayIndexOutOfBoundsException a) {
                throw new NoSuchOptionException("Brak opcji o id " + value);
            }
        }
    }
}
