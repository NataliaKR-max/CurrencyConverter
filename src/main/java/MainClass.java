import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class MainClass {

    static private int chosenCurrency;                                      // User's chosen conversion type
    static private double chosenAmountToConvert;                            // User's chosen amount to convert
    static private double convertedAmount;                                  // The converted amount
    static private ArrayList<Double> resultsArrayList = new ArrayList<>();  // Conversions array


    public static void main(String[] args) {
        System.out.println("Welcome to currency converter");
        welcomeScreen();
    }

    //Asking the user to choose the conversion type
    static private void welcomeScreen() {
        System.out.println("Please choose an option (1/2):\n" +
                "1. Dollars to Shekels\n" +
                "2. Shekels to Dollars");

        Scanner s = new Scanner(System.in);

        try {
            chosenCurrency = s.nextInt();                                       // Getting the user's choice
            if (chosenCurrency == 1) {                                          // User chose to convert Dollars to Shekels
                System.out.println(
                        "You have chosen to convert Dollars to Shekels\n");
                chooseConversionAmount();                                       // Method for choosing the amount to convert
            } else if (chosenCurrency == 2) {                                   // User chose to convert Shekels to Dollars
                System.out.println(
                        "You have chosen to convert Shekels to Dollars\n");
                chooseConversionAmount();                                       // Method for choosing amount to convert
            } else  {
                System.out.println("Invalid Choice, please try again\n");
                welcomeScreen();                                                // Ask the user to choose again
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Choice, please try again");
            System.out.println("(Error: " + e + ")\n");
            welcomeScreen();                                                    // Ask the user to choose again
        }
    }

    //Asking the user to type in the amount to convert
    private static void chooseConversionAmount() {
        System.out.println("Please choose an amount to convert:");
        Scanner s = new Scanner(System.in);
        try {
            chosenAmountToConvert = s.nextDouble();                             // Getting the user's amount
            if (chosenAmountToConvert > 0) {                                    // Valid amount
                System.out.println("You have chosen to convert - "
                        + chosenAmountToConvert + "\n");
                currencyConversion();                                           // Call a method for converting the amount
            } else if (chosenAmountToConvert <= 0) {                            // User's ampunt is less than 0
                System.out.println("Invalid amount, please try again\n");
                chooseConversionAmount();                                       // Ask again for amount to convert
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount, please try again");
            System.out.println("(Error: " + e + ")\n");
            chooseConversionAmount();                                           // Ask again for amount to convert
        }
    }

    //Converting the user's amount
    private static void currencyConversion() {
        ILS ils = new ILS();                                                    // Creating an ILS object
        USD usd = new USD();                                                    // Creating a USD object

        switch (chosenCurrency) {
            case 1:                                                             // Convert from Dollars to Shekels
                convertedAmount = chosenAmountToConvert * usd.getValue();
                System.out.println("The converted amount is: "
                        + convertedAmount + " Shekels");
                resultsArrayList.add(convertedAmount);              // Add the current converted amount to the array
                break;
            case 2:                                                             // Convert from Shekels to Dollars
                convertedAmount = chosenAmountToConvert * ils.getValue();
                String amount = String.format("%.2f", convertedAmount);         // Setting number of trailing zeros
                convertedAmount = Double.parseDouble(amount);                   // String to double
                System.out.println("The converted amount is: "
                        + convertedAmount + " Dollars");
                resultsArrayList.add(convertedAmount);              // Add the current converted amount to the array
                break;
        }
        resultScreen();
    }

    private static void resultScreen() {
        System.out.println("Would you like to convert another amount? (Y/N)");
        Scanner s = new Scanner(System.in);
        String userChoice = s.next();
        if (userChoice.equalsIgnoreCase("y")) {
            welcomeScreen();            // User chose to convert again - calling welcomeScreen() method
        } else if (userChoice.equalsIgnoreCase("n")) {
            endScreen();                // User chose to end - calling endScreen() method
        } else {
            System.out.println("Invalid choice, please try again");
            resultScreen();
        }
    }

    private static void endScreen() {
        System.out.println("Thanks for using our currency converter\n");
        System.out.println("These are your previous conversions:");
        for (int i = 0; i < resultsArrayList.size(); i++) {                 // Loop for printing the array
            System.out.print(resultsArrayList.get(i) + " ");
        }
    }
}