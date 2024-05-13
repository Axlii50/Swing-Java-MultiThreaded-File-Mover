package pl.wit.Components;

import javax.swing.*;
import java.awt.*;

public class ButtonComponent extends CustomComponent {
    public ButtonComponent(Dimension dimension, String name) {
        super(dimension, name);
    }

    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(dimension);


        return button;
    }
}
