package edu.pdx.cs.joy.dagalvez;

import edu.pdx.cs.joy.AbstractFlight;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Flight extends AbstractFlight {
  int flightNumber = 0;
  String src;
  String dest;
  LocalDateTime deptDateAndTime;
  LocalDateTime arriveDateAndTime;

  @Override
  public int getNumber() {
    return this.flightNumber;
  }

  @Override
  public String getSource() {
    return this.src;
  }

  @Override
  public String getDepartureString() {
    return this.src;
    // throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDestination() {
    return this.dest;
    // throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getArrivalString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
