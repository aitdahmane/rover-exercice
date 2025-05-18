package com.nasa.rover.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nasa.rover.model.IPlateau;
import com.nasa.rover.model.IRover;
import com.nasa.rover.model.IRoverCommand;
import com.nasa.rover.model.impl.Direction;
import com.nasa.rover.model.impl.Plateau;
import com.nasa.rover.model.impl.Position;
import com.nasa.rover.model.impl.Rover;
import com.nasa.rover.model.impl.RoverCommand;
import com.nasa.rover.service.IInputFileService;
import com.nasa.rover.service.IMissionService;
import com.nasa.rover.service.IRoverControlService;

/** Unit tests for the MissionService implementation. */
public class MissionServiceTest {

  @Mock private IInputFileService inputFileService;

  @Mock private IRoverControlService roverControlService;

  private IMissionService missionService;

  @TempDir Path tempDir;

  private IPlateau plateau;
  private List<IRoverCommand> roverCommands;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);

    // Creating mocked dependencies
    plateau = new Plateau(5, 5);

    IRover rover1 = new Rover(new Position(1, 3), Direction.N);
    IRover rover2 = new Rover(new Position(5, 1), Direction.E);

    IRoverCommand command1 = new RoverCommand(rover1, "LMLMLMLMM");
    IRoverCommand command2 = new RoverCommand(rover2, "MMRMMRMRRM");

    roverCommands = Arrays.asList(command1, command2);

    // Creating the service to test with mocked dependencies
    missionService = new MissionService(inputFileService, roverControlService);
  }

  @Test
  public void testExecuteMission() throws Exception {
    // Given
    String inputFilePath = "input.txt";

    when(inputFileService.readPlateauFromFile(inputFilePath)).thenReturn(plateau);
    when(inputFileService.readRoverCommandsFromFile(inputFilePath)).thenReturn(roverCommands);

    // When
    String result = missionService.executeMission(inputFilePath);

    // Then
    verify(inputFileService).readPlateauFromFile(inputFilePath);
    verify(inputFileService).readRoverCommandsFromFile(inputFilePath);

    verify(roverControlService)
        .executeCommands(
            roverCommands.get(0).getRover(), roverCommands.get(0).getCommands(), plateau);

    verify(roverControlService)
        .executeCommands(
            roverCommands.get(1).getRover(), roverCommands.get(1).getCommands(), plateau);

    String expectedResult = "1 3 N\n5 1 E";
    assertEquals(expectedResult, result.trim());
  }

  @Test
  public void testExecuteMissionWithEmptyCommands() throws Exception {
    // Given
    String inputFilePath = "empty_commands.txt";
    List<IRoverCommand> emptyCommands = List.of();

    when(inputFileService.readPlateauFromFile(inputFilePath)).thenReturn(plateau);
    when(inputFileService.readRoverCommandsFromFile(inputFilePath)).thenReturn(emptyCommands);

    // When/Then
    Exception exception =
        assertThrows(Exception.class, () -> missionService.executeMission(inputFilePath));

    assertEquals("No rover commands found in the input file", exception.getMessage());
  }

  @Test
  public void testExecuteMissionWithFileReadError() throws Exception {
    // Given
    String inputFilePath = "non_existent.txt";
    Exception expectedException = new IOException("File not found");

    when(inputFileService.readPlateauFromFile(inputFilePath)).thenThrow(expectedException);

    // When/Then
    Exception exception =
        assertThrows(Exception.class, () -> missionService.executeMission(inputFilePath));

    assertEquals(expectedException, exception.getCause());
  }

  @Test
  public void testExecuteMissionEndToEnd() throws Exception {
    // Given
    // Create a temporary input file
    File inputFile = tempDir.resolve("input_test.txt").toFile();
    Files.writeString(inputFile.toPath(), "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM");

    // Create an actual instance of MissionService with real dependencies for end-to-end test
    IInputFileService realInputService = new com.nasa.rover.service.impl.InputFileService();
    IRoverControlService realControlService = new com.nasa.rover.service.impl.RoverControlService();
    IMissionService realMissionService = new MissionService(realInputService, realControlService);

    // When
    String result = realMissionService.executeMission(inputFile.getAbsolutePath());

    // Then
    String expectedResult = "1 3 N\n5 1 E";
    assertEquals(expectedResult, result.trim());
  }
}
