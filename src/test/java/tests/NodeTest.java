package tests;

import org.junit.jupiter.api.Test;
import pl.wit.Node;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Klasa testowa dla Node
 */
public class NodeTest {

    /**
     * Testowanie dodawania dzieci do węzła
     */
    @Test
    public void wholeNodeTest() {
        //Arrange
        Node main = new Node("mainNode", "src/test/java/additionalDirectory");
        Node child = new Node("childNode", "src/test/java/testDirectory");

        //Act

        String mainName = main.getName();
        String childName = child.getName();
        main.addChild(child);
        String mainpath = main.getPath();
        String childpath = child.getPath();

        //Assert

        assertEquals("mainNode", mainName);
        assertEquals("childNode", childName);
        assertEquals("src/test/java/additionalDirectory", mainpath);
        assertEquals("src/test/java/testDirectory", childpath);
        assertEquals(main.getChildren().getFirst(), child);
    }
}