package com.nasa.rover.model.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** Unit tests for the Direction enum implementation. */
public class DirectionTest {

  @Test
  public void testDirectionValues() {
    // Verify the enum values exist with the correct names
    assertEquals("N", Direction.N.name());
    assertEquals("E", Direction.E.name());
    assertEquals("S", Direction.S.name());
    assertEquals("W", Direction.W.name());
  }

  @Test
  public void testTurnLeft() {
    // Given/When/Then
    assertEquals(Direction.W, Direction.N.turnLeft());
    assertEquals(Direction.N, Direction.E.turnLeft());
    assertEquals(Direction.E, Direction.S.turnLeft());
    assertEquals(Direction.S, Direction.W.turnLeft());
  }

  @Test
  public void testTurnRight() {
    // Given/When/Then
    assertEquals(Direction.E, Direction.N.turnRight());
    assertEquals(Direction.S, Direction.E.turnRight());
    assertEquals(Direction.W, Direction.S.turnRight());
    assertEquals(Direction.N, Direction.W.turnRight());
  }

  @Test
  public void testGetValue() {
    // Given/When/Then
    assertEquals("N", Direction.N.getValue());
    assertEquals("E", Direction.E.getValue());
    assertEquals("S", Direction.S.getValue());
    assertEquals("W", Direction.W.getValue());
  }

  @Test
  public void testFromValue() {
    // Given/When/Then
    assertEquals(Direction.N, Direction.fromValue("N"));
    assertEquals(Direction.E, Direction.fromValue("E"));
    assertEquals(Direction.S, Direction.fromValue("S"));
    assertEquals(Direction.W, Direction.fromValue("W"));
  }

  @Test
  public void testFromValueWithInvalidInput() {
    // Given/When/Then
    assertThrows(IllegalArgumentException.class, () -> Direction.fromValue("X"));
  }
}
