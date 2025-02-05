package edu.pdx.cs.joy.dagalvez;

import edu.pdx.cs.joy.ParserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class TextDumperTest {

  @Test
  void airlineNameIsDumpedInTextFormat() {
    String airlineName = "Test Airline";
    Airline airline = new Airline(airlineName);

    StringWriter sw = new StringWriter();
    TextDumper dumper = new TextDumper(sw);
    dumper.dump(airline);

    String text = sw.toString();
    assertThat(text, containsString(airlineName));
  }

  @Test
  void flightDataCanBeDumpedToFile() {
    String airlineName = "Test Airline 2";
    Airline airline = new Airline(airlineName);
    Flight flight = new Flight();
    flight.flightNumber = 1;
    airline.addFlight(flight);
    int flightNumber = airline.flight.flightNumber;

    try {
      FileWriter fw = new FileWriter("output.txt");
      TextDumper dumper = new TextDumper(fw);
      dumper.dump(airline);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
      String line;
      StringBuilder outputString = new StringBuilder();
      while ((line = reader.readLine()) != null) {
        outputString.append(line);
      }
      System.out.println(outputString);

    } catch (IOException e) {
      System.err.println("there was an error reading the file");
    }
    assertThat("Test Airline 2", containsString(airlineName));
  }

  @Test
  void canParseTextWrittenByTextDumper(@TempDir File tempDir) throws IOException, ParserException {
    String airlineName = "Test Airline";
    Airline airline = new Airline(airlineName);

    File textFile = new File(tempDir, "airline.txt");
    TextDumper dumper = new TextDumper(new FileWriter(textFile));
    dumper.dump(airline);

    TextParser parser = new TextParser(new FileReader(textFile));
    Airline read = parser.parse();
    assertThat(read.getName(), equalTo(airlineName));
  }
}
