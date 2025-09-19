import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        System.out.println("=== Console To-Do List Application ===");
        System.out.println("Commands: a (add), v<id>, v<date>, d<id>");

        while (true) {
            System.out.print("\nEnter command: ");
            String input = sc.nextLine().trim();

            if (input.equals("a")) {
                System.out.print("Message: ");
                String message = sc.nextLine();
                System.out.print("Date (dd/MM/yyyy): ");
                String date = sc.nextLine();
                manager.addTask(message, date);

            } else if (input.startsWith("v")) {
                String arg = input.substring(1);
                if (arg.matches("\\d+")) { // view by id
                    manager.viewTaskById(Long.parseLong(arg));
                } else { // view by date
                    manager.viewTasksByDate(arg);
                }

            } else if (input.startsWith("d")) {
                String arg = input.substring(1);
                if (arg.matches("\\d+")) {
                    manager.deleteTask(Long.parseLong(arg));
                } else {
                    System.out.println("Invalid delete command. Use d<id>");
                }

            } else {
                System.out.println("Invalid command! Try again.");
            }
        }
    }
}