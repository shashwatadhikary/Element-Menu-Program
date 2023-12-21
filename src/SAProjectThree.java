// Shashwat Adhikary

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class SAProjectThree {

    public static void main(String[] args) {
        // Initialize the list of elements and the input scanner
        ArrayList<Element> elements = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            // Print the menu for the user
            System.out.println("\nSelect an option number from the following menu:");
            System.out.println(" 1 Load elements from a text file.");
            System.out.println(" 2 Display the collection sorted by symbol.");
            System.out.println(" 3 Add a chemical element to the collection.");
            System.out.println(" 4 Delete an element from the collection.");
            System.out.println(" 5 Search for an element in the collection.");
            System.out.println(" 6 Quit.");
            System.out.print("Enter your choice: ");

            // Handle user input for menu selection   
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                choice = 0;
            }

            // Process the user's choice
            switch (choice) {
                case 1:
                    // Load elements from a text file
                    System.out.print("Enter the file name: ");
                    String fileName = input.nextLine();
                    try {
                        File file = new File(fileName);
                        Scanner fileScanner = new Scanner(file);

                        while (fileScanner.hasNextLine()) {
                            String[] data = fileScanner.nextLine().split(",");
                            Element element = new Element(data[0].trim(), data[1].trim(), Integer.parseInt(data[2].trim()), Double.parseDouble(data[3].trim()));
                            elements.add(element);
                        }

                        fileScanner.close();
                        System.out.println("Elements loaded successfully.");
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid data format in file.");
                    }
                    break;

                case 2:
                    // Display the collection sorted by symbol
                    elements.sort(Comparator.comparing(Element::getSymbol));
                    System.out.printf("%-6s %-12s %-7s %-7s\n", "Symbol", "Name", "Number", "Weight");
                    System.out.println("------------------------------------");
                    for (Element element : elements) {
                        System.out.printf("%-6s %-12s %-7d %-7.3f\n", element.getSymbol(), element.getName(), element.getAtomicNumber(), element.getAtomicWeight());
                    }
                    break;

                case 3:
                    // Add a chemical element to the collection
                    System.out.print("Enter chemical symbol: ");
                    String symbol = input.next();
                    System.out.print("Enter element name: ");
                    String name = input.next();
                    System.out.print("Enter atomic number: ");
                    int atomicNumber = input.nextInt();
                    System.out.print("Enter atomic weight: ");
                    double atomicWeight = input.nextDouble();

                    Element newElement = new Element(symbol, name, atomicNumber, atomicWeight);
                    elements.add(newElement);
                    System.out.println("Element added successfully.");
                    input.nextLine();
                    break;

                case 4:
                    // Delete an element from the collection
                    System.out.print("Enter chemical symbol to delete: ");
                    String symbolToDelete = input.next();
                    boolean removed = elements.removeIf(element -> element.getSymbol().equalsIgnoreCase(symbolToDelete));

                    if (removed) {
                        System.out.println("Element deleted successfully.");
                    } else {
                        System.out.println("Element not found.");
                    }
                    input.nextLine();
                    break;

                case 5:
                    // Search element from the collection
                    System.out.print("Enter chemical symbol to search: ");
                    String symbolToSearch = input.next();
                    boolean found = false;

                    for (Element element : elements) {
                        if (element.getSymbol().equalsIgnoreCase(symbolToSearch)) {
                            System.out.printf("Symbol: %s, Name: %s, Atomic Number: %d, Atomic Weight: %.3f\n", element.getSymbol(), element.getName(), element.getAtomicNumber(), element.getAtomicWeight());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Element not found.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting the program...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    input.nextLine();
                    break;
            }
        } while (choice != 6);

        input.close();
    }
}
