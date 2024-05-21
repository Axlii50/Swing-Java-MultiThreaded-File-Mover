package pl.wit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
        AtomicInteger activeTasks = new AtomicInteger(1);  // Start with 1 to account for the initial task
        CountDownLatch latch = new CountDownLatch(1);

        copyDirectory(fileStructure, destination, activeTasks, latch);

        try {
            latch.await();  // Wait for all tasks to complete
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void copyDirectory(Node node, String destinationPath, AtomicInteger activeTasks, CountDownLatch latch) {
        createDirectory(destinationPath);

        //iterujemy po kazdym dziecku rodzica
        for (Node child : node.getChildren()) {
            //tworzymy sciezke dla nowego elementu bazujac na jego nazwie
            String newDestinationPath = destinationPath + "\\" + child.getName();
            //zwiekszamy ilosc akwywnych wątków
            activeTasks.incrementAndGet();
            //startujemy nowe zadanie do wykonania
            executor.execute(() -> {
                try {
                    //wykonaj adekwatne do typu zadanie
                    if (child instanceof Leaf && child.getChildren().isEmpty()) {
                        copyFile(child.getPath(), newDestinationPath);
                    } else {
                        copyDirectory(child, newDestinationPath, activeTasks, latch);
                    }
                } finally {
                    //jeżeli wszystkie zadania zostały zakonczone to zwalniamy blokade (latch)
                    if (activeTasks.decrementAndGet() == 0) {
                        latch.countDown();
                    }
                }
            });
        }

        //jeżeli wszystkie zadania zostały zakonczone to zwalniamy blokade (latch)
        if (activeTasks.decrementAndGet() == 0) {
            latch.countDown();
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
