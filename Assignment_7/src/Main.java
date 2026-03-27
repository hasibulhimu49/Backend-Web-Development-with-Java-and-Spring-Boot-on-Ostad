import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine();

            if (input.equals("a")) {

                System.out.print("Message: ");
                String message = scanner.nextLine();

                System.out.print("Date (dd/MM/yyyy): ");
                String date = scanner.nextLine();

                Task task = new Task(message, date);
                manager.addTask(task);
            }

            else if (input.startsWith("v")) {

                String param = input.substring(1);

                if (param.contains("/")) {
                    manager.viewByDate(param);
                } else {
                    long id = Long.parseLong(param);
                    manager.viewById(id);
                }
            }

            else if (input.startsWith("d")) {

                long id = Long.parseLong(input.substring(1));
                manager.deleteTask(id);
            }

            else {
                System.out.println("Invalid command");
            }
        }
    }
}