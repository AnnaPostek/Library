package pl.javaStart.library.io.File;

import pl.javaStart.library.model.Library;

import java.io.FileNotFoundException;

public interface FileManager {
    Library importData();
    void exportData(Library library);
}
