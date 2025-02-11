package edu.pdx.cs.joy.dagalvez;

import edu.pdx.cs.joy.AirlineParser;
import edu.pdx.cs.joy.ParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;

/**
 * A skeletal implementation of the <code>TextParser</code> class for Project 2.
 */
public class TextParser implements AirlineParser<Airline> {
  private final Reader reader;

  public TextParser(Reader reader) {
    this.reader = reader;
  }

  @Override
  public Airline parse() throws ParserException {
    try (
      BufferedReader br = new BufferedReader(this.reader)
    ) {

      String airlineName = br.readLine();
      String flightNumber = br.readLine();
      String src = br.readLine();
      String dest = br.readLine();
      String departureTime = br.readLine();
      String arrivalTime = br.readLine();
      if (airlineName == null) {
        throw new ParserException("Missing airline name");
      }


      if (flightNumber == null) {
        throw new ParserException("Missing flight number");
      }

      if (src == null) {
        throw new ParserException("Missing flight src");
      }

      if (dest == null) {
        throw new ParserException("Missing flight destination");
      }

      if (departureTime == null) {
        throw new ParserException("Missing departure time");
      }

      if (arrivalTime == null) {
        throw new ParserException("Missing arrival time");
      }


      return new Airline(airlineName);

    } catch (IOException e) {
      throw new ParserException("While parsing airline text", e);
    }
  }
}
