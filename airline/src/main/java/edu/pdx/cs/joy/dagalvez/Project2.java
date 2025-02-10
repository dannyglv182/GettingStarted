package edu.pdx.cs.joy.dagalvez;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs.joy.ParserException;

import java.io.*;
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
            if (helper.flightArgsAreValid(args)) {
                Airline airline = new Airline(args[0]);
                flight.flightNumber = Integer.parseInt(args[1]);
                flight.src = args[2];
                flight.dest = args[5];
                LocalDateTime departureDateTime = LocalDateTime.parse(args[3] + " " + args[4], formatter);
                flight.deptDateAndTime = departureDateTime;
                LocalDateTime arrivalDateTime = LocalDateTime.parse(args[6] + " " + args[7], formatter);
                flight.arriveDateAndTime = arrivalDateTime;


                // Check for print
                if (Arrays.asList(args).contains("-print")) {
                    System.out.println(flight.flightNumber + " " + flight.src + " " + flight.dest + " " + flight.deptDateAndTime);
                }

                // Check for text file option if textFile option is included
                if (Arrays.asList(args).contains("-textFile")) {
                    for (int i = 0 ; i < args.length; i++) {
                        if (args[i].equals("-textFile")) {
                            textFileFlagPos = i;
                        }
                    }
                    try {
                        // Parse the file if it exists
                        File file = new File(args[textFileFlagPos + 1]);
                        if (file.exists() && file.length() > 0) {
                            FileInputStream inputStream = new FileInputStream(args[textFileFlagPos + 1]);

                            // check if the file contains valid flight information
                            if (helper.parsedTextIsValid(args[textFileFlagPos + 1])){
                                // Parse the data and create the airline and flight if it does
                                TextParser parser = new TextParser(new InputStreamReader(inputStream));
                                Airline ParsedAirline = parser.parse();
                                System.out.println("file parsed and airline added.");
                                return;
                            }
                        } else {
                            // Dump the flight into the file
                            airline.addFlight(flight);
                            FileWriter fw = new FileWriter(args[textFileFlagPos + 1]);
                            TextDumper dumper = new TextDumper(fw);
                            dumper.dump(airline);
                        }
                    } catch (IOException e) {
                        System.err.println("Error: No file specified.");
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("Error: No file specified.");
                    } catch (ParserException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("text files option included");
                    return;
                }
                // add the flight when there is no file to read or write to
                airline.addFlight(flight);
                System.out.println("The flight has been added.");
            }
        }
    }

}