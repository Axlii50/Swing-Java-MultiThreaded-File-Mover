package pl.wit.Components;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa ButtonComponent rozszerzająca klasę CustomComponent i
 * służąca do tworzenia niestandardowych przycisków w aplikacji.
 */
public class ButtonComponent extends CustomComponent {
    private JButton button;

    /**
     * Konstruktor klasy ButtonComponent
     *
     * @param dimension wymiary przycisku
     * @param name      nazwa komponentu
     */
    public ButtonComponent(Dimension dimension, String name) {
        super(dimension, name);
    }

    /**
     * Utworzenie przycisku z podanym tekstem.
     *
     * @param text tekst wyświetlany na przycisku
     * @return utworzony przycisk
     */
    public JButton createButton(String text) {
        button = new JButton(text);
        button.setPreferredSize(dimension);

        return button;
    }

    /**
     * Zwraca przycisk
     *
     * @return obiekt JButton
     */
    public JButton getButton() {
        return this.button;
    }

    /**
     * Ustawia przycisk
     *
     * @param button obiekt JButton do ustawienia
     */
    public void setButton(JButton button) {
        this.button = button;
    }
}
