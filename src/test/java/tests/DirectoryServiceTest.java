package tests;

import org.junit.jupiter.api.Test;
import pl.wit.DirectoryService;
import pl.wit.Node;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Klasa testowa dla DirectoryService
 *
 * @author Damian Dziedzic
 * @version 1.0
 * @since 2024-05-21
 */
public class DirectoryServiceTest {

    /**
     * Testowanie metody getDirectoryStructureCorrectPath z poprawną ściezką
     */
    @Test
    public void getDirectoryStructureCorrectPathTest() {
        //Arrange

        DirectoryService directory = new DirectoryService();
        IllegalArgumentException ex1 = null;
        Node correctStructure = new Node("name", "/.");

        //Act
        try {
            correctStructure = directory.getDirectoryStructure("src/test/java/testDirectory", ".*");
        } catch (IllegalArgumentException e) {
            ex1 = e;
        }

        //Assert
        assertNull(ex1);
    }

    /**
     * Testowanie metody getDirectoryStructure z niepoprawną ścieżką
     */
    @Test
    public void getDirectoryStructureFailurePathTest() {
        //Arrange

        DirectoryService directory = new DirectoryService();
        IllegalArgumentException ex1 = null;
        Node correctStructure = new Node("name", "/.");

        //Act

        try {
            correctStructure = directory.getDirectoryStructure("/blednasciezka", ".*");
        } catch (IllegalArgumentException e) {
            ex1 = e;
        }

        //Assert

        assertNotNull(ex1);
    }

    /**
     * Testowanie metody getDirectoryStructure z pustą ścieżką
     */
    @Test
    public void getDirectoryStructureEmptyPathTest() {
        //Arrange

        DirectoryService directory = new DirectoryService();
        IllegalArgumentException ex1 = null;
        Node correctStructure = new Node("name", "/.");

        //Act

        try {
            correctStructure = directory.getDirectoryStructure("", ".*");
        } catch (IllegalArgumentException e) {
            ex1 = e;
        }

        //Assert

        assertNotNull(ex1);
    }

    /**
     * Testowanie metody addDirectoryToTree z poprawnym folderem
     */
    @Test
    public void addDirectoryToTreeCorrectDirectoryTest() {
        //Arrange

        DirectoryService directory = new DirectoryService();
        IllegalArgumentException ex1 = null;
        Node parent1 = new Node("name", "/");
        File correctfile = new File("src/test/java/testDirectory");

        //Act

        try {
            directory.addDirectoryToTree(parent1, correctfile, ".*");
        } catch (IllegalArgumentException e) {
            ex1 = e;
        }

        //Assert

        assertNull(ex1);
    }

    /**
     * Testowanie metody addDirectoryToTree z niepoprawnym folderem
     */
    @Test
    public void addDirectoryToTreeIncorrectDirectoryTest() {
        //Arrange

        DirectoryService directory = new DirectoryService();
        IllegalArgumentException ex1 = null;
        Node parent1 = new Node("name", "/");
        File incorrectfile = new File("src/test/java/testDirectory/incorrectFile");

        //Act

        try {
            directory.addDirectoryToTree(parent1, incorrectfile, ".*");
        } catch (IllegalArgumentException e) {
            ex1 = e;
        }

        //Assert

        assertNotNull(ex1);
    }

    /**
     * Testowanie metody addDirectoryToTree z wyrażeniem regularnym
     */
    @Test
    public void addDirectoryToTreeWithRegexTest() {
        //Arrange

        DirectoryService directory = new DirectoryService();
        IllegalArgumentException ex1 = null;
        Node parent1 = new Node("name", "src/test/java/AdditionalDirectory");
        int childrensize = parent1.getChildren().size();
        File correctfile = new File("src/test/java/testDirectory");

        //Act

        try {
            directory.addDirectoryToTree(parent1, correctfile, "(?:test)");
        } catch (IllegalArgumentException e) {
            ex1 = e;
        }

        //Assert

        assertNull(ex1);
        assertNotEquals(childrensize, parent1.getChildren().size());
    }
    
    /**
     * Konstruktor bezparametrowy
     */
    public DirectoryServiceTest() {
    }
}
