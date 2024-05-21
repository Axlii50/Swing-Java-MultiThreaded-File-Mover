package tests;

import org.junit.jupiter.api.Test;
import pl.wit.DirectoryService;
import pl.wit.Node;
import pl.wit.ThreadService;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Klasa testowa dla ThreadService
 *
 * @author Damian Dziedzic
 * @version 1.0
 * @since 2024-05-21
 */
public class ThreadServiceTest {

    /**
     * Testowanie metody CopyFiles w klasie ThreadService
     */
    @Test
    public void copyFilesTest() {
        //Arrange

        ThreadService thread = new ThreadService(5);
        Node structure = new Node("default", "src/test/java/testDirectory");
        String newDirectory = "src/test/java/additionalDirectory";

        DirectoryService directory = new DirectoryService();
        IllegalArgumentException ex1 = null;
        Node newStruct = new Node("new", "/.");
        //Act

        thread.CopyFiles(structure, newDirectory);

        try {
            newStruct = directory.getDirectoryStructure(newDirectory, ".*");
        } catch (IllegalArgumentException ex) {
            ex1 = ex;
        }

        //Assert

        assertEquals(structure.getChildren().size(), newStruct.getChildren().size());
        assertEquals(newStruct.getPath(), newDirectory.replace("/", "\\")); //podmiana ze względu na interpretowanie ścieżki podawanej w stringu
        assertNull(ex1);
    }

    /**
     * Test kopiowania 100 plików
     *
     * @throws IOException          błąd operacji na pliku
     * @throws InterruptedException błąd wątku
     */
    @Test
    public void copy100filesTest() throws IOException, InterruptedException {

        String folderFrom = "./target/testCopyFolderFrom";
        String folderTo = "./target/testCopyFolderTo";

        deleteOldFilesFromDir(folderFrom);
        deleteOldFilesFromDir(folderTo);

        createTestFolderWithFiles(folderFrom);

        Node folderFromNode = new DirectoryService().getDirectoryStructure(folderFrom, ".*");

        ThreadService thread = new ThreadService(8);
        thread.CopyFiles(folderFromNode, folderTo);

        Thread.sleep(1000);

        int expected = countFiles(folderFrom);
        int actual = countFiles(folderTo);

        assertEquals(expected, actual);
    }

    /*
     * Metoda pomocnicza do tworzenia plików do kopiowania
     */
    private String createTestFolderWithFiles(String mainfolder) throws IOException {

        for (int i = 0; i < 10; i++) {
            File folder = new File(mainfolder, "folder-" + String.valueOf(i));
            folder.mkdirs();
            for (int j = 0; j < 10; j++) {
                new File(folder, "plik-" + i + "-" + j).createNewFile();
            }
        }

        return mainfolder;
    }

    /*
     * Metoda pomocnicza do usuwanie starych plików w folderach
     */
    private void deleteOldFilesFromDir(String mainfolder) {
        File mainFOlderFile = new File(mainfolder);
        File[] folderfiles = mainFOlderFile.listFiles();
        if (folderfiles != null) {
            for (File folderFile : folderfiles) {

                File[] files = folderFile.listFiles();

                if (files != null) {
                    for (File file : files) {
                        file.delete();
                    }
                }
                folderFile.delete();
            }
        }
    }

    /*
     * Metoda pomocnicza do zliczania plików w folderze
     */
    private int countFiles(String mainfolder) {
        int fileCount = 0;

        File mainFOlderFile = new File(mainfolder);
        File[] folderfiles = mainFOlderFile.listFiles();
        if (folderfiles != null) {
            for (File folderFile : folderfiles) {
                File[] files = folderFile.listFiles();
                if (files != null) {
                    fileCount += files.length;
                }
            }
        }
        return fileCount;
    }

    /**
     * Konstruktor bezparametrowy
     */
    public ThreadServiceTest() {
    }
}