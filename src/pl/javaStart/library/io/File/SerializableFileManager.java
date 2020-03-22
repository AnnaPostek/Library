package pl.javaStart.library.io.File;

import pl.javaStart.library.exception.DataExportException;
import pl.javaStart.library.exception.DataImportExpection;
import pl.javaStart.library.model.Library;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "Library.o";

    @Override
    public Library importData() {
        try
            ( FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis); )
        {return (Library) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportExpection("Brak pliku " + FILE_NAME);
        } catch (IOException io) {
            throw new DataImportExpection("Błąd zapisu danych do pliku " + FILE_NAME);
        } catch (ClassNotFoundException ce) {
            throw new DataImportExpection("Niezgodny typ danych w pliku " + FILE_NAME);
        }
    }

    @Override
    public void exportData(Library library) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream ois = new ObjectOutputStream(fos);) {
            ois.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportException("Brak pliku " + FILE_NAME);
        } catch (IOException io) {
            throw new DataExportException("Błąd zapisu danych do pliku " + FILE_NAME);
        }

    }
}
