package pl.wit.components;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa TextBoxComponent rozszerzająca klasę CustomComponent
 * służąca do tworzenia niestandardowych pól tekstowych w aplikacji
 *
 * @author Maciej Nojszewski
 * @author Adam Bartos
 * @version 1.0
 * @since 2024-05-21
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

}
