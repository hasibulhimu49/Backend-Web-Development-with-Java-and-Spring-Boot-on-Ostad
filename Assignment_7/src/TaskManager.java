import java.util.*;

class TaskManager {
    private Set<Task> tasks = new HashSet<>(); // store tasks (20%)
    private Map<Long, Task> idMap = new HashMap<>(); // search by id (10%)
    private Map<String, List<Task>> dateMap = new HashMap<>(); // search by date (10%)

    // Add task (10%)
    public void addTask(String message, String date) {
        Task task = new Task(message, date);
        tasks.add(task);
        idMap.put(task.getId(), task);
        dateMap.computeIfAbsent(date, k -> new ArrayList<>()).add(task);
        System.out.println("\nTask added successfully!\n");
        showAllTasks();
    }

    // Delete task (10%)
    public void deleteTask(long id) {
        Task task = idMap.remove(id);
        if (task != null) {
            tasks.remove(task);
            dateMap.get(task.getDate()).remove(task);
            System.out.println("\nTask deleted successfully!\n");
        } else {
            System.out.println("\nTask not found!\n");
        }
        showAllTasks();
    }

    // View by id
    public void viewTaskById(long id) {
        Task task = idMap.get(id);
        if (task != null) {
            System.out.println(task);
        } else {
            System.out.println("No task found with ID: " + id);
        }
    }

    // View by date
    public void viewTasksByDate(String date) {
        List<Task> list = dateMap.get(date);
        if (list != null && !list.isEmpty()) {
            for (Task t : list) {
                System.out.println(t);
            }
        } else {
            System.out.println("No tasks found on date: " + date);
        }
    }

    // Show all tasks
    public void showAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Current Tasks:");
            for (Task t : tasks) {
                System.out.println(t);
            }
        }
    }
}