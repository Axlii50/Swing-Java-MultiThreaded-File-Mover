package pl.wit;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryService {

    public Node getDirectoryStructure(String path, String regex) throws IllegalArgumentException {
        File folder = new File(path);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Nieprawidłowa ścieżka: " + path);
        }

        Node root = new Node(folder.getName());
        addDirectoryToTree(root, folder, regex);
        return root;
    }

    public void addDirectoryToTree(Node parent, File folder, String regex) {
        for (File file : folder.listFiles()) {
            Node child = new Node(file.getName());
            if (file.isDirectory()) {
                parent.addChild(child);
                addDirectoryToTree(child, file, regex);
            }  else if (file.getName().matches(regex)) {
                parent.addChild(new Leaf(file.getName()));
            }
        }
    }
}