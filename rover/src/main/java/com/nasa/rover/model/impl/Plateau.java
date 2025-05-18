package com.nasa.rover.model.impl;

import com.nasa.rover.model.IPlateau;

/**
 * Implementation of the Mars plateau. Represents a rectangular grid with dimensions width x height.
 */
public class Plateau implements IPlateau {
  private final int width;
  private final int height;

  /**
   * Creates a new plateau with the specified dimensions.
   *
   * @param width the width of the plateau
   * @param height the height of the plateau
   * @throws IllegalArgumentException if either dimension is negative
   */
  public Plateau(int width, int height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Plateau dimensions cannot be negative");
    }
    this.width = width;
    this.height = height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public boolean isValidPosition(int x, int y) {
    return x >= 0 && x <= width && y >= 0 && y <= height;
  }
}
