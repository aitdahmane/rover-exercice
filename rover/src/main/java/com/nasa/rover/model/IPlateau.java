package com.nasa.rover.model;

/** Interface representing a plateau on Mars. */
public interface IPlateau {
  /**
   * Gets the width of the plateau.
   *
   * @return the width of the plateau
   */
  int getWidth();

  /**
   * Gets the height of the plateau.
   *
   * @return the height of the plateau
   */
  int getHeight();

  /**
   * Checks if a position is valid on the plateau.
   *
   * @param x x coordinate
   * @param y y coordinate
   * @return true if the position is valid, false otherwise
   */
  boolean isValidPosition(int x, int y);
}
