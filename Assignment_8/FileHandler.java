import java.io.*;
import java.nio.file.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FileHandler {

    private static final Gson gson = new Gson();

    // Reads input file line by line and converts to Operation objects
    public static List<Operation> readInputFile(String fileName) {
        List<Operation> operations = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                try {
                    String[] parts = line.trim().split(" ");
                    if (parts.length == 3) {
                        double numOne = Double.parseDouble(parts[0]);
                        String operator = parts[1];
                        double numTwo = Double.parseDouble(parts[2]);
                        operations.add(new Operation(numOne, numTwo, operator));
                    }
                } catch (Exception e) {
                    System.err.println("⚠️ Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Error reading file: " + e.getMessage());
        }
        return operations;
    }

    // Writes operations list to JSON file
    public static void writeToJsonFile(String fileName, List<Operation> operations) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(operations, writer);
        } catch (IOException e) {
            System.err.println("❌ Error writing to file: " + e.getMessage());
        }
    }

    // Reads JSON file and converts back to Operation objects
    public static List<Operation> readFromJsonFile(String fileName) {
        try (Reader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, new TypeToken<List<Operation>>(){}.getType());
        } catch (IOException e) {
            System.err.println("❌ Error reading JSON file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
