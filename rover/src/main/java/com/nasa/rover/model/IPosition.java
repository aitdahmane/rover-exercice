package com.nasa.rover.model;

/** Interface representing a position on the Mars plateau. */
public interface IPosition {
  /**
   * Gets the X coordinate.
   *
   * @return the X coordinate
   */
  int getX();

  /**
   * Gets the Y coordinate.
   *
   * @return the Y coordinate
   */
  int getY();

  /**
   * Sets the X coordinate.
   *
   * @param x the new X coordinate
   */
  void setX(int x);

  /**
   * Sets the Y coordinate.
   *
   * @param y the new Y coordinate
   */
  void setY(int y);
}
