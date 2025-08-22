import java.util.Scanner;

public class BMICalculator {

    public static double convertHeightToMeters(String heightStr) {
        heightStr = heightStr.replaceAll("\\s+", ""); // remove spaces
        int feet = 0, inches = 0;

        if (heightStr.contains("ft")) {
            String[] parts = heightStr.split("ft");
            feet = Integer.parseInt(parts[0]);
            if (parts.length > 1 && parts[1].contains("in")) {
                inches = Integer.parseInt(parts[1].replace("in", ""));
            }
        } else if (heightStr.contains("in")) {
            inches = Integer.parseInt(heightStr.replace("in", ""));
        }

        int totalInches = feet * 12 + inches;
        return totalInches * 0.0254; // convert to meters
    }

    public static void printStars(int n) {
        if (n <= 0) return;
        System.out.print("*");
        printStars(n - 1);
    }

    public static String getCategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal Weight";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }

    public static void printResult(double bmi, String category) {
        int starsCount = (int) bmi;

        printStars(starsCount);
        System.out.println();

        System.out.printf("Your BMI is: %.2f\n", bmi);
        System.out.println("Category: " + category);

        printStars(starsCount);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter weight (kg): ");
        double weight = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter height (e.g., 5ft10in, 4ft 2in, 10in): ");
        String heightStr = sc.nextLine();

        double heightMeters = convertHeightToMeters(heightStr);

        double bmi = weight / (heightMeters * heightMeters);

        String category = getCategory(bmi);

        printResult(bmi, category);

        sc.close();
    }
}
