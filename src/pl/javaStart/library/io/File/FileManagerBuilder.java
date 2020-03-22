package pl.javaStart.library.io.File;

import pl.javaStart.library.exception.NoSuchFileTypeException;
import pl.javaStart.library.exception.NoSuchOptionException;
import pl.javaStart.library.io.ConsolePrinter;
import pl.javaStart.library.io.DataReader;

public class FileManagerBuilder {
    ConsolePrinter consolePrinter;
    DataReader dataReader;

    public FileManagerBuilder(ConsolePrinter consolePrinter, DataReader dataReader) {
        this.consolePrinter = consolePrinter;
        this.dataReader = dataReader;
    }
    public FileManager build() {
        consolePrinter.printLine("Wybierz format danych: ");
        FileType fileType = getFileType();
        switch (fileType) {
            case SERIAL:
                return new SerializableFileManager();
            case CSV:
                return new CsvFileManager();
            default:
                throw new NoSuchFileTypeException("Nieobsługiwany typ danych");
        }
    }

    private FileType getFileType() {
       boolean typeOk = false;
       FileType result = null;
       do {
           printType();
           String type = dataReader.getString().toUpperCase();
           try {
               result = FileType.valueOf(type);
               typeOk = true;
           } catch (IllegalArgumentException ie) {
               consolePrinter.printLine("Nieobslugiwany typ danych, wybierz ponownie.");
           }
       }
       while (!typeOk);
       return result;
}

    private void printType() {
        for (FileType value : FileType.values()) {
            consolePrinter.printLine(value.toString());
        }
    }
}
