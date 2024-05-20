package pl.wit;

import java.io.File;
import java.util.regex.Pattern;

public class DirectoryService {

    public Node getDirectoryStructure(String path, String regex) throws IllegalArgumentException {
        File folder = new File(path);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Nieprawidłowa ścieżka: " + path);
        }

        Node root = new Node(folder.getName(), folder.getPath());
        addDirectoryToTree(root, folder, regex);
        return root;
    }

    public void addDirectoryToTree(Node parent, File folder, String regex) throws IllegalArgumentException {
        File[] files = folder.listFiles();

        if (files == null)
            throw new IllegalArgumentException("Nie ma plików w: " + folder.getPath());

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        for (File file : files) {
            Node child = new Node(file.getName(),file.getPath());
            if (file.isDirectory()) {
                parent.addChild(child);
                addDirectoryToTree(child, file, regex);
            }  else if (pattern.matcher(file.getName()).find()) {
                parent.addChild(new Leaf(file.getName(),file.getPath()));
                System.out.println(file.getName());

            }
        }
    }
}