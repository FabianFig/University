package src;

import java.util.*;

public class Main {
    static int maxItems = 50;
    static String[] itemArray = new String[maxItems];
    static String[] checkedOffArray = new String[maxItems];
    static int numberOfItems = 0;

    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("\n");
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    checkOffItem();
                    break;
                case 4:
                    printList();
                    break;
                case 5:
                    printList();
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scnr.close();
    }

    public static void displayMenu() {
        System.out.println("Welcome to Grocery List Management!");
        System.out.println("1. Add item");
        System.out.println("2. Remove item");
        System.out.println("3. Check Off Item");
        System.out.println("4. Print List");
        System.out.println("5. Exit");
    }

    public static int getUserChoice() {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Please enter your choice: ");
        int choice = scnr.nextInt();
        return choice;
    }

    public static void addItem() {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter the item you would like to add: ");
        String item = scnr.nextLine();
        boolean itemFound = itemExists(item);

        for (int i = 0; i < numberOfItems; i++) {
            if (item.equals(itemArray[i])) {
                itemFound = true;
                break;
            }
        }

        if (itemFound) {
            System.out.println("\nItem already exists\n");
        } else {
            itemArray[numberOfItems] = item;
            checkedOffArray[numberOfItems] = "-";
            numberOfItems++;
            System.out.println("Item added");
        }
    }

    public static void removeItem() {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter the item you would like to remove: ");
        String item = scnr.nextLine();
        boolean itemFound = itemExists(item);

        for (int i = 0; i < numberOfItems; i++) {
            if (item.equals(itemArray[i])) {
                itemArray[i] = itemArray[numberOfItems - 1];
                checkedOffArray[i] = checkedOffArray[numberOfItems - 1];
                numberOfItems--;
                itemFound = true;
                break;
            }
        }
        
        if (itemFound == true) {
            System.out.println("\nItem removed successfully.\n");
        } else {
            System.out.println("\nItem not found.\n");
        }
    }

    public static void checkOffItem() {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter item name or number to check off: ");
        String input = scnr.nextLine().trim();
        int index = -1;

        if (input.matches("\\d+")) {
            int itemNumber = Integer.parseInt(input);
            index = itemNumber - 1;
            if (index < 0 || index >= numberOfItems) {
                System.out.println("Invalid number.");
                return;
            }
        } else {
            for (int i = 0; i < numberOfItems; i++) {
                if (input.equalsIgnoreCase(itemArray[i])) {
                    index = i;
                    break;
                }
            }
        }

        if (index == -1) {
            System.out.println("Item not found.");
            return;
        }

        checkedOffArray[index] = "x";
        System.out.println("Item checked off.");
    }

    public static void printList() {
        if (numberOfItems == 0) {
            System.out.println("The list is empty.");
            return;
        }
        for (int i = 0; i < numberOfItems; i++) {
            System.out.printf("%d. %s %s\n", i + 1, checkedOffArray[i], itemArray[i]);
        }
    }

    public static boolean itemExists(String item) {

        for (int i = 0; i < numberOfItems; i++) {
            if (item.equals(itemArray[i])) {
                return true;
            } 
        }
        return false;
    }
}