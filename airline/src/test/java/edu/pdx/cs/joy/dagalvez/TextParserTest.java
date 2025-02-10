package edu.pdx.cs.joy.dagalvez;

import edu.pdx.cs.joy.ParserException;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextParserTest {

  @Test
  void validTextFileCanBeParsed() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("valid-airline.txt");
    assertThat(resource, notNullValue());

    TextParser parser = new TextParser(new InputStreamReader(resource));
    Airline airline = parser.parse();
    assertThat(airline.getName(), equalTo("Test Airline"));
  }

  @Test
  void canParseFromFile() throws ParserException, FileNotFoundException {
    FileInputStream inputStream = new FileInputStream("src/test/resources/edu/pdx/cs/joy/dagalvez/valid-airline.txt");
    TextParser parser = new TextParser(new InputStreamReader(inputStream));
    Airline airline = parser.parse();
    assertThat(airline.getName(), equalTo("Test Airline"));
  }

  @Test
  void invalidTextFileThrowsParserException() {
    InputStream resource = getClass().getResourceAsStream("empty-airline.txt");
    assertThat(resource, notNullValue());

    TextParser parser = new TextParser(new InputStreamReader(resource));
    assertThrows(ParserException.class, parser::parse);
  }


  @Test
  void badDateGetsCaughtInHelper() {
    boolean result = helper.parsedTextIsValid("output.txt");
    assertThat(result, equalTo(true));
  }
}


