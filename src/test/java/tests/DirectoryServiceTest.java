package tests;

import org.junit.jupiter.api.Test;
import pl.wit.DirectoryService;
import pl.wit.Node;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class DirectoryServiceTest {

    @Test
    public void getDirectoryStructureTest()
    {
        //Arrange

        DirectoryService directory = new DirectoryService();
        IllegalArgumentException ex1 = null;
        IllegalArgumentException ex2 = null;
        IllegalArgumentException ex3 = null;
        IllegalArgumentException ex4 = null;
        Node correctStructure = new Node("name","/.");

        //Act

        try {correctStructure = directory.getDirectoryStructure("/.",".*");}
        catch(IllegalArgumentException e)
        {
            ex1=e;
        }

        try {correctStructure = directory.getDirectoryStructure("/blednasciezka",".*");}
        catch(IllegalArgumentException e)
        {
            ex2=e;
        }

        try {correctStructure = directory.getDirectoryStructure("",".*");}
        catch(IllegalArgumentException e)
        {
            ex3=e;
        }
        try {correctStructure = directory.getDirectoryStructure("src/test/java/testDirectory",".*");}
        catch(IllegalArgumentException e)
        {
            ex4=e;
        }

        //Assert

        assertNotNull(ex1);
        assertNotNull(ex2);
        assertNotNull(ex3);
        assertNull(ex4);
    }

    @Test
    public void addDirectoryToTreeTest()
    {
        //Arrange

        DirectoryService directory = new DirectoryService();
        IllegalArgumentException ex1 = null;
        IllegalArgumentException ex2 = null;
        IllegalArgumentException ex3 = null;
        Node parent1 = new Node("name","/");
        int childrensize = parent1.getChildren().size();
        File correctfile = new File("src/test/java/testDirectory");
        File incorrectfile = new File("src/test/java/testDirectory/incorrectFile");

        //Act

        try {directory.addDirectoryToTree(parent1,correctfile, ".*" ); }
        catch(IllegalArgumentException e)
        {
            ex1=e;
        }

        try {directory.addDirectoryToTree(parent1,incorrectfile, ".*" ); }
        catch(IllegalArgumentException e)
        {
            ex2=e;
        }

        try {directory.addDirectoryToTree(parent1,correctfile, "(?:test)" ); }
        catch(IllegalArgumentException e)
        {
            ex3=e;
        }

        //Assert

        assertNull(ex1);
        assertNotNull(ex2);
        assertNull(ex3);
        assertNotEquals(childrensize,parent1.getChildren().size());
    }
}
