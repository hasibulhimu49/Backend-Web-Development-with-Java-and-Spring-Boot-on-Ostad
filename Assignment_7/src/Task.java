public class Task {

    private static long counter = 100;

    private long id;
    private String message;
    private String date;

    public Task(String message, String date) {
        this.id = counter++;
        this.message = message;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + message + " | " + date;
    }
}