package edu.pdx.cs.joy.dagalvez;

import com.google.common.annotations.VisibleForTesting;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * The main class for the Airline Project
 */
public class Project1 {

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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

    // No arguments provided
    if (args.length < 6) {
      System.err.println("Missing command line arguments");
    }

    // all arguments provided but no options
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

      // Check for text
      if (Arrays.asList(args).contains("-textFile")) {
        System.out.println("text file option included");
      }

      // add the flight
      airline.addFlight(flight);
      System.out.println("The flight has been added.");
    }
  }

}