package InventorySystem;
import Database.database;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.atomic.AtomicInteger;

import static Database.database.PRODUCTS_FILE_PATH;

public class Product {

    private static final AtomicInteger nextProductId = new AtomicInteger(1);


    private static void addProduct(String productName, String productID, String category, double price, String productionDate, String expirationDate, int quantity) {
        File productFile = new File(PRODUCTS_FILE_PATH);
        database.createFile(productFile);
        appendProductInfo(productFile, productName, category, price, productionDate, expirationDate, quantity);
    }

    public static void appendProductInfo(File file, String productName, String category, double price,
                                         String productionDate, String expirationDate, int quantity) {
        String productID = generateUniqueProductID();
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(productName + " - ");
            writer.write(productID + " - ");
            writer.write(category + " - ");
            writer.write(price + " - ");
            writer.write(productionDate + " - ");
            writer.write(expirationDate + " - ");
            writer.write(quantity + "\n");
        } catch (IOException e) {
            System.err.println("An error occurred while writing product info to file: " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }

    private static String generateUniqueProductID() {
        return String.valueOf(nextProductId.getAndIncrement());
    }

    public static void deleteProduct(File file, String productID) {
        try {
            File tempFile = new File(file.getAbsolutePath() + ".temp");
            Files.lines(file.toPath())
                    .filter(line -> !line.contains("Product ID: " + productID))
                    .forEach(line -> {
                        try (FileWriter writer = new FileWriter(tempFile, true)) {
                            writer.write(line + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            Files.move(tempFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("An error occurred while deleting the product with ID " + productID);
            e.printStackTrace();
        }
    }

    public static void updateProduct(File file, String productID, String newProductName, String newCategory,
                                     double newPrice, String newProductionDate, String newExpirationDate, int newQuantity) {
        try {
            File tempFile = new File(file.getAbsolutePath() + ".temp");
            Files.lines(file.toPath())
                    .map(line -> {
                        if (line.contains("Product ID: " + productID)) {
                            return "Product Name: " + newProductName + " - " +
                                    "Product ID: " + productID + " - " +
                                    "Category: " + newCategory + " - " +
                                    "Price: " + newPrice + " - " +
                                    "Production Date: " + newProductionDate + " - " +
                                    "Expiration Date: " + newExpirationDate + " - " +
                                    "Quantity: " + newQuantity + "\n\n";
                        } else {
                            return line;
                        }
                    })
                    .forEach(line -> {
                        try (FileWriter writer = new FileWriter(tempFile, true)) {
                            writer.write(line + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            Files.move(tempFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("An error occurred while updating the product with ID " + productID);
            e.printStackTrace();
        }
    }

    public static void searchProductByID(File file, String productID) {
        try {
            Files.lines(file.toPath())
                    .filter(line -> line.contains("Product ID: " + productID))
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("An error occurred while searching for the product with ID " + productID);
            e.printStackTrace();
        }
    }
    public boolean checklogincustomer(String email , String password)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("login.txt")))  {
            String line;
            while ((line =reader.readLine()) != null)
            {
                String [] parts = line.split(":");
                if (parts.length ==2)
                {
                    String StoredEmail = parts[0].trim();
                    String StoredPass = parts[1].trim();
                    if(StoredEmail.equals(email) && StoredPass.equals(password))
                        return true;
                }
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public void add(String eamil,String pass,  String Filename)
    {
        try {
            FileWriter login = new FileWriter("login.txt" , true);
            PrintWriter loginwriter = new PrintWriter(login);
            loginwriter.print(eamil);
            loginwriter.println(":" + pass);
            loginwriter.close();

            FileWriter fileWriter = new FileWriter(Filename, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("email:" + eamil);
            printWriter.println("pass:" + pass);
            printWriter.close();
            System.out.println(" Data added succ.");

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}


