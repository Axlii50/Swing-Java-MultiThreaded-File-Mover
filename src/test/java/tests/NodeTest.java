package tests;

import org.junit.jupiter.api.Test;
import pl.wit.Node;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void wholeNodeTest()
    {
        //Arrange
        Node main = new Node("mainNode","src/test/java/additionalDirectory");
        Node child = new Node("childNode","src/test/java/testDirectory");

        //Act

        String mainName = main.getName();
        String childName = child.getName();
        main.addChild(child);
        String mainpath = main.getPath();
        String childpath = child.getPath();

        //Assert

        assertSame("mainNode",mainName);
        assertSame("childNode",childName);
        assertSame("src/test/java/additionalDirectory",mainpath);
        assertSame("src/test/java/testDirectory",childpath);
        assertSame(main.getChildren().getFirst(),child);
    }
}