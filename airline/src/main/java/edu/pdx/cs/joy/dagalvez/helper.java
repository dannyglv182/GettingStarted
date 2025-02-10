package edu.pdx.cs.joy.dagalvez;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class helper {
    public static boolean flightArgsAreValid(String[] args) {
        boolean flightdataPasses = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

        // Check the flight number
        try {
            int flightNumber = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            flightdataPasses = false;
            System.err.println("Error: The flight number you entered is invalid.");
            // throw new RuntimeException(e);
        }

        // check the departure date
        try {
            LocalDateTime departureDateTime = LocalDateTime.parse(args[3] + " " + args[4], formatter);
        } catch (Exception e) {
            flightdataPasses = false;
            System.err.println("Error: The date you entered is invalid.");
        }

        // Check the arrival date and time
        try {
            LocalDateTime arrivalDateTime = LocalDateTime.parse(args[6] + " " + args[7], formatter);
        } catch (Exception e) {
            flightdataPasses = false;
            System.err.println("Error: The arrival date you entered is invalid.");
        }
        return flightdataPasses;
    }
    /**
     * Validates the departure and arrival date and time passed as string arguments.
     * This method attempts to parse the provided date and time strings (in the format MM/dd/yyyy HH:mm)
     * from the given arguments. If the parsing of either the departure or arrival date and time fails,
     * the method prints an error message Otherwise, it returns true.
     * @param args an array of Strings where args[4] contains the departure date and time.
     * and args[5] contains the arrival date and time.
     * @return boolean true if both dates are successfully parsed, false if any parsing fails.
     */
    public static boolean parsedTextIsValid(String filePath) {
        final Reader reader;
        boolean parsedDataPasses = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String airlineName = br.readLine();
            String flightNumber = br.readLine();
            String src = br.readLine();
            String dest = br.readLine();
            String departureTime = br.readLine();
            String arrivalTime = br.readLine();
            System.out.println(departureTime);

            // Check three letter flight codes
            if (src.length()  > 3 || dest.length() >3) {
                parsedDataPasses = false;
                System.err.println("Error: The flight code in the txt file is invalid.");
            }

            // Check dates
            try {
                LocalDateTime departureDateTime = LocalDateTime.parse(departureTime, formatter);
            } catch (Exception e) {
                parsedDataPasses = false;
                System.err.println("Error: The departure date in the txt file is invalid.");
            }

            try {
                LocalDateTime arrivalDateTime = LocalDateTime.parse(arrivalTime, formatter);
            } catch (Exception e) {
                parsedDataPasses = false;
                System.err.println("Error: The arrival date in the txt file is invalid.");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: The file " + filePath + " does not exist.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The data was parsed successfully.");
        return parsedDataPasses;
    }
}
