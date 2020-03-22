package pl.javaStart.library.io.File;

import pl.javaStart.library.exception.DataExportException;
import pl.javaStart.library.exception.DataImportExpection;
import pl.javaStart.library.exception.InvalidDataException;
import pl.javaStart.library.model.*;

import java.io.*;
import java.util.Collection;
import java.util.Scanner;

public class CsvFileManager implements FileManager {
    private static final String FILE_NAME = "Library.csv";
    private static final String USER_FILE_NAME = "Library_users.csv";

    @Override
    public Library importData() {
        Library library = new Library();
        importPublication(library);
        importUser(library);
        return library;
    }

    private void importUser(Library library) {
        try (Scanner fileReader = new Scanner(new File(USER_FILE_NAME))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                LibraryUser userFromString = createUserFromString(line);
                library.addUser(userFromString);
            }
        } catch (FileNotFoundException e) {
            throw new DataImportExpection("Brak pliku " + USER_FILE_NAME);
        }
    }

    private LibraryUser createUserFromString(String csvText) {
        String[] split = csvText.split(";");
        String firstName = split[0];
        String lastName = split[1];
        String pesel = split[2];
        return new LibraryUser(firstName, lastName, pesel);
    }

    private void importPublication(Library library) {
        try (
                Scanner fileReader = new Scanner(new File(FILE_NAME))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Publication publication = createObjectFromString(line);
                library.addPublication(publication);
            }
        } catch (FileNotFoundException e) {
            throw new DataImportExpection("Not implemented");
        }
    }

    //Magazyn;1989;Ogrodnictwo;Publisher;7;15;Polski
    private Publication createObjectFromString(String line) {
        String[] split = line.split(";");
        String type = split[0];
        if (Book.TYPE.equals(type)) {
            return createBook(split);
        } else if (Magazin.TYPE.equals(type)) {
            return createMagazin(split);
        }
        throw new InvalidDataException("Nieznany typ publikacji " + type);
    }

    private Magazin createMagazin(String[] split) {
        String title = split[2];
        String publisher = split[3];
        int year = Integer.valueOf(split[1]);
        int month = Integer.valueOf(split[4]);
        int day = Integer.valueOf(split[5]);
        String language = split[6];
        return new Magazin(year, title, publisher, month, day, language);
    }

    private Book createBook(String[] split) {
        String title = split[2];
        String publisher = split[3];
        int year = Integer.valueOf(split[1]);
        String author = split[4];
        int pages = Integer.valueOf(split[5]);
        String isbn = split[6];
        return new Book(year, title, publisher, author, pages, isbn);
    }

    @Override
    public void exportData(Library library) {
        exportPublication(library);
        exportUser(library);
    }

    private void exportUser(Library library) {
        Collection<LibraryUser> users = library.getUsers().values();
        exportToCsv(users, USER_FILE_NAME);
    }

    private void exportPublication(Library library) {
        Collection<Publication> publications = library.getPublications().values();
        exportToCsv(publications, FILE_NAME);
    }
    public <T extends CsvConvertible> void exportToCsv(Collection <T> collection, String fileName) {
        try (var fileWriter = new FileWriter(fileName);
             var buffererdWriter = new BufferedWriter(fileWriter);
        ) {
            for (T element : collection) {
                buffererdWriter.write(element.toCsv());
                buffererdWriter.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + USER_FILE_NAME);
        }
    }
}
