package pl.wit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class DirectoryService {

    public List<String> getDirectoryStructure(String path) {
        File folder = new File(path);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Nieprawidłowa ścieżka: " + path);
        }

        List<String> directoryStructure = new ArrayList<>();
        addDirectoryToList(directoryStructure, folder);
        return directoryStructure;
    }

    private void addDirectoryToList(List<String> directoryStructure, File folder) {
        directoryStructure.add(folder.getAbsolutePath());
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                addDirectoryToList(directoryStructure, file);
            } else {
                directoryStructure.add(file.getAbsolutePath());
            }
        }
    }
}
