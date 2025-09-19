import java.util.*;

class Task {
    private static long idCounter = 100; // starting ID (auto-increment)
    private long id;
    private String message;
    private String date; // format: dd/MM/yyyy

    public Task(String message, String date) {
        this.id = idCounter++;
        this.message = message;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Task ID: " + id + " | Message: " + message + " | Date: " + date;
    }
}