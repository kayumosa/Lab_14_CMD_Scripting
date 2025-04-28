import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileScan {

    public static void main(String[] args) {
        File file = null;

        // Check if command line argument is provided
        if (args.length > 0) {
            file = new File(args[0]);
            if (!file.exists()) {
                System.out.println("File not found: " + args[0]);
                return;
            }
        } else {
            // Use JFileChooser if no argument
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            } else {
                System.out.println("No file selected. Exiting...");
                return;
            }
        }

        // Now scan the file
        int charCount = 0;
        int wordCount = 0;
        int lineCount = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineCount++;
                charCount += line.length();
                wordCount += line.split("\\s+").length;
            }

            System.out.println("File: " + file.getName());
            System.out.println("Characters: " + charCount);
            System.out.println("Words: " + wordCount);
            System.out.println("Lines: " + lineCount);

        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
