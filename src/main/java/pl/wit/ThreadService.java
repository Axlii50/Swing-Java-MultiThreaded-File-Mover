package pl.wit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadService {
    private final ExecutorService executorService;

    public ThreadService(int maxThreadsUsage) {
        executorService = Executors.newFixedThreadPool(maxThreadsUsage);
    }

    public void CopyFiles(Node fileStructure, String destination) {
        executorService.execute(new FileService(fileStructure, destination, this.executorService));
    }

    public void Shutdown() {
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown(); // Rozpocznij zamykanie wątków
    }
}


