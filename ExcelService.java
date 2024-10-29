import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelService {

    // Method to read data from a CSV file
    public static List<List<String>> readCsv(String filePath) {
        List<List<String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<String> row = new ArrayList<>();
                for (String value : values) {
                    row.add(value.trim());
                }
                data.add(row);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return data;
    }

    // Method to write data to a CSV file
    public static void writeCsv(String filePath, List<List<String>> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (List<String> row : data) {
                String line = String.join(",", row);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    // Method to modify a specific cell in the CSV data
    public static void modifyCsv(String filePath, int rowIndex, int colIndex, String newValue) {
        // Read the existing data
        List<List<String>> data = readCsv(filePath);

        // Modify the specified cell if the row and column indices are valid
        if (rowIndex >= 0 && rowIndex < data.size() && colIndex >= 0 && colIndex < data.get(rowIndex).size()) {
            data.get(rowIndex).set(colIndex, newValue);
        } else {
            System.err.println("Invalid row or column index.");
            return;
        }

        // Write the modified data back to the file
        writeCsv(filePath, data);
    }

    // try this to see how the reader and writer work
    // public static void main(String[] args) {
    //     ExcelService service = new ExcelService();

    //     // Writing to a CSV file
    //     List<List<String>> newData = new ArrayList<>();
    //     newData.add(List.of("Name", "Age", "Country"));
    //     newData.add(List.of("Alice", "30", "USA"));
    //     newData.add(List.of("Bob", "25", "UK"));
    //     service.writeCsv("example.csv", newData);

    //     // Reading from a CSV file
    //     List<List<String>> data = service.readCsv("example.csv");
    //     for (List<String> row : data) {
    //         System.out.println(row);
    //     }
    // }
}
