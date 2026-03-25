public class Student {
    private final long Id;
    private final String fullName;
    private final String bloodGroup;
    private final float CGPA;


    public Student(long id, String fullName, String bloodGroup, float CGPA) {
        Id = id;
        this.fullName = fullName;
        this.bloodGroup = bloodGroup;
        this.CGPA = CGPA;
    }

    public long getId() {
        return Id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public float getCGPA() {
        return CGPA;
    }


    public void print() {
        System.out.println("Id=" + Id +
                ", fullName='" + fullName + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", CGPA=" + CGPA);
    }


    public boolean match(String query) {
        query = query.toLowerCase();

        String[] parts = fullName.split(" ");
        String lastName = parts[parts.length - 1].toLowerCase();
        return lastName.equalsIgnoreCase(query) || bloodGroup.equalsIgnoreCase(query);

    }
}
