package pl.wit.Components;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa LabelComponent rozszerzająca klasę CustomComponent i
 * służąca do tworzenia niestandardowych etykiet w aplikacji
 */
public class LabelComponent extends CustomComponent {
    private JLabel label;

    /**
     * Konstruktor klasy LabelComponent
     *
     * @param dimension wymiary etykiety
     * @param name      nazwa komponentu
     */

    public LabelComponent(Dimension dimension, String name) {
        super(dimension, name);
    }

    /**
     * Utworzenie etykiety z podanym tekstem
     *
     * @param text tekst wyświetlany na etykiecie
     * @return utworzona etykieta
     */
    public JLabel createLabel(String text) {
        label = new JLabel(text);
        label.setPreferredSize(dimension);
        return label;
    }

    /**
     * Zwracanie etykiety
     *
     * @return obiekt JLabel
     */
    public JLabel getLabel() {
        return this.label;
    }

    /**
     * Ustawianie etykiety
     *
     * @param label Obiekt JLabel do ustawienia
     */
    public void setLabel(JLabel label) {
        this.label = label;
    }
}
