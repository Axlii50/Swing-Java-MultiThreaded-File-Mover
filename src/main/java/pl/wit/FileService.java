package pl.wit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Klasa odpowiedzialna za kopiowanie plików i katalogów
 */
public class FileService implements Runnable {

    /**
     * Struktura plików do skopiowania
     */
    private final Node fileStructure;
    /**
     * Ścieżka docelowa do kopiowania plików
     */
    private final String destination;
    /**
     * Serwis wykonawczy do zarządzania wątkami
     */
    private final ExecutorService executor;

    /**
     * Konstruktor tworzący serwis plików
     *
     * @param fileStructure struktura plików do skopiowania
     * @param destination ścieżka docelowa do kopiowania plików
     * @param executor serwis wykonawczy do zarządzania wątkami
     */
    public FileService(Node fileStructure, String destination, ExecutorService executor){

        this.fileStructure = fileStructure;
        this.destination = destination;
        this.executor = executor;

    }

    /**
     * Metoda uruchamiana w nowym wątku, rozpoczynająca proces kopiowania
     */
    @Override
    public void run() {

        /**
         * Licznik aktywnych zadań
         */
        AtomicInteger activeTasks = new AtomicInteger(1);

        /**
         * Synchronizator pozwalający na czekanie na zakończenie wszystkich zadań
         */
        CountDownLatch latch = new CountDownLatch(1);

        /**
         * Rozpoczęcie procesu kopiowania katalogu, przekazując strukturę plików, ścieżkę docelową, licznik aktywnych zadań oraz synchronizator
         */
        copyDirectory(fileStructure, destination, activeTasks, latch);

        try {
            /**
             * Czekanie na zakończenie wszystkich zadań
             */
            latch.await();
        } catch (InterruptedException e) {
            /**
             * Obsługiwanie przerwania oczekiwania na zakończenie zadań
             */
            throw new RuntimeException(e);
        }
        /**
         * Zamykanie wykonawcy, aby nie przyjmował nowych zadań
         */
        executor.shutdown();
        try {
            /**
             * Oczekiwanie na zakończenie wszystkich aktywnych zadań wykonywanych przez wykonawcę
             */
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
    /**
     * Obsługiwanie przerwania oczekiwania
     */
            throw new RuntimeException(e);
        }
    }

    /**
     * Kopiowanie katalogu i jego zawartości rekurencyjnie
     *
     * @param node węzeł katalogu do skopiowania
     * @param destinationPath ścieżka docelowa do skopiowania katalogu
     * @param activeTasks licznik aktywnych zadań
     * @param latch synchronizator
     */
    private void copyDirectory(Node node, String destinationPath, AtomicInteger activeTasks, CountDownLatch latch) {
        createDirectory(destinationPath);

        /**
         * Iteracja po każdym dziecku rodzica
         */
        for (Node child : node.getChildren()) {

            /**
             * Utworzenie ścieżki dla nowego elementu bazując na jego nazwie
             */
            String newDestinationPath = destinationPath + "\\" + child.getName();

            /**
             * Zwiększenie liczby aktywnych zadań
             */
            activeTasks.incrementAndGet();

            /**
             * Startowanie nowego zadania do wykonania
             */
            executor.execute(() -> {
                try {
                    if (child instanceof Leaf && child.getChildren().isEmpty()) {
                        copyFile(child.getPath(), newDestinationPath);
                    } else {
                        copyDirectory(child, newDestinationPath, activeTasks, latch);
                    }
                } finally {
                    if (activeTasks.decrementAndGet() == 0) {
                        latch.countDown();
                    }
                }
            });
        }

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
