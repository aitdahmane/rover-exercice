package com.nasa.rover.model.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** Unit tests for the Position class implementation. */
public class PositionTest {

  @Test
  public void testCreatePositionWithValidCoordinates() {
    // Given
    int x = 5;
    int y = 10;

    // When
    Position position = new Position(x, y);

    // Then
    assertEquals(x, position.getX(), "X coordinate should match the constructor value");
    assertEquals(y, position.getY(), "Y coordinate should match the constructor value");
  }

  @Test
  public void testSetXCoordinate() {
    // Given
    Position position = new Position(0, 0);
    int newX = 3;

    // When
    position.setX(newX);

    // Then
    assertEquals(newX, position.getX(), "X coordinate should be updated after setX");
  }

  @Test
  public void testSetYCoordinate() {
    // Given
    Position position = new Position(0, 0);
    int newY = 7;

    // When
    position.setY(newY);

    // Then
    assertEquals(newY, position.getY(), "Y coordinate should be updated after setY");
  }

  @Test
  public void testToStringMethod() {
    // Given
    int x = 3;
    int y = 8;
    Position position = new Position(x, y);
    String expected = x + " " + y;

    // When
    String result = position.toString();

    // Then
    assertEquals(expected, result, "toString should return coordinates in format 'x y'");
  }
}
