package Files;

import java.util.HashMap;
import java.util.ArrayList;

public class Directory extends Node{
    private HashMap<String, Node> children;
    private Directory parent;

    public Directory(String name) {
        super(name);
        children = new HashMap<>();
        parent = null;
    }

    public void remove(String name) {
        this.children.remove(name);
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public Directory getParent() {
        return this.parent;
    }

    public void addChild(Node node) {
        children.put(node.getName(), node);
    }

    public Node getChild(String name) {
        return children.get(name);
    }

    public ArrayList<String> getChildren() {
        return new ArrayList<>(children.keySet());
    }
}
