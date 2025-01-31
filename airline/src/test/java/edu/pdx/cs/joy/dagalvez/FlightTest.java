package edu.pdx.cs.joy.dagalvez;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Unit tests for the {@link Flight} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class FlightTest {

  /**
   * This unit test will need to be modified (likely deleted) as you implement
   * your project.
   */
  @Test
  void getArrivalStringNeedsToBeImplemented() {
    Flight flight = new Flight();
    assertThrows(UnsupportedOperationException.class, flight::getArrivalString);
  }

  /**
   * This unit test will need to be modified (likely deleted) as you implement
   * your project.
   */
  @Test
  void initiallyAllFlightsHaveTheSameNumber() {
    Flight flight = new Flight();
    assertThat(flight.getNumber(), equalTo(0));
  }

  @Test
  void flightSrcCanBeSet() {
    Flight flight = new Flight();
    flight.src = "PDX";
    assertThat(flight.getSource(), equalTo("PDX"));
  }

  @Test
  void destCanBeSet() {
    Flight flight = new Flight();
    flight.dest = "SFO";
    assertThat(flight.getDestination(), equalTo("SFO"));
  }

  @Test
  void forProject1ItIsOkayIfGetDepartureTimeReturnsNull() {
    Flight flight = new Flight();
    assertThat(flight.getDeparture(), is(nullValue()));
  }

  @Test
  void correctArrivalDateInputCanBeParsedAndSet() {
    Flight flight = new Flight();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    LocalDateTime dateTime = LocalDateTime.parse("12/15/2020 12:11", formatter);
    System.out.println(dateTime);
    flight.arriveDateAndTime = dateTime;
    assertThat(flight.arriveDateAndTime, is(dateTime));
  }

  @Test
  void correctDepartureDateInputCanBeParsedAndSet() {
    Flight flight = new Flight();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    LocalDateTime dateTime = LocalDateTime.parse("12/15/2020 12:11", formatter);
    System.out.println(dateTime);
    flight.deptDateAndTime = dateTime;
    assertThat(flight.deptDateAndTime, is(dateTime));
  }


  @Test
  void flightCanBeAddedWhenAllArgsArePassed() {
    Flight flight = new Flight();
    flight.flightNumber = 1;
    flight.src = "PDX";
    flight.dest = "SFO";
    assertThat(flight.flightNumber, is(1));
    assertThat(flight.src, is("PDX"));
    assertThat(flight.dest, is("SFO"));
  }
  
}
