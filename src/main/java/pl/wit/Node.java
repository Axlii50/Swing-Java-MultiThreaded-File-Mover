package pl.wit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {
    private String name;
    private List<Node> children;
    private String path;

    public Node(String name, String path) {
        this.name = name;
        this.path = path;
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

    public String getPath() {
        return path;
    }
}

class Leaf extends Node {
    public Leaf(String name, String path) {
        super(name, path);
    }

    @Override
    public List<Node> getChildren() {
        return Collections.emptyList(); // Liście nie mają potomnych
    }
}