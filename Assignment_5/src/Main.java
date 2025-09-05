import student.info.Student;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of Students: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        List<Student> students=new ArrayList<>();

        // Input students
        for (int i = 1; i <= n; i++) {
            System.out.println("---- Student " + i + " ----");

            System.out.print("Enter ID: ");
            long id = scanner.nextLong();
            scanner.nextLine();

            System.out.print("Enter Full Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Blood Group: ");
            String bloodGroup = scanner.next();

            System.out.print("Enter CGPA: ");
            float cgpa = scanner.nextFloat();
            scanner.nextLine(); // consume newline

            students.add(new Student(id, name, bloodGroup, cgpa));
        }

        // Query input
        System.out.print("\nEnter your query: ");
        String query = scanner.nextLine();

        // Print all matching students
        System.out.println("\n--- Matching Students ---");
        for (Student s : students) {
            if (s.matches(query)) {
                s.print();
                System.out.println();
            }
        }

        // Find lowest CGPA student
        Student lowest = findLowestCGPA(students);
        System.out.println("\n--- Lowest CGPA Student ---");
        lowest.print();
    }

    // Function outside Student class
    public static Student findLowestCGPA(List<Student> students) {
        Student lowest = students.get(0);
        for (Student s : students) {
            if (s.getCgpa() < lowest.getCgpa()) {
                lowest = s;
            }
        }
        return lowest;
    }
}
