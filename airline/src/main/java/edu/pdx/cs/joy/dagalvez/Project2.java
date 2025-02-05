package edu.pdx.cs.joy.dagalvez;

import com.google.common.annotations.VisibleForTesting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * The main class for the Airline Project
 */
public class Project2 {

    @VisibleForTesting
    static boolean isValidDateAndTime(String dateAndTime) {
        return true;
    }

    /**
     * This class parses the arguments provided by the user and adds a flight to
     * an airline
     */
    public static void main(String[] args) {
        Flight flight = new Flight();
        int textFileFlagPos = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

        // No arguments provided
        if (args.length < 6) {
            System.err.println("Missing command line arguments");
        }

        // all necessary arguments provided
        else if (args.length > 6) {
            Airline airline = new Airline(args[0]);
            flight.flightNumber = Integer.parseInt(args[1]);
            flight.src = args[2];
            flight.dest = args[5];

            // add the departure date
            try {
                LocalDateTime departureDateTime = LocalDateTime.parse(args[3] + " " + args[4], formatter);
                flight.deptDateAndTime = departureDateTime;
            } catch (Exception e) {
                System.out.println("Error: The date you entered is invalid.");
            }

            // add the arrival date
            try {
                LocalDateTime arrivalDateTime = LocalDateTime.parse(args[6] + " " + args[7], formatter);
                flight.arriveDateAndTime = arrivalDateTime;
            } catch (Exception e) {
                System.out.println("Error: The arrival date you entered is invalid.");
            }

            // Check for print
            if (Arrays.asList(args).contains("-print")) {
                System.out.println(flight.flightNumber + " " + flight.src + " " + flight.dest + " " + flight.deptDateAndTime);
            }

            // Check for text file option
            if (Arrays.asList(args).contains("-textFile")) {
                for (int i = 0 ; i < args.length; i++) {
                    if (args[i].equals("-textFile")) {
                        textFileFlagPos = i;
                    }
                }

                System.out.println("text files option included");
                try {

                    // Parse the file
                    File file = new File(args[textFileFlagPos + 1]);
                    if (file.exists() && file.length() > 0) {
                        System.out.println("file parsed");
                    }

                    // Dump the flight
                    airline.addFlight(flight);
                    FileWriter fw = new FileWriter(args[textFileFlagPos + 1]);
                    TextDumper dumper = new TextDumper(fw);
                    dumper.dump(airline);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            // add the flight
            airline.addFlight(flight);
            System.out.println("The flight has been added.");
        }
    }

}