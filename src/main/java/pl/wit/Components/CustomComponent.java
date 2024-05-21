package pl.wit.Components;

import java.awt.*;

/**
 * Klasa reprezentująca niestandardowy komponent z wymiarami i nazwą
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
     * @param dimension wymiary komponentu
     * @param name nazwa komponentu
     */
    public CustomComponent(Dimension dimension,String name) {
        this.dimension = dimension;
        this.name = name;
    }

}
