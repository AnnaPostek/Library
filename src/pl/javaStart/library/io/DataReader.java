package pl.javaStart.library.io;

import pl.javaStart.library.model.Book;

import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);

    public void close() {
        sc.close();
    }

    public Book newBook(){
        System.out.println("Wprowadź nową książkę");
        System.out.println("Tytuł");
        String title = sc.nextLine();
        System.out.println("Autor");
        String author = sc.nextLine();
        System.out.println("Rok wydania");
        int year = sc.nextInt();
        sc.nextLine();
        System.out.println("Liczba str");
        int pages = sc.nextInt();
        sc.nextLine();
        System.out.println("Publisher");
        String publisher = sc.nextLine();
        System.out.println("ISBN");
        String ISBN = sc.nextLine();
        return new Book(title, author, year, pages, publisher, ISBN);
    }
}
