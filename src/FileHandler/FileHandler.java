package FileHandler;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    // Read file and return list of lines
    public static List<String> readFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        File folder = new File("data");
        // Create 'data' folder if it doesn't exist
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File("data/" + fileName);

        // Create file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + fileName);
            }
        }

        // Read data from file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
        }

        return lines;
    }

    // Write list of lines to file (overwrite)
    public static void writeToFile(String fileName, List<String> data) {
        File folder = new File("data");
        // Create 'data' folder if it doesn't exist
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File("data/" + fileName);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }
}

