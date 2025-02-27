package edu.pdx.cs.joy.dagalvez;

import edu.pdx.cs.joy.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * An integration test for the {@link Project2} main class.
 */
class Project2IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project2.class, args );
    }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
  void testNoCommandLineArguments() {
    MainMethodResult result = invokeMain();
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
  }

    @Test
    void testWithSuccessFullArguments() {
        // Simulating passing a single command line argument
        String[] args = {"Alaska", "1", "PDX", "12/15/2020", "12:11", "SFO", "12/15/2020", "12:12"};

        // Call main with the simulated arguments
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);

        // Check the expected output
        assertThat(result.getTextWrittenToStandardOut(), containsString("The flight has been added."));
    }

    @Test
    void testWithSuccessFullArgumentsANdPrint() {
        // Simulating passing a single command line argument
        String[] args = {"Alaska", "1", "PDX", "12/15/2020", "12:11", "SFO", "12/15/2020", "12:12", "-print"};

        // Call main with the simulated arguments
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);

        // Check the expected output
        assertThat(result.getTextWrittenToStandardOut(), containsString("1 PDX"));
    }

    @Test
    void testWithSuccessFullArgumentsAndTextFileThatDoesntExistDumpsContent() {
        String[] args = {"Alaska", "1", "LAX", "12/15/2020", "12:11", "SFO", "12/15/2020", "12:12", "-textFile", "output.txt"};

        // Call main with the simulated arguments
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);

        // Check the expected output
        assertThat(result.getTextWrittenToStandardOut(), containsString("text files option included"));
    }

    @Test
    void ExistingFileGetsParsed() {
        String[] args = {"Alaska", "1", "LAX", "12/15/2020", "12:11", "SFO", "12/15/2020", "12:12", "-textFile", "existsAlready.txt"};

        // Call main with the simulated arguments
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);

        // Check the expected output
        assertThat(result.getTextWrittenToStandardOut(), containsString("The data was parsed successfully."));
    }

    @Test
    void badDepartureDateInArgumentGetsCaught() {
        // Simulating passing a single command line argument
        String[] args = {"Alaska", "1", "LAX", "12-15/2x20", "12:11", "SFO", "12/15/2020", "12:12", "-textFile", "output.txt"};

        // Call main with the simulated arguments
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);

        // Check the expected output
        assertThat(result.getTextWrittenToStandardError(), containsString("Error: The date you entered is invalid."));
    }

    @Test
    void badArrivalDateArgumentGetsCaught() {
        // Simulating passing a single command line argument
        String[] args = {"Alaska", "1", "LAX", "12/15/2020", "12:11", "SFO", "12/15-2x20", "12:12", "-textFile", "badparse.txt"};

        // Call main with the simulated arguments
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);

        // Check the expected output
        assertThat(result.getTextWrittenToStandardError(), containsString("Error: The arrival date you entered is invalid."));
    }

    @Test
    void badArrivalDateInExistingFIleGetsCaught() {
        // Simulating passing a single command line argument
        String[] args = {"Alaska", "1", "LAX", "12/15/2024", "12:11", "SFO", "12/15/2024", "12:12", "-textFile", "badparse.txt"};

        // Call main with the simulated arguments
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);

        // Check the expected output
        assertThat(result.getTextWrittenToStandardError(), containsString("Error: The departure date in the txt file is invalid."));
    }

    @Test
    void noTextFileGetsCaughtWhenAllOtherAreCorrect() {
        // Simulating passing a single command line argument
        String[] args = {"Alaska", "1", "LAX", "12/15/2020", "12:11", "SFO", "12/15/2020", "12:12", "-textFile"};

        // Call main with the simulated arguments
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);

        // Check the expected output
        assertThat(result.getTextWrittenToStandardError(), containsString("Error: No file specified."));
    }

    @Test
    void flightNumberArgumentIsNotValid() {
        // Simulating passing a single command line argument
        String[] args = {"Alaska", "X", "LAX", "12/15/2020", "12:11", "SFO", "12/15-2020", "12:12", "-textFile", "output.txt"};

        // Call main with the simulated arguments
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);

        // Check the expected output
        assertThat(result.getTextWrittenToStandardError(), containsString("Error: The flight number you entered is invalid."));
    }

}