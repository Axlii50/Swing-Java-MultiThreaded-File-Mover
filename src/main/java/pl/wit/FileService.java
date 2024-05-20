package pl.wit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;

public class FileService implements Runnable {

    private final Node fileStructure;
    private final String destination;
    private final ExecutorService executor;

    public FileService(Node fileStructure, String destination, ExecutorService executor){

        this.fileStructure = fileStructure;
        this.destination = destination;
        this.executor = executor;

    }

    @Override
    public void run() {
        copyDirectory(fileStructure, destination);
    }

    private void copyDirectory(Node node, String destinationPath) {
        createDirectory(destinationPath);

        for (Node child : node.getChildren()) {
            String newDestinationPath = destinationPath + "\\" + child.getName();
            if (child instanceof Leaf && child.getChildren().isEmpty()) {
                executor.execute(() -> copyFile(child.getPath(), newDestinationPath));
            } else {
                executor.execute(() -> copyDirectory(child, newDestinationPath));
            }
        }
    }

    private void createDirectory(String path) {
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFile(String filePath, String destinationPath) {
        try {
            Files.copy(Paths.get(filePath), Paths.get(destinationPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
