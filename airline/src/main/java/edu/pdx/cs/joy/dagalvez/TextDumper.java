package edu.pdx.cs.joy.dagalvez;

import edu.pdx.cs.joy.AirlineDumper;

import java.io.PrintWriter;
import java.io.Writer;
import java.io.FileWriter;

/**
 * A skeletal implementation of the <code>TextDumper</code> class for Project 2.
 */
public class TextDumper implements AirlineDumper<Airline> {
  private final Writer writer;

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  @Override
  public void dump(Airline airline) {

    try (
      PrintWriter pw = new PrintWriter(this.writer)
      ) {
      pw.println(airline.getName());
      for (Flight flight : airline.getFlights()) {
        pw.println(flight.flightNumber);
        pw.println(flight.src);
        pw.println((flight.dest));
        pw.println(flight.deptDateAndTime.toString());
        pw.println(flight.arriveDateAndTime.toString());
      }
      pw.flush();
    }
  }
}
