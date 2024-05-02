package pl.wit;

import javax.swing.*;
import java.awt.*;

public class TextBoxComponent extends CustomComponent {
    public TextBoxComponent(Dimension dimension, String name) {
        super(dimension, name);
    }

    public JTextField createTextBox(String text) {
        JTextField textBox = new JTextField(text);
        textBox.setPreferredSize(dimension);
        return textBox;
    }
}
