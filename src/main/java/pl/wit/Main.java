package pl.wit;

import pl.wit.Components.LabelComponent;
import pl.wit.Windows.MainWindow;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Main {
    private String Source = null;
    private String Destination = null;
    static MainWindow window;
    public static void main(String[] args) {

        window = new MainWindow();

        window.setStartButtonActionListener(StartButtonListener());
    }

    private static ActionListener StartButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Wybierz folder");
                chooser.setFileSystemView(FileSystemView.getFileSystemView());

                int result = chooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                }
            }
        };
    }
}