package pl.wit;

import pl.wit.Components.LabelComponent;
import pl.wit.Windows.MainWindow;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Main {
    private static String Source = null;
    private static String Destination = null;
    private static String Regex = null;

    private static ThreadService threadService = null;
    static MainWindow window;
    public static void main(String[] args) {

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

                Node structure = GetFolderStructure();
                threadService.CopyFiles(structure, Destination);
            }
        };
    }

    private static Node GetFolderStructure(){
        return new DirectoryService().getDirectoryStructure(Source, Regex);
    }
}