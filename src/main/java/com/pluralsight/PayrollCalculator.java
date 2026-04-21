package com.pluralsight;

//Imports

import java.io.*;
import java.util.Scanner;

public class PayrollCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the emplyee file o process: ");
        String inputFile = scanner.nextLine();

        System.out.print("Enter the name of the payroll file to create: ");
        String outputFile = scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile));


            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {

                String[] tokens = line.split("\\|");

                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double hoursWorked = Double.parseDouble(tokens[2]);
                double payRate = Double.parseDouble(tokens[3]);
                Employee emp = new Employee(id, name, hoursWorked, payRate);
                System.out.printf("ID: %d  |  Name: %s  |  Gross Pay: $%.2f%n",
                        emp.getEmployeeId(),
                        emp.getName(),
                        emp.getGrossPay());
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

    }
}
