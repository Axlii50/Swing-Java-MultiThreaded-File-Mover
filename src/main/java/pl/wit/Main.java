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
    static MainWindow window;
    public static void main(String[] args) {

        window = new MainWindow();

        window.setSourceButtonActionListener(SourceButtonListener());
        window.setDestinationButtonActionListener(DestinationButtonListener());
    }

    private static ActionListener SourceButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Wybierz folder");
                chooser.setFileSystemView(FileSystemView.getFileSystemView());

                int result = chooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    window.getSourcePathLabelComponent().getLabel().setText(selectedFile.getAbsolutePath());
                    Source = selectedFile.getAbsolutePath();
                }
            }
        };
    }

    private static ActionListener DestinationButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Wybierz folder");
                chooser.setFileSystemView(FileSystemView.getFileSystemView());

                int result = chooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    window.getDestinationPathLabelComponent().getLabel().setText(selectedFile.getAbsolutePath());
                    Destination = selectedFile.getAbsolutePath();
                }
            }
        };
    }
}