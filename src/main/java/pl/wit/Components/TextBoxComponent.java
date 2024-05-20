package pl.wit.Components;

import javax.swing.*;
import java.awt.*;

public class TextBoxComponent extends CustomComponent {
    public JTextField textBox;

    public TextBoxComponent(Dimension dimension, String name) {
        super(dimension, name);
    }

    public JTextField createTextBox(String text) {
        textBox = new JTextField(text);
        textBox.setPreferredSize(dimension);
        return textBox;
    }

    public JTextField getTextbox() {
        return this.textBox;
    }

    public void setTextbox(JTextField textbox) {
        this.textBox = textbox;
    }
}
