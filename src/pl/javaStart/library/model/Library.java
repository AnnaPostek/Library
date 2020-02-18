package pl.javaStart.library.model;

import pl.javaStart.library.io.DataReader;

public class Library {
    private final int MAX_BOOK = 1000;
    private Book[]books = new Book[MAX_BOOK];
    private int booksNumber;

    public void addBook(Book book) {
        if(booksNumber<MAX_BOOK) {
            books[booksNumber] = book;
            booksNumber++;
        } else {
            System.out.println("Nie można dodać więcej książek");
        }
    }
    public void printBooks(){
        if(booksNumber == 0) {
            System.out.println("Nie ma w katalogu żadnych książek");
        } else {
            for (int i = 0; i < booksNumber; i++) {
              books[i].printInfo();
            }

            }
        }
    }
