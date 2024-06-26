package pl.wit;

import pl.wit.windows.MainWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Główna klasa projektu
 * Zarządza głównym oknem, wątkami i obsługuje interakcję użytkownika z zadaniami kopiowanie pliku
 *
 * @author Jakub Stegienko
 * @author Maciej Nojszewski
 * @version 1.0
 * @since 2024-05-21
 */

public class Main {

    /**
     * Konstruktor bezprametrowy
     */
    public Main() {
    }

    /**
     * Ścieżka źródłowa do kopiowania
     */
    private static String Source = null;

    /**
     * Ścieżka docelowa do kopiowania
     */
    private static String Destination = null;

    /**
     * Wyrażenie używane do filtrowania plików podczas procesu kopiowania
     */
    private static String Regex = null;

    /**
     * Serwis wątków zarządzający wykonywaniem kopiowania
     */
    private static ThreadService threadService = null;

    /**
     * Główne okno projektu (UI)
     */
    static MainWindow window;

    /**
     * Główna metoda programu
     *
     * @param args Parametry uruchamiania programu
     */
    public static void main(String[] args) {

        /*
         * Inicjalizacja serwisu wątków z liczbą dostępnych procesorów
         */
        threadService = new ThreadService(Runtime.getRuntime().availableProcessors());

        /*
         * Inicjalizacja głównego okna
         */
        window = new MainWindow();

        /*
         * Nasłuchiowanie akcji dla przycisku Start
         */
        window.setStartButtonActionListener(StartButtonListener());
    }

    /**
     * Metoda zwracająca wydarzenie dotyczące startu
     *
     * @return zwracanie wydarzenia startu
     */
    private static ActionListener StartButtonListener() {
        return new ActionListener() {
            @Override

            /*
             * Metoda wywoływana po naciśnięciu przycisku start
             *
             * @param e Obiekt zdarzenia akcji
             */
            public void actionPerformed(ActionEvent e) {
                Source = window.getSourcePathString();
                Destination = window.getDestinationPathString();
                Regex = window.getRegexExpression();

                try {

                    /*
                     * Pobieranie struktury folderów na podstawie ścieżki źródłowej i wyrażenia regularnego
                     */
                    Node structure = GetFolderStructure();

                    /*
                     * Proces kopiowania
                     */
                    threadService.CopyFiles(structure, Destination);
                } catch (IllegalArgumentException exp) {

                    /*
                     * Wyświetlanie komunikatu o błędzie użytkownika
                     */
                    window.setErrorLabelString(exp.getMessage());
                    window.setErrorLabelColor(Color.RED);
                    window.setErrorLabelVisible(true);
                    return;
                }

                /*
                 * Wyświetlanie komunikatu o skończeniu kopiowania
                 */
                window.setErrorLabelString("Program skonczył kopiować");
                window.setErrorLabelColor(Color.GREEN);
                window.setErrorLabelVisible(true);
            }
        };
    }

    /**
     * Pobieranie struktury folderów na podstawie ścieżki źródłowej i wyrażenia regularnego
     *
     * @return zwracanie korzenia struktury katalogów
     */
    private static Node GetFolderStructure() {
        return new DirectoryService().getDirectoryStructure(Source, Regex);
    }
}