package com.nasa.rover.model;

/** Interface representing the cardinal direction of a rover. */
public interface IDirection {
  /**
   * Turns left (90 degrees counterclockwise).
   *
   * @return the new direction after rotation
   */
  IDirection turnLeft();

  /**
   * Turns right (90 degrees clockwise).
   *
   * @return the new direction after rotation
   */
  IDirection turnRight();

  /**
   * Gets the textual representation of this direction (N, E, S, W).
   *
   * @return the character representing this direction
   */
  String getValue();
}
