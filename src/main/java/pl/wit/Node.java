package pl.wit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Klasa reprezentująca rozgałęzienia drzewa katalogów
 */
public class Node {
    /**
     * Nazwa węzła
     */
    private String name;
    /**
     * Lista dzieci (podwęzłów) tego węzła
     */
    private List<Node> children;
    /**
     * Ścieżka do węzła
     */
    private String path;

    /**
     * Konstruktor tworzący węzeł
     *
     * @param name nazwa węzła
     * @param path ścieżka do węzła
     */
    public Node(String name, String path) {
        this.name = name;
        this.path = path;
        this.children = new ArrayList<>();
    }

    /**
     * Dodaje dziecko do tego węzła
     *
     * @param child węzeł dziecka
     */
    public void addChild(Node child) {
        children.add(child);
    }

    public String getName() {
        return name;
    }

    public List<Node> getChildren() {
        return children;
    }

    public String getPath() {
        return path;
    }
}

/**
 * Klasa reprezentująca liść drzewa katalogów, który nie ma potomnych
 */
class Leaf extends Node {

    /**
     * Konstruktor tworzący liść
     *
     * @param name nazwa liścia
     * @param path ścieżka do liścia
     */
    public Leaf(String name, String path) {
        super(name, path);
    }

    /**
     * Pobieranie listy dzieci danego liścia
     *
     * @return pusta lista, ponieważ liść nie ma dzieci
     */
    @Override
    public List<Node> getChildren() {
        return Collections.emptyList();
    }
}