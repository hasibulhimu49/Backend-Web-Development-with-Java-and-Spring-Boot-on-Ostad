package student.info;

public final class Student {
    private final long id;
    private final String name;
    private final String bloodGroup;
    private final float cgpa;

    public Student(long id, String name, String bloodGroup, float cgpa) {
        this.id = id;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.cgpa = cgpa;
    }

    // Getters
    public long getId() { return id; }
    public String getName() { return name; }
    public String getBloodGroup() { return bloodGroup; }
    public float getCgpa() { return cgpa; }

    // Print student info
    public void print() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Blood Group: " + bloodGroup);
        System.out.println("CGPA: " + cgpa);
    }

    // Check match against query
    public boolean matches(String query) {
        // Extract last name (if available)
        String[] parts = name.split(" ");
        String lastName = parts[parts.length - 1];

        return lastName.equalsIgnoreCase(query) || bloodGroup.equalsIgnoreCase(query);
    }
}
