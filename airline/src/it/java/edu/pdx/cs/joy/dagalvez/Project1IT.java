package edu.pdx.cs.joy.dagalvez;

import edu.pdx.cs.joy.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An integration test for the {@link Project1} main class.
 */
class Project1IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project1.class, args );
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
        assertThat(result.getTextWrittenToStandardOut(), containsString("The flight has been added."));
    }

}