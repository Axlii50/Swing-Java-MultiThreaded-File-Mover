package pl.wit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {
    private String name;
    private List<Node> children;

    public Node(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }
}

class Leaf extends Node {
    public Leaf(String name) {
        super(name);
    }

    @Override
    public List<Node> getChildren() {
        return Collections.emptyList(); // Liście nie mają potomnych
    }
}