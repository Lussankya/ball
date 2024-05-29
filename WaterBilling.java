package org.example;

import java.util.Scanner;

public class WaterBilling {
    // Constants for water pricing
    private static final double TIER1_RATE = 5973;
    private static final double TIER2_RATE = 7052;
    private static final double TIER3_RATE = 8669;
    private static final double TIER4_RATE = 15929;

    // Constants for taxes and fees
    private static final double VAT_RATE = 0.05;
    private static final double ENV_FEE_RATE = 0.10;

    public static double calculateWaterBill(String previousReadingStr, String currentReadingStr) {
        try {
            int previousReading = Integer.parseInt(previousReadingStr);
            int currentReading = Integer.parseInt(currentReadingStr);
            return calculateWaterBill(previousReading, currentReading);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Both readings must be valid integers.");
        }
    }

    public static double calculateWaterBill(int previousReading, int currentReading) {
        if (previousReading < 0 || currentReading < 0) {
            throw new IllegalArgumentException("Reading cannot be negative.");
        }
        if (currentReading < previousReading) {
            throw new IllegalArgumentException("Invalid water usage: Water usage cannot be negative.");
        }

        int usage = currentReading - previousReading;

        if (usage > 10000) {
            throw new IllegalArgumentException("Unrealistic water usage: Please verify your input.");
        }

        double cost = 0;
        if (usage <= 10) {
            cost = usage * TIER1_RATE;
        } else if (usage <= 20) {
            cost = 10 * TIER1_RATE + (usage - 10) * TIER2_RATE;
        } else if (usage <= 30) {
            cost = 10 * TIER1_RATE + 10 * TIER2_RATE + (usage - 20) * TIER3_RATE;
        } else {
            cost = 10 * TIER1_RATE + 10 * TIER2_RATE + 10 * TIER3_RATE + (usage - 30) * TIER4_RATE;
        }

        double vat = cost * VAT_RATE;
        double envFee = cost * ENV_FEE_RATE;

        return cost + vat + envFee;
    }

    public static void main(String[] args) {
        System.out.println("Total Bill: " + calculateWaterBill("445", "452")); // Example usage
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter previous reading: ");
            String previousReading = scanner.nextLine();

            System.out.print("Enter current reading: ");
            String currentReading = scanner.nextLine();

            double totalBill = calculateWaterBill(previousReading, currentReading);
            System.out.printf("Total Bill: %.2f%n", totalBill);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
