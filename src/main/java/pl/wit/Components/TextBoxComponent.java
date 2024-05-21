package pl.wit.Components;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa TextBoxComponent rozszerzająca klasę CustomComponent
 * służąca do tworzenia niestandardowych pól tekstowych w aplikacji
 */
public class TextBoxComponent extends CustomComponent {
    private JTextField textBox;

    /**
     * Konstruktor klasy TextBoxComponent
     *
     * @param dimension wymiary pola tekstowego
     * @param name      nazwa komponentu
     */
    public TextBoxComponent(Dimension dimension, String name) {
        super(dimension, name);
    }

    /**
     * Utworzenie pola tekstowego z podanym tekstem
     *
     * @param text tekst wyświetlany w polu tekstowym
     * @return utworzone pole tekstowe
     */
    public JTextField createTextBox(String text) {
        textBox = new JTextField(text);
        textBox.setPreferredSize(dimension);
        return textBox;
    }

    /**
     * Zwrócenie pola tekstowego
     *
     * @return obiekt JTextField.
     */
    public JTextField getTextbox() {
        return this.textBox;
    }

    /**
     * Ustawienie pola tekstowego
     *
     * @param textbox obiekt JTextField do ustawienia.
     */
    public void setTextbox(JTextField textbox) {
        this.textBox = textbox;
    }
}
