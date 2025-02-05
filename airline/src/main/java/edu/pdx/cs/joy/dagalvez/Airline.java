package edu.pdx.cs.joy.dagalvez;

import edu.pdx.cs.joy.AbstractAirline;

import java.util.ArrayList;
import java.util.Collection;

public class Airline extends AbstractAirline<Flight> {
  private final String name;
  Flight flight;
  private Collection<Flight> flights;


  public Airline(String name) {
    this.name = name;
    this.flights = new ArrayList<>();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void addFlight(Flight flight) {
    this.flight = flight;
    this.flights.add(flight);
    // throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public Collection<Flight> getFlights() {
    return this.flights;
    // throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
