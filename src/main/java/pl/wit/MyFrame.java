package pl.wit;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private JPanel panel;
    private JPanel sourcePanel;
    private JPanel destinationPanel;
    private JPanel maskFilterPanel;
    private JPanel infoPanel;
    private JPanel startPanel;

    private LabelComponent sourceLabelComponent;
    private ButtonComponent sourceButtonComponent;
    private LabelComponent sourcePathLabelComponent;

    private LabelComponent destinationLabelComponent;
    private ButtonComponent destinationButtonComponent;
    private LabelComponent destinationPathLabelComponent;

    private LabelComponent filterLabelComponent;
    private TextBoxComponent filterTextBoxComponent;

    private LabelComponent filesToCopyLabel;
    private LabelComponent filesFoundLabel;
    private LabelComponent filesSizeLabel;

    private ButtonComponent startButtonComponent;

    public MyFrame() {
        this.setTitle("Java"); // sets title of frame
        this.setSize(300, 435); // sets dimensions of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        this.setResizable(true); // prevent frame from being resized
        this.setVisible(true); // make frame visible
        this.setLayout(new BorderLayout());

        ImageIcon image = new ImageIcon("me.jpg");
        this.setIconImage(image.getImage());

        init();
    }

    private void init() {
        sourcePanelInit();
        destinationPanelInit();
        maskFilterPanelInit();
        infoPanelInit();
        startPanelInit();
        mainPanelInit();
    }

    private void mainPanelInit() {
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        this.add(panel, BorderLayout.WEST);
        panel.setPreferredSize(new Dimension(300, 400));

        panel.add(sourcePanel);
        panel.add(destinationPanel);
        panel.add(maskFilterPanel);
        panel.add(infoPanel);
        panel.add(startPanel);
    }

    private void sourcePanelInit() {
        sourcePanel = new JPanel();
        sourcePanel.setBackground(Color.WHITE);
        sourcePanel.setPreferredSize(new Dimension(300, 100));
        sourcePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        sourceLabelComponent = new LabelComponent(new Dimension(new Dimension(125, 30)), "sourceLabel");
        sourceButtonComponent = new ButtonComponent(new Dimension(125, 30), "sourceButton");
        sourcePathLabelComponent = new LabelComponent(new Dimension(280, 40), "sourcePathLabel");

        sourcePanel.add(sourceLabelComponent.createLabel("Source Directory"));
        sourcePanel.add(sourceButtonComponent.createButton("Browse..."));
        sourcePanel.add(sourcePathLabelComponent.createLabel("Source Path..."));
    }

    private void destinationPanelInit() {
        destinationPanel = new JPanel();
        destinationPanel.setBackground(Color.WHITE);
        destinationPanel.setPreferredSize(new Dimension(300, 100));
        destinationPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        destinationLabelComponent = new LabelComponent(new Dimension(new Dimension(125, 30)), "destinationLabel");
        destinationButtonComponent = new ButtonComponent(new Dimension(125, 30), "destinationButton");
        destinationPathLabelComponent = new LabelComponent(new Dimension(280, 40), "destinationPathLabel");

        destinationPanel.add(destinationLabelComponent.createLabel("Destination Directory"));
        destinationPanel.add(destinationButtonComponent.createButton("Browse..."));
        destinationPanel.add(destinationPathLabelComponent.createLabel("Destination Path..."));
    }

    private void maskFilterPanelInit() {
        maskFilterPanel = new JPanel();
        maskFilterPanel.setBackground(Color.WHITE);
        maskFilterPanel.setPreferredSize(new Dimension(300, 50));
        maskFilterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        filterLabelComponent = new LabelComponent(new Dimension(new Dimension(70, 30)), "filterLabel");
        filterTextBoxComponent = new TextBoxComponent(new Dimension(190, 30), "filterTextBox");

        maskFilterPanel.add(filterLabelComponent.createLabel("Mask Filter"));
        maskFilterPanel.add(filterTextBoxComponent.createTextBox(""));
    }

    private void infoPanelInit() {
        infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setPreferredSize(new Dimension(300, 80));
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        filesToCopyLabel = new LabelComponent(new Dimension(new Dimension(140, 30)), "filesToCopyLabel");
        filesFoundLabel = new LabelComponent(new Dimension(new Dimension(140, 30)), "filesFoundLabel");
        filesSizeLabel = new LabelComponent(new Dimension(new Dimension(140, 30)), "filesSizeLabel");

        infoPanel.add(filesToCopyLabel.createLabel("Files To Copy: 0"));
        infoPanel.add(filesFoundLabel.createLabel("Files Found: 0"));
        infoPanel.add(filesSizeLabel.createLabel("Files Size: 0"));
    }

    private void startPanelInit() {
        startPanel = new JPanel();
        startPanel.setBackground(Color.WHITE);
        startPanel.setPreferredSize(new Dimension(300, 40));
        startPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        startButtonComponent = new ButtonComponent(new Dimension(200, 30), "startButton");
        startPanel.add(startButtonComponent.createButton("Start"));
    }
}
