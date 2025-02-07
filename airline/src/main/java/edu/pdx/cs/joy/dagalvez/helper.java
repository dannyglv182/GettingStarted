package edu.pdx.cs.joy.dagalvez;

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
            System.err.println("Error: The flight number is invalid.");
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
}
