package pl.wit.Components;

import javax.swing.*;
import java.awt.*;

public class TextBoxComponent extends CustomComponent {
    public JTextField textbox;

    public TextBoxComponent(Dimension dimension, String name) {
        super(dimension, name);
    }



    public JTextField createTextBox(String text) {
        JTextField textBox = new JTextField(text);
        textBox.setPreferredSize(dimension);
        return textBox;
    }

    public JTextField getTextbox() {
        return this.textbox;
    }

    public void setTextbox(JTextField textbox) {
        this.textbox = textbox;
    }
}
