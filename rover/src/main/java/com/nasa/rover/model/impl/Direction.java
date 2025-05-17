package com.nasa.rover.model.impl;

import com.nasa.rover.model.IDirection;

/**
 * Enum representing the cardinal directions that a rover can face (N, E, S, W). Implements the
 * IDirection interface and provides direction-related functionality.
 */
public enum Direction implements IDirection {
  N, // North
  E, // East
  S, // South
  W; // West

  @Override
  public IDirection turnLeft() {
    return switch (this) {
      case N -> W;
      case E -> N;
      case S -> E;
      case W -> S;
    };
  }

  @Override
  public IDirection turnRight() {
    return switch (this) {
      case N -> E;
      case E -> S;
      case S -> W;
      case W -> N;
    };
  }

  @Override
  public String getValue() {
    return this.name();
  }

  /**
   * Returns the Direction enum corresponding to the given string value.
   *
   * @param value the string representation of the direction (N, E, S, W)
   * @return the corresponding Direction enum value
   * @throws IllegalArgumentException if the value doesn't match any direction
   */
  public static Direction fromValue(String value) {
    for (Direction direction : values()) {
      if (direction.name().equals(value)) {
        return direction;
      }
    }
    throw new IllegalArgumentException("Invalid direction: " + value);
  }
}
