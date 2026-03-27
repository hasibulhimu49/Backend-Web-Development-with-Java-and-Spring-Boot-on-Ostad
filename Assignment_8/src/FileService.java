import java.io.*;
import java.util.*;

public class FileService {

    public List<Operation> readInputFile(String path) {
        List<Operation> operations = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(" ");

                    double num1 = Double.parseDouble(parts[0]);
                    String op = parts[1];
                    double num2 = Double.parseDouble(parts[2]);

                    Operation operation = new Operation(num1, num2, op);
                    operation.calculate();

                    operations.add(operation);

                } catch (Exception e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        }

        return operations;
    }

    public void writeToJsonFile(String path, List<Operation> operations) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {

            bw.write("[\n");

            for (int i = 0; i < operations.size(); i++) {
                bw.write(operations.get(i).toJson());

                if (i != operations.size() - 1) {
                    bw.write(",");
                }
                bw.write("\n");
            }

            bw.write("]");

        } catch (IOException e) {
            System.out.println("File write error: " + e.getMessage());
        }
    }

    public List<Operation> readJsonFile(String path) {
        List<Operation> operations = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = br.readLine()) != null) {

                line = line.trim();

                if (line.startsWith("{")) {
                    try {
                        // Extract values using simple replace
                        String numOneStr = line.split("\"numOne\":")[1].split(",")[0].trim();
                        String numTwoStr = line.split("\"numTwo\":")[1].split(",")[0].trim();
                        String opStr = line.split("\"operator\":")[1].split(",")[0].replace("\"", "").trim();
                        String resultStr = line.split("\"result\":")[1].replace("}", "").trim();

                        double num1 = Double.parseDouble(numOneStr);
                        double num2 = Double.parseDouble(numTwoStr);
                        double result = Double.parseDouble(resultStr);

                        Operation o = new Operation(num1, num2, opStr);
                        o.calculate(); // optional

                        operations.add(o);

                    } catch (Exception e) {
                        System.out.println("Skipping bad JSON line: " + line);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Read JSON error: " + e.getMessage());
        }

        return operations;
    }
}