package Admin;

import java.io.*;

public class CategoryManagement {
    public void addCategory(String name) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Categories.txt", true))) {
            writer.append(name + "\n");
            writer.append("-----------------------\n");
            System.out.println("Category added successfully");
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }

    public void deleteCategory(String name) {
        try {
            File inputFile = new File("Categories.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().equals(name)) {
                    found = true;
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            writer.close();
            reader.close();

            if (!found) {
                System.out.println("Category not found");
                return;
            }

            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
                System.out.println("Category deleted successfully");
            } else {
                System.out.println("Failed to delete category");
            }
        } catch (IOException e) {
            System.out.println("Category does not exist");
        }
    }
}