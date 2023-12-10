package Database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import InventorySystem.*;

import static InventorySystem.Product.appendProductInfo;


public class database {
    public static final String BASE_PATH = "C:\\Users\\Dell\\Downloads\\PL_Project\\src\\Files";
    public static final String PRODUCTS_FILE_PATH = BASE_PATH + "\\Products\\products.txt";

    public static void initAdmin(String adminName, String adminID) {
        File adminDir = new File(BASE_PATH + "\\Admins");
        createDirectory(adminDir);
        File adminFile = new File(adminDir, adminID + ".txt");
        createFile(adminFile);
    }

    public static void initCustomer(String customerName, String customerID) {
        File customerDir = new File(BASE_PATH + "\\Customers");
        createDirectory(customerDir);
        File customerFile = new File(customerDir, customerID + ".txt");
        createFile(customerFile);
    }

    public static void initSuppliers(String supplierName, String supplierID) {
        File supplierDir = new File(BASE_PATH + "\\Suppliers");
        createDirectory(supplierDir);
        File supplierFile = new File(supplierDir, supplierID + ".txt");
        createFile(supplierFile);
    }



    private static void createDirectory(File directory) {
        if (!directory.exists()) {
            boolean directoryCreated = directory.mkdirs();
            if (!directoryCreated) {
                System.err.println("Failed to create the directory: " + directory.getAbsolutePath());
            }
        }
    }

    public static void createFile(File file) {
        try {
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                if (!fileCreated) {
                    System.err.println("Failed to create the file: " + file.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while creating the file: " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }






}
