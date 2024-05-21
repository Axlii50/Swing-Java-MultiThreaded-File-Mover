package pl.wit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Klasa zarządzająca wyjątkami dla procesu kopiowania plików
 *
 * @author Jakub Stegienko
 * @version 1.0
 * @since 2024-05-21
 */
public class ThreadService {

    /**
     * Obiekt do zarządzania zadaniami
     */
    private final ExecutorService executorService;

    /**
     * Konstruktor tworzący serwis wątków z określoną maksymalną liczbą wątków
     *
     * @param maxThreadsUsage maksymalna liczba wątków do wykorzystania
     */
    public ThreadService(int maxThreadsUsage) {
        executorService = Executors.newFixedThreadPool(maxThreadsUsage);
    }

    /**
     * Rozpoczęcie procesu kopiowania plików zgodnie z podaną strukturą plików
     *
     * @param fileStructure struktura plików do skopiowania
     * @param destination   ścieżka docelowa do kopiowania plików
     */
    public void CopyFiles(Node fileStructure, String destination) {
        executorService.execute(new FileService(fileStructure, destination, this.executorService));
    }
}


