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

            String line;

            boolean isJson = outputFile.toLowerCase().endsWith(".json");

            if (isJson) {
                writer.println("[");
            }
            boolean first = true;

            while ((line = reader.readLine()) != null) {

                System.out.println("READING: "+line);

                String[] tokens = line.split("\\|");

                if (tokens[0].equalsIgnoreCase("id")) continue;

                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double hoursWorked = Double.parseDouble(tokens[2]);
                double payRate = Double.parseDouble(tokens[3]);

                Employee emp = new Employee(id, name, hoursWorked, payRate);

                System.out.println("WRITING: " + name);

                if (isJson) {
                    if (!first) {
                        writer.println(",");
                    }
                    writer.printf("{ \"id\": %d, \"name\": \"%s\",\"grossPay\": %.2f }",
                    emp.getEmployeeId(),
                    emp.getName(),
                    emp.getGrossPay());

                first = false;
                } else {
                    writer.printf("%d|%s|%.2f%n",
                    emp.getEmployeeId(),
                    emp.getName(),
                    emp.getGrossPay());
                }
            }
            if (isJson) {
                writer.println();
                writer.println("]");
            }
            writer.close();
            reader.close();

            System.out.println("Payroll file created successfully!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

    }
}
