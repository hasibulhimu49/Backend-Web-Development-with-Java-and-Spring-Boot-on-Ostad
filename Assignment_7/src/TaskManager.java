import java.util.*;

public class TaskManager {

    Set<Task> tasks = new HashSet<>();
    Map<Long, Task> idMap = new HashMap<>();
    Map<String, List<Task>> dateMap = new HashMap<>();

    public void addTask(Task task) {
        tasks.add(task);
        idMap.put(task.getId(), task);

        dateMap.putIfAbsent(task.getDate(), new ArrayList<>());
        dateMap.get(task.getDate()).add(task);

        showAllTasks();
    }

    public void viewById(long id) {
        Task task = idMap.get(id);
        if (task != null) {
            System.out.println(task);
        } else {
            System.out.println("Task not found");
        }
    }

    public void viewByDate(String date) {
        List<Task> list = dateMap.get(date);
        if (list != null) {
            for (Task t : list) {
                System.out.println(t);
            }
        } else {
            System.out.println("No tasks for this date");
        }
    }

    public void deleteTask(long id) {
        Task task = idMap.remove(id);

        if (task != null) {
            tasks.remove(task);
            dateMap.get(task.getDate()).remove(task);

            System.out.println("Deleted successfully");
            showAllTasks();
        } else {
            System.out.println("Task not found");
        }
    }

    public void showAllTasks() {
        System.out.println("---- ALL TASKS ----");
        for (Task t : tasks) {
            System.out.println(t);
        }
    }
}