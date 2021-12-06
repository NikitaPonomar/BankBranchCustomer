import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Branch> branchList = new ArrayList<Branch>();

    public static void main(String[] args) {


        boolean quit = false;
        while (!quit) {
            printMenu();
            //Checking that input is integer number
            while (!scanner.hasNextInt()) {
                System.out.println("Wrong value, Please input integer number");
                scanner.nextLine(); //to avoid endless loop
            }
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Exit");
                    quit = true;
                    break;
                case 2:
                    addNewBranch();
                    break;
                case 3:
                    addNewCustomer();
                    break;
                case 4:
                    addTransaction();
                    break;
                case 5:
                    printAllCustomers();
                    break;
                case 6:
                    printAllBranches();
                    break;
                default:
                    System.out.println("Unknown command");
                    quit = true;
                    break;
            }
        }


        scanner.close();
    }

    public static void printMenu() {
        System.out.println("----------------MENU----------------");
        System.out.println("\t 1 - Quit");
        System.out.println("\t 2 - Add new Branch");
        System.out.println("\t 3 - Add new Customer");
        System.out.println("\t 4 - Add transaction for an existing Customer");
        System.out.println("\t 5 - Print all Customers of the Branch");
        System.out.println("\t 6 - Print all Branches");
    }

    public static void addNewBranch() {
        System.out.println("Input the name of new Branch");
        String name = scanner.nextLine();
        Branch newBranch = new Branch(name, null);
        if (findBranch(name) >= 0) {
            System.out.println("Can not add Branch " + name + " , cause it is already in Branch List");
            return;
        }
        branchList.add(newBranch);
        System.out.println("Branch " + name + " was added to Branch list");
    }

    private static int findBranch(String name) {
        for (int i = 0; i < branchList.size(); i++) {
            if (branchList.get(i).getBranchName().equals(name)) return i;
        }
        return -1;
    }

    private static void addNewCustomer() {
        System.out.println("Input the name of new Customer");
        String name = scanner.nextLine();
        System.out.println("Input the amount of Customer's deposit");
        while (!scanner.hasNextDouble()) {
            System.out.println("Wrong value, Please input number of type double");
            scanner.nextLine(); //to avoid endless loop
        }
        double newDeposit = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Input the Branch name, where the Customer opens deposit");
        String branchName = scanner.nextLine();
        int position = findBranch(branchName);
        if (position >= 0) {
            if (branchList.get(position).addCustomer(name, newDeposit)) System.out.println("new Customer " +
                    name + " was added to branch " + branchName);
            else System.out.println("Customer was not added, cause " + name + " does already exist in " + branchName);
        } else {
            System.out.println("Failed, cause our bank does not have " + branchName + " branch");
            return;
        }
    }

    private static void printAllBranches() {
        System.out.println("List of Branches");
        for (int i = 0; i < branchList.size(); i++) {
            System.out.println((i+1) + ". " + branchList.get(i).getBranchName());
        }
        if (branchList.size()<=0) System.out.println("Bank does not have branches");
    }

    private static void addTransaction(){
        System.out.println("Input the name of the Customer");
        String name = scanner.nextLine();
        System.out.println("Input the new transaction");
        while (!scanner.hasNextDouble()) {
            System.out.println("Wrong value, Please input number of type double");
            scanner.nextLine(); //to avoid endless loop
        }
        double transaction = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Input the Branch name, where the Customer wants to perform transaction");
        String branchName = scanner.nextLine();
        int position = findBranch(branchName);
        if (position >= 0) {
            if (branchList.get(position).addTransaction(name,transaction)) System.out.println("Transaction was added");
            else System.out.println("Transaction was not added, cause " + name + " does not exist in " + branchName);
        } else {
            System.out.println("Failed, cause our bank does not have " + branchName + " branch");
            return;
        }
    }

    private static void printAllCustomers(){
        System.out.println("Input the name of the Branch, you want to print");
        String branchName = scanner.nextLine();
        int position = findBranch(branchName);
        if (position<0) {
            System.out.println("We do not have such Branch");
            return;
        }
        branchList.get(position).printCustomers();
    }


}
