package Users;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client extends User {

    private String username;
    private String email;
    private String password;

    // Constructor
    public Client() {
        askUser();
        menu();
    }

    // Getter methods
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void add() throws Exception {
        signUp();
    }

    @Override
    public void update() throws Exception {
        changeInformation();
    }

    @Override
    public void remove() throws Exception {
        // Implement the remove method if needed
    }

    @Override
    public void select() throws Exception {
        // Implement the select method if needed
    }

    @Override
    protected void writeFile() throws IOException {
        FileWriter writer = new FileWriter("C:\\Users\\Dell\\Downloads\\PL_Project\\src\\Files\\UserFiles\\" + this.email + ".txt");
        writer.write(this.username + '-' + this.email + '-' + this.password);
        writer.close();
        System.out.println("File written successfully!");
    }

    @Override
    protected void readFile() throws IOException {
        File file = new File("C:\\Users\\Dell\\Downloads\\PL_Project\\src\\Files\\UserFiles\\" + this.email + ".txt");
        Scanner fileScanner = new Scanner(file);
        String fileContent = fileScanner.nextLine();
        String[] attributes = fileContent.split("-");
        this.username = attributes[0];
        this.email = attributes[1];
        this.password = attributes[2];
        fileScanner.close();
        System.out.println("File read successfully!");
    }

    public void askUser() {
        Scanner input = new Scanner(System.in);
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("Please enter your choice:\n"
                    + "(1) for Sign up\n"
                    + "(2) for Login");
            try {
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        signUp();
                        validChoice = true;
                        break;
                    case 2:
                        login();
                        validChoice = true;
                        break;
                    default:
                        System.out.println("Unknown choice");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // Clear the input buffer
            }
        }
    }

    // Sign up method
    private void signUp() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Enter Username (should not contain numbers): ");
            this.username = input.nextLine();
            while (containsNumbers(this.username)) {
                System.out.println("Invalid username. Please enter a username without numbers.");
                this.username = input.nextLine();
            }
            System.out.println("Input Email: ");
            this.email = input.nextLine();
            System.out.println("Input Password: ");
            this.password = input.nextLine();

            File file = new File("C:\\Users\\Dell\\Downloads\\PL_Project\\src\\Files\\UserFiles\\" + this.email + ".txt");
            if (file.exists()) {
                System.out.println("Email already exists.Please try signing up again with a different email.");
                signUp();
            } else {
                setID(generateUniqueID());
                writeFile();
                System.out.println("Sign up successful!");
            }

        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            signUp();
        }
    }

    // Login method
    private void login() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Enter Email: ");
            this.email = input.nextLine();
            File file = new File("C:\\Users\\Dell\\Downloads\\PL_Project\\src\\Files\\UserFiles\\" + this.email + ".txt");
            if (file.exists()) {
                readFile();
                System.out.println("Login successful!");
            } else {
                System.out.println("Email does not exist. Please sign up.");
                signUp();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            login();
        }
    }

    // Helper method to check if a string contains numbers
    private boolean containsNumbers(String input) {
        return input.matches(".*\\d.*");
    }

    // Method to change user information
    private void changeInformation() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Enter new Username (should not contain numbers): ");
            String newUsername = input.nextLine();
            while (containsNumbers(newUsername)) {
                System.out.println("Invalid username. Please enter a username without numbers.");
                newUsername = input.nextLine();
            }
            setName(newUsername);
            System.out.println("Input new Email: ");
            String newEmail = input.nextLine();
            System.out.println("Input new Password: ");
            String newPassword = input.nextLine();

            File file = new File("C:\\Users\\Dell\\Downloads\\PL_Project\\src\\Files\\UserFiles\\" + this.email + ".txt");
            if (file.exists()) {
                if (!newEmail.equals(this.email)) {
                    File newFile = new File("C:\\Users\\Dell\\Downloads\\PL_Project\\src\\Files\\UserFiles\\" + newEmail + ".txt");
                    if (newFile.exists()) {
                        System.out.println("Email already exists. Please try again.");
                        changeInformation();
                    } else {
                        file.renameTo(newFile);
                    }
                }
                this.email = newEmail;
                this.password = newPassword;
                writeFile();
                System.out.println("Information updated successfully!");
            } else {
                System.out.println("User not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while renaming the file.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            changeInformation();
        }
    }

    // Main menu for client
    private void menu() {
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Please enter your choice:\n"
                    + "(1) for Updating Information\n"
                    + "(2) for Logging Out");
            try {
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        update();
                        break;
                    case 2:
                        exit = true;
                        System.out.println("Logged out successfully!");
                        break;
                    default:
                        System.out.println("Unknown choice");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // Clear the input buffer
            }
        }
    }
}
