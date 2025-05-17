package com.nasa.rover.model.impl;

import com.nasa.rover.model.IPosition;

/** Represents a position on the Mars plateau with x, y coordinates. */
public class Position implements IPosition {
  private int x;
  private int y;

  /**
   * Creates a new position with the specified coordinates.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   */
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public void setX(int x) {
    this.x = x;
  }

  @Override
  public void setY(int y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return x + " " + y;
  }

  @Override
  public int hashCode() {
    return 31 * x + y;
  }
}
