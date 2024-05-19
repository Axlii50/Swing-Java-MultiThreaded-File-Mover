package tests;

import org.junit.jupiter.api.Test;
import pl.wit.DirectoryService;
import pl.wit.Node;
import pl.wit.ThreadService;

import static org.junit.jupiter.api.Assertions.*;

class ThreadServiceTest {

    @Test
    public void CopyFilestest()
    {
        //Arrange

        ThreadService thread = new ThreadService(5);
        Node structure = new Node("default","src/test/java/testDirectory");
        String newDirectory = "src/test/java/additionalDirectory";

        DirectoryService directory = new DirectoryService();
        IllegalArgumentException ex1 = null;
        Node newStruct = new Node("new","/.");
        //Act

        thread.CopyFiles(structure,newDirectory);

      try{ newStruct = directory.getDirectoryStructure(newDirectory,".*");}
      catch(IllegalArgumentException ex)
      {
          ex1 = ex;
      }

        //Assert

        assertEquals(structure.getChildren().size(), newStruct.getChildren().size());
        assertEquals(newStruct.getPath(), newDirectory.replace("/","\\")); // podmiana ze względu na różnicę między przekazywanym stringiem a tworzoną ścieżką,
        //różnica wynika ze specyfiki języka i guess.
        assertNull(ex1);
    }
}