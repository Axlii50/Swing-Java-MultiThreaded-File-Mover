package pl.wit.Components;

import javax.swing.*;
import java.awt.*;


public class ButtonComponent extends CustomComponent {
    public JButton button;

    public ButtonComponent(Dimension dimension, String name) {
        super(dimension, name);
    }

    public JButton createButton(String text) {
        button = new JButton(text);
        button.setPreferredSize(dimension);

        return button;
    }

    public JButton getButton() {
        return this.button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }
}
