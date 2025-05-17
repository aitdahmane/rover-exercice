package com.nasa.rover.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nasa.rover.model.IPlateau;
import com.nasa.rover.model.IRover;
import com.nasa.rover.model.impl.Direction;
import com.nasa.rover.model.impl.Plateau;
import com.nasa.rover.model.impl.Position;
import com.nasa.rover.model.impl.Rover;

/** Unit tests for the RoverControlService implementation. */
public class RoverControlServiceTest {

  private IRoverControlService roverControlService;
  private IPlateau plateau;
  private IRover rover;

  @BeforeEach
  public void setUp() {
    roverControlService = new RoverControlService();
    plateau = new Plateau(5, 5);
    rover = new Rover(new Position(1, 2), Direction.N);
  }

  @Test
  public void testExecuteCommandsWithEmpty() {
    // When
    roverControlService.executeCommands(rover, "", plateau);

    // Then - position should not change
    assertEquals(1, rover.getPosition().getX());
    assertEquals(2, rover.getPosition().getY());
    assertEquals(Direction.N, rover.getDirection());
  }

  @Test
  public void testExecuteCommandsWithLeftTurn() {
    // When
    roverControlService.executeCommands(rover, "L", plateau);

    // Then
    assertEquals(1, rover.getPosition().getX());
    assertEquals(2, rover.getPosition().getY());
    assertEquals(Direction.W, rover.getDirection());
  }

  @Test
  public void testExecuteCommandsWithRightTurn() {
    // When
    roverControlService.executeCommands(rover, "R", plateau);

    // Then
    assertEquals(1, rover.getPosition().getX());
    assertEquals(2, rover.getPosition().getY());
    assertEquals(Direction.E, rover.getDirection());
  }

  @Test
  public void testExecuteCommandsWithMove() {
    // When
    roverControlService.executeCommands(rover, "M", plateau);

    // Then
    assertEquals(1, rover.getPosition().getX());
    assertEquals(3, rover.getPosition().getY());
    assertEquals(Direction.N, rover.getDirection());
  }

  @Test
  public void testExecuteCommandsWithMultipleCommands() {
    // When
    roverControlService.executeCommands(rover, "LMLMLMLMM", plateau);

    // Then
    assertEquals(1, rover.getPosition().getX());
    assertEquals(3, rover.getPosition().getY());
    assertEquals(Direction.N, rover.getDirection());
  }

  @Test
  public void testExecuteCommandsWithAnotherExample() {
    // Given
    IRover otherRover = new Rover(new Position(3, 3), Direction.E);

    // When
    roverControlService.executeCommands(otherRover, "MMRMMRMRRM", plateau);

    // Then
    assertEquals(5, otherRover.getPosition().getX());
    assertEquals(1, otherRover.getPosition().getY());
    assertEquals(Direction.E, otherRover.getDirection());
  }

  @Test
  public void testExecuteCommandsWithInvalidCommand() {
    // When/Then
    assertThrows(
        IllegalArgumentException.class,
        () -> roverControlService.executeCommands(rover, "LMX", plateau),
        "Should throw exception for invalid command character");
  }

  @Test
  public void testExecuteCommandsWithEdgeCase() {
    // Given - Rover at edge of plateau
    IRover edgeRover = new Rover(new Position(5, 5), Direction.N);

    // When
    roverControlService.executeCommands(edgeRover, "M", plateau);

    // Then - Position should not change as it would be invalid
    assertEquals(5, edgeRover.getPosition().getX());
    assertEquals(5, edgeRover.getPosition().getY());
    assertEquals(Direction.N, edgeRover.getDirection());
  }
}
