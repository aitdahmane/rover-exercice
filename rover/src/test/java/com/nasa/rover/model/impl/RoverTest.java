package com.nasa.rover.model.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nasa.rover.model.IDirection;
import com.nasa.rover.model.IPlateau;
import com.nasa.rover.model.IPosition;
import com.nasa.rover.model.IRover;

/** Unit tests for the Rover. */
public class RoverTest {

  private IPlateau plateau;
  private IPosition initialPosition;
  private IDirection initialDirection;

  @BeforeEach
  public void setUp() {
    plateau = new Plateau(5, 5);
    initialPosition = new Position(1, 2);
    initialDirection = Direction.N;
  }

  @Test
  public void testCreateRover() {
    // When
    IRover rover = new Rover(initialPosition, initialDirection);

    // Then
    assertEquals(initialPosition, rover.getPosition());
    assertEquals(initialDirection, rover.getDirection());
  }

  @Test
  public void testTurnLeft() {
    // Given
    IRover rover = new Rover(initialPosition, Direction.N);

    // When
    rover.turnLeft();

    // Then
    assertEquals(Direction.W, rover.getDirection());

    // When turn left again
    rover.turnLeft();

    // Then
    assertEquals(Direction.S, rover.getDirection());
  }

  @Test
  public void testTurnRight() {
    // Given
    IRover rover = new Rover(initialPosition, Direction.N);

    // When
    rover.turnRight();

    // Then
    assertEquals(Direction.E, rover.getDirection());

    // When turn right again
    rover.turnRight();

    // Then
    assertEquals(Direction.S, rover.getDirection());
  }

  @Test
  public void testMoveForwardNorth() {
    // Given
    IRover rover = new Rover(initialPosition, Direction.N);
    int expectedY = initialPosition.getY() + 1;

    // When
    boolean result = rover.moveForward(plateau);

    // Then
    assertTrue(result);
    assertEquals(initialPosition.getX(), rover.getPosition().getX());
    assertEquals(expectedY, rover.getPosition().getY());
  }

  @Test
  public void testMoveForwardEast() {
    // Given
    IRover rover = new Rover(initialPosition, Direction.E);
    int expectedX = initialPosition.getX() + 1;

    // When
    boolean result = rover.moveForward(plateau);

    // Then
    assertTrue(result);
    assertEquals(expectedX, rover.getPosition().getX());
    assertEquals(initialPosition.getY(), rover.getPosition().getY());
  }

  @Test
  public void testMoveForwardSouth() {
    // Given
    IRover rover = new Rover(initialPosition, Direction.S);
    int expectedY = initialPosition.getY() - 1;

    // When
    boolean result = rover.moveForward(plateau);

    // Then
    assertTrue(result);
    assertEquals(initialPosition.getX(), rover.getPosition().getX());
    assertEquals(expectedY, rover.getPosition().getY());
  }

  @Test
  public void testMoveForwardWest() {
    // Given
    IRover rover = new Rover(initialPosition, Direction.W);
    int expectedX = initialPosition.getX() - 1;

    // When
    boolean result = rover.moveForward(plateau);

    // Then
    assertTrue(result);
    assertEquals(expectedX, rover.getPosition().getX());
    assertEquals(initialPosition.getY(), rover.getPosition().getY());
  }

  @Test
  public void testMoveForwardOutOfBounds() {
    // Given - Rover at edge of plateau
    IPosition edgePosition = new Position(5, 5);
    IRover rover = new Rover(edgePosition, Direction.N);

    // When - Try to move beyond the edge
    boolean result = rover.moveForward(plateau);

    // Then - Move should fail and position should remain unchanged
    assertFalse(result);
    assertEquals(edgePosition.getX(), rover.getPosition().getX());
    assertEquals(edgePosition.getY(), rover.getPosition().getY());
  }

  @Test
  public void testGetPositionReport() {
    // Given
    IRover rover = new Rover(initialPosition, Direction.N);

    // When
    String report = rover.getPositionReport();

    // Then
    assertEquals("1 2 N", report);
  }
}
