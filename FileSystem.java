package Files;

import java.util.ArrayList;
import java.util.Collections;

public class FileSystem {
    private Directory root;
    private Directory pwd;

    public FileSystem() {
        root = new Directory("~");
        pwd = root;
    }

    public void mkdir(String name) {
        if (pwd.getChildren().contains(name)) {
            System.out.println("Already exists: " + name);
            return;
        }

        Directory newDirectory = new Directory(name);
        newDirectory.setParent(pwd);

        pwd.addChild(newDirectory);

    }

    public void touch(String name) {
        if (pwd.getChildren().contains(name)) {
            System.out.println("Already exists: " + name);
            return;
        }

        pwd.addChild(new File(name));
    }

    public void ls() {
        for (String file : pwd.getChildren()) {
            Node child = pwd.getChild(file);

            if (child instanceof Directory) {
                System.out.println(file + "/");
            } else {
                System.out.println(file);
            }
        }
    }

    public void cd() {
        pwd = root;
    }

    public void cd(String name) {
        if (name.equals("..")) {
            if (pwd.getParent() != null) {
                pwd = pwd.getParent();
            }
            return;
        }

        Node child = pwd.getChild(name);

        if (child == null) {
            System.out.println("No such file or directory: " + name);
            return;
        } else if (child instanceof File) {
            System.out.println("Not a directory: " + name);
            return;
        }

        pwd = (Directory) child;
    }

    public String getPwdPath() {
        ArrayList<String> path = new ArrayList<>();
        Directory tmp = pwd;

        while (tmp != null) {
            path.add(tmp.getName());
            tmp = tmp.getParent();
        }

        Collections.reverse(path);
        return String.join("/", path);
    }

    public void rm(String name) {
        if (pwd.getChild(name) == null) {
            System.out.println("No such file or directory: " + name);
            return;
        } else if (pwd.getChild(name) instanceof Directory) {
            Directory removedDirectory = (Directory) pwd.getChild(name);
            if (removedDirectory.getChildren().size() > 0) {
                System.out.println("Error: " + name + " is not empty.");
                return;
            }
        }

        pwd.remove(name);
    }

    public void tree() {
        System.out.println(pwd.getName());
        dirTree(pwd, "");
    }

    public void dirTree(Directory dir, String indent) {
        ArrayList<String> children = dir.getChildren();
        boolean isLast;
        String modIndent;

        for (int i = 0; i < children.size(); i++) {
            if (i == children.size() - 1) {
                isLast = true;
            } else {
                isLast = false;
            }

            String name = children.get(i);
            String connector = "";

            if (isLast) {
                connector = "└── ";
                modIndent = indent + "    ";
            } else {
                connector = "├── ";
                modIndent = indent + "|   ";
            }
            
            System.out.println(indent + connector + name);

            if (dir.getChild(name) instanceof Directory) {
                dirTree((Directory) dir.getChild(name), modIndent);
            }
        }
    }
}
