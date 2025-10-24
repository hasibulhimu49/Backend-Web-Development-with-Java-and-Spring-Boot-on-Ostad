import java.util.*;

public class TextFileReader {
    public static void main(String[] args) {
        String inputFile = "input.txt";   // Input file containing operations
        String outputFile = "output.json"; // Output JSON file

        // Step 1: Read from input file
        List<Operation> operations = FileHandler.readInputFile(inputFile);

        // Step 2: Write to JSON file
        FileHandler.writeToJsonFile(outputFile, operations);

        // Step 3: Read JSON file back into objects
        List<Operation> resultOps = FileHandler.readFromJsonFile(outputFile);

        // Step 4: Display results
        for (Operation op : resultOps) {
            System.out.println(op);
        }
    }
}
