package com.nasa.rover.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** Unit tests for the Plateau class implementation. */
public class PlateauTest {

  @Test
  public void testCreatePlateauWithValidDimensions() {
    // Given
    int width = 5;
    int height = 5;

    // When
    Plateau plateau = new Plateau(width, height);

    // Then
    assertEquals(width, plateau.getWidth(), "Width should match the constructor value");
    assertEquals(height, plateau.getHeight(), "Height should match the constructor value");
  }

  @Test
  public void testIsValidPositionWithValidCoordinates() {
    // Given
    Plateau plateau = new Plateau(5, 5);

    // When/Then
    assertTrue(plateau.isValidPosition(0, 0), "Origin (0,0) should be valid");
    assertTrue(plateau.isValidPosition(5, 5), "Max coordinates (5,5) should be valid");
    assertTrue(plateau.isValidPosition(3, 4), "Position within bounds should be valid");
  }

  @Test
  public void testIsValidPositionWithInvalidCoordinates() {
    // Given
    Plateau plateau = new Plateau(5, 5);

    // When/Then
    assertFalse(plateau.isValidPosition(-1, 0), "Negative X should be invalid");
    assertFalse(plateau.isValidPosition(0, -1), "Negative Y should be invalid");
    assertFalse(plateau.isValidPosition(6, 5), "X > width should be invalid");
    assertFalse(plateau.isValidPosition(5, 6), "Y > height should be invalid");
  }

  @Test
  public void testCreatePlateauWithNegativeDimensions() {
    // When/Then
    assertThrows(
        IllegalArgumentException.class,
        () -> new Plateau(-1, 5),
        "Creating plateau with negative width should throw IllegalArgumentException");

    assertThrows(
        IllegalArgumentException.class,
        () -> new Plateau(5, -1),
        "Creating plateau with negative height should throw IllegalArgumentException");
  }

}
