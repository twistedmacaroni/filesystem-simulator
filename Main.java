package Files;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileSystem system = new FileSystem();

        while (true) {
            System.out.print(system.getPwdPath() + " $ ");
            String input = scanner.nextLine();

            String[] parts = input.split(" ");
            String command = parts[0];
            if (command.equals("exit")) {
                break;
            }

            String node = "";

            if (parts.length == 2) {
                node = parts[1];
            }

            if (command.equals("mkdir")) {
                if (parts.length < 2) {
                    System.out.println("Usage: " + command + " <name>");
                    continue;
                }

                system.mkdir(node);
            } else if (command.equals("touch")) {
                if (parts.length < 2) {
                    System.out.println("Usage: " + command + " <name>");
                    continue;
                }

                system.touch(node);
            } else if (command.equals("rm")) {
                if (parts.length < 2) {
                    System.out.println("Usage: " + command + " <name>");
                    continue;
                }

                system.rm(node);
            } else if (command.equals("cd") && parts.length == 1) {
                system.cd();
            } else if (command.equals("cd")) {
                system.cd(node);
            } else if (command.equals("ls")) {
                system.ls();
            } else if (command.equals("pwd")) {
                System.out.println(system.getPwdPath());
            } else if (command.equals("tree")) {
                system.tree();
            } else {
                System.out.println("Unknown command: " + command);
            }
        }

        scanner.close();
    }
}
