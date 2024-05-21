package pl.wit;

import pl.wit.Windows.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Klasa główna projektu
 */

public class Main {
    private static String Source = null;
    private static String Destination = null;
    private static String Regex = null;
    private static ThreadService threadService = null;
    static MainWindow window;

    public static void main(String[] args) {
        threadService = new ThreadService(Runtime.getRuntime().availableProcessors());
        window = new MainWindow();
        window.setStartButtonActionListener(StartButtonListener());
    }

    private static ActionListener StartButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Source = window.getSourcePathString();
                Destination = window.getDestinationPathString();
                Regex = window.getRegexExpression();

                try{
                    Node structure = GetFolderStructure();

                    // kopiowanie
                    threadService.CopyFiles(structure, Destination);
                }catch(IllegalArgumentException exp) {
                    window.setErrorLabelString(exp.getMessage());
                    window.setErrorLabelVisible(true);
                }

                threadService.Shutdown();
            }
        };
    }

    private static Node GetFolderStructure(){
        return new DirectoryService().getDirectoryStructure(Source, Regex);
    }
}