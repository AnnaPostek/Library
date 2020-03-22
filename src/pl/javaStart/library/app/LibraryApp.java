package pl.javaStart.library.app;

public class LibraryApp {

    private static final String appName = "Biblioteka v0.9";

    public static void main(String[] args) {
        System.out.println(appName);
        LibraryControl libraryControl = new LibraryControl();
        libraryControl.controlLoop();
    }
}
