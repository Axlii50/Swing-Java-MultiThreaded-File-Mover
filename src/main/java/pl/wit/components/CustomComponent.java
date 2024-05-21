package pl.wit.components;

import java.awt.*;

/**
 * Klasa reprezentująca niestandardowy komponent z wymiarami i nazwą
 *
 * @author Maciej Nojszewski
 * @version 1.0
 * @since 2024-05-21
 */
public class CustomComponent {

    /**
     * Wymiary komponentu.
     */
    Dimension dimension;

    /**
     * Nazwa komponentu.
     */
    String name;

    /**
     * Konstruktor tworzący niestandardowy komponent
     *
     * @param dimension wymiary komponentu
     * @param name      nazwa komponentu
     */
    public CustomComponent(Dimension dimension, String name) {
        this.dimension = dimension;
        this.name = name;
    }

}
