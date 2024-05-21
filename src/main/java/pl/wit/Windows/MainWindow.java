package pl.wit.Windows;

import pl.wit.Components.ButtonComponent;
import pl.wit.Components.LabelComponent;
import pl.wit.Components.TextBoxComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

/**
 * Klasa MainWindow odpowiada za główne okno aplikacji, w której użytkownik może wybrać
 * źródłowy folder, docelowy folder, ustawić filtr maski oraz uruchomić proces kopiowania plików
 */
public class MainWindow extends JFrame {
    private JPanel sourcePanel;
    private JPanel destinationPanel;
    private JPanel maskFilterPanel;
    private JPanel startPanel;
    private JPanel errorPanel;

    private ButtonComponent sourceButtonComponent;
    private LabelComponent sourcePathLabelComponent;

    private ButtonComponent destinationButtonComponent;
    private LabelComponent destinationPathLabelComponent;

    private TextBoxComponent filterTextBoxComponent;

    private LabelComponent errorLabelComponent;

    private ButtonComponent startButtonComponent;

    /**
     * Pobieranie komponentu etykiety błędu
     *
     * @return komponent etykiety błędu
     */
    public LabelComponent getErrorLabelComponent() {
        return errorLabelComponent;
    }

    /**
     * Pobieranie komponentu przycisku wyboru folderu docelowego
     *
     * @return komponent przycisku wyboru folderu docelowego
     */
    public ButtonComponent getDestinationButtonComponent() {
        return destinationButtonComponent;
    }

    public LabelComponent getDestinationPathLabelComponent() {
        return destinationPathLabelComponent;
    }

    public ButtonComponent getSourceButtonComponent() {
        return sourceButtonComponent;
    }

    public LabelComponent getSourcePathLabelComponent() {
        return sourcePathLabelComponent;
    }

    public TextBoxComponent getFilterTextBoxComponent() {
        return filterTextBoxComponent;
    }

    public ButtonComponent getStartButtonComponent() {
        return startButtonComponent;
    }

    public void setErrorLabelVisible(boolean errorLabelVisible) {
        errorLabelComponent.getLabel().setVisible(errorLabelVisible);
    }

    public String getSourcePathString() {
        return sourcePathLabelComponent.getLabel().getText();
    }

    public String getDestinationPathString() {
        return destinationPathLabelComponent.getLabel().getText();
    }

    public String getRegexExpression() {
        return filterTextBoxComponent.getTextbox().getText();
    }

    public void setErrorLabelString(String str) {
        errorLabelComponent.getLabel().setText(str);
    }

    public void setErrorLabelColor(Color color) {
        errorLabelComponent.getLabel().setForeground(color);
    }

    /**
     * Klasa MainWindow odpowiada za główne okno aplikacji, w której użytkownik może wybrać
     * źródłowy folder, docelowy folder, ustawić filtr maski oraz uruchomić proces kopiowania plików
     */
    public MainWindow() {
        this.setTitle("Java"); // sets title of frame
        this.setSize(300, 375); // sets dimensions of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        this.setResizable(false); // prevent frame from being resized
        this.setLayout(new BorderLayout());
        init();

        ImageIcon image = new ImageIcon("me.jpg");
        this.setIconImage(image.getImage());

        this.setVisible(true); // make frame visible
    }

    private void init() {
        sourcePanelInit();
        destinationPanelInit();
        maskFilterPanelInit();
        errorPanelInit();
        startPanelInit();
        mainPanelInit();
    }

    private void mainPanelInit() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        this.add(panel, BorderLayout.WEST);
        panel.setPreferredSize(new Dimension(300, 400));

        panel.add(sourcePanel);
        panel.add(destinationPanel);
        panel.add(maskFilterPanel);
        panel.add(errorPanel);
        panel.add(startPanel);
    }

    private void sourcePanelInit() {
        sourcePanel = new JPanel();
        sourcePanel.setBackground(Color.WHITE);
        sourcePanel.setPreferredSize(new Dimension(300, 100));
        sourcePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        LabelComponent sourceLabelComponent = new LabelComponent(new Dimension(new Dimension(125, 30)), "sourceLabel");
        sourceButtonComponent = new ButtonComponent(new Dimension(125, 30), "sourceButton");
        sourcePathLabelComponent = new LabelComponent(new Dimension(280, 40), "sourcePathLabel");

        sourcePanel.add(sourceLabelComponent.createLabel("Source Directory"));
        sourcePanel.add(sourceButtonComponent.createButton("Browse..."));
        sourcePanel.add(sourcePathLabelComponent.createLabel("Source Path..."));

        sourceButtonComponent.getButton().addActionListener(filePathActionListener(sourcePathLabelComponent));
    }

    public void setSourceButtonActionListener(ActionListener actionListener) {
        sourceButtonComponent.getButton().addActionListener(actionListener);
    }

    private void destinationPanelInit() {
        destinationPanel = new JPanel();
        destinationPanel.setBackground(Color.WHITE);
        destinationPanel.setPreferredSize(new Dimension(300, 100));
        destinationPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        LabelComponent destinationLabelComponent = new LabelComponent(new Dimension(new Dimension(125, 30)), "destinationLabel");
        destinationButtonComponent = new ButtonComponent(new Dimension(125, 30), "destinationButton");
        destinationPathLabelComponent = new LabelComponent(new Dimension(280, 40), "destinationPathLabel");

        destinationPanel.add(destinationLabelComponent.createLabel("Destination Directory"));
        destinationPanel.add(destinationButtonComponent.createButton("Browse..."));
        destinationPanel.add(destinationPathLabelComponent.createLabel("Destination Path..."));

        destinationButtonComponent.getButton().addActionListener(filePathActionListener(destinationPathLabelComponent));
    }

    public void setDestinationButtonActionListener(ActionListener actionListener) {
        destinationButtonComponent.getButton().addActionListener(actionListener);
    }

    private void maskFilterPanelInit() {
        maskFilterPanel = new JPanel();
        maskFilterPanel.setBackground(Color.WHITE);
        maskFilterPanel.setPreferredSize(new Dimension(300, 30));
        maskFilterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        LabelComponent filterLabelComponent = new LabelComponent(new Dimension(new Dimension(120, 20)), "filterLabel");
        filterTextBoxComponent = new TextBoxComponent(new Dimension(140, 20), "filterTextBox");

        maskFilterPanel.add(filterLabelComponent.createLabel("Mask Filter (regex)"));
        maskFilterPanel.add(filterTextBoxComponent.createTextBox(".*"));
    }


    private void errorPanelInit() {
        errorPanel = new JPanel();
        errorPanel.setBackground(Color.WHITE);
        errorPanel.setPreferredSize(new Dimension(300, 30));
        errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        errorLabelComponent = new LabelComponent(new Dimension(new Dimension(285, 25)), "errorLabel");

        errorPanel.add(errorLabelComponent.createLabel("Error"));
        errorLabelComponent.getLabel().setForeground(Color.RED);
        errorLabelComponent.getLabel().setVisible(false);
    }

    private void startPanelInit() {
        startPanel = new JPanel();
        startPanel.setBackground(Color.WHITE);
        startPanel.setPreferredSize(new Dimension(300, 40));
        startPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        startButtonComponent = new ButtonComponent(new Dimension(200, 30), "startButton");

        startPanel.add(startButtonComponent.createButton("Start"));
    }

    public void setStartButtonActionListener(ActionListener actionListener) {
        startButtonComponent.getButton().addActionListener(actionListener);
    }

    private ActionListener filePathActionListener(LabelComponent labelPathComponent) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Wybierz folder");

                chooser.setFileSystemView(FileSystemView.getFileSystemView());
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int result = chooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    labelPathComponent.getLabel().setText(selectedFile.getAbsolutePath());
                }
            }
        };
    }

}
