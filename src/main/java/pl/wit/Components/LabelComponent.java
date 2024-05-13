package pl.wit.Components;

import javax.swing.*;
import java.awt.*;

public class LabelComponent extends CustomComponent {
    JLabel label;

    public LabelComponent(Dimension dimension, String name) {
        super(dimension, name);
    }

    public JLabel createLabel(String text) {
        label = new JLabel(text);
        label.setPreferredSize(dimension);
        return label;
    }

    public JLabel getLabel() {
        return this.label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
