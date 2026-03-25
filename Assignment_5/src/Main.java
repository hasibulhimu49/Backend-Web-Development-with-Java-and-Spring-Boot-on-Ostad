import java.util.Scanner;

public class Main {

    public static Student lowestCGPA(Student[] students) {
        Student min = students[0];

        for (Student s : students) {
            if (s.getCGPA() < min.getCGPA()) {
                min = s;
            }
        }
        return min;
    }


    public static void main(String[] args) {

        System.out.print("Enter number of student: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();


        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Give information for student " + i);
            long Id = scanner.nextInt();
            scanner.nextLine();


            String fullName = scanner.nextLine();
            String bloodGroup = scanner.nextLine();
            float CGPA = scanner.nextFloat();
            scanner.nextLine();
            students[i] = new Student(Id, fullName, bloodGroup, CGPA);

        }

        String query = scanner.nextLine();

        for (Student s : students) {
            if (s.match(query)) {
                s.print();
            }
        }


        Student lowestCgpa = lowestCGPA(students);
        System.out.println("lowest cgpa : ");
        lowestCgpa.print();

    }
}