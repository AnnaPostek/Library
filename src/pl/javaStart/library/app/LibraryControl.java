package pl.javaStart.library.app;

import pl.javaStart.library.io.DataReader;
import pl.javaStart.library.model.Book;
import pl.javaStart.library.model.Library;

public class LibraryControl {
    private final int exit = 0;
    private final int addBook = 1;
    private final int printBooks = 2;
    private Library library = new Library();
    private DataReader dataReader = new DataReader();

    public void controlLoop() {
        int option;
        do{PrintOption();
        option=dataReader.getInt();
        switch (option) {
            case 0: exit();
            break;
            case 1: addBook();
            break;
            case 2: printBook();
            break;
            default:
                System.out.println("nie ma takiej opcji, wprowadź ponownie");
            }
        } while (option!=exit);
        }

public void PrintOption(){
    System.out.println("Wybierz opcję: ");
    System.out.println(exit + " - wyjście z programu");
    System.out.println(addBook + " - dodanie nowej książki");
    System.out.println(printBooks + " - wyświetl dostępne książki");
}


    public void addBook(){
       Book book = dataReader.readAndCreateBook();
        library.addBook(book);
    }
    public void printBook(){
        library.printBooks();
    }
    public void exit(){
        System.out.println("Koniec programu");
        dataReader.close();
    }
}
