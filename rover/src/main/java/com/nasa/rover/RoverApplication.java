// C:/Users/dell/Documents/Github/rover-exercice/rover/src/main/java/com/nasa/rover/RoverApplication.java
package com.nasa.rover;

// No service imports needed here anymore if ApplicationRunner handles them
// import com.nasa.rover.service.IInputFileService;
// ...

public class RoverApplication {

  // It's good practice to define these constants here if ApplicationRunner uses them
  public static final int EXIT_CODE_SUCCESS = 0;
  public static final int EXIT_CODE_ERROR = 1;

  public static void main(String[] args) {
    ApplicationRunner runner = new ApplicationRunner();
    int exitCode = runner.run(args);
    System.exit(exitCode);
  }
}
