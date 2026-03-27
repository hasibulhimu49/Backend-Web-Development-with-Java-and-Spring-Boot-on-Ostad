import java.util.List;

public class Main {
    public static void main(String[] args) {

        FileService fileService = new FileService();

        // Step 1: Read input file
        List<Operation> operations = fileService.readInputFile("input.txt");

        // Step 2: Write to JSON
        fileService.writeToJsonFile("output.json", operations);

        // Step 3: Read JSON back
        List<Operation> resultList = fileService.readJsonFile("output.json");

        // Step 4: Print output
        for (Operation op : resultList) {
            System.out.println(op);
        }
    }
}