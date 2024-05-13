package pl.wit.Components;

import javax.swing.*;
import java.awt.*;

public class LabelComponent extends CustomComponent {
    public LabelComponent(Dimension dimension, String name) {
        super(dimension, name);
    }

    public JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(dimension);
        return label;
    }
}
