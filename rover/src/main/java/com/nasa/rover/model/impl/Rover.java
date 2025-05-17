package com.nasa.rover.model.impl;

import com.nasa.rover.model.IDirection;
import com.nasa.rover.model.IPlateau;
import com.nasa.rover.model.IPosition;
import com.nasa.rover.model.IRover;

/**
 * Implementation of a Mars Rover. Represents a rover with its position and direction on the Mars
 * plateau.
 */
public class Rover implements IRover {
  private IPosition position;
  private IDirection direction;

  /**
   * Creates a new rover with the specified position and direction.
   *
   * @param position the initial position
   * @param direction the initial direction
   */
  public Rover(IPosition position, IDirection direction) {
    this.position = position;
    this.direction = direction;
  }

  @Override
  public IPosition getPosition() {
    return position;
  }

  @Override
  public IDirection getDirection() {
    return direction;
  }

  @Override
  public void turnLeft() {
    direction = direction.turnLeft();
  }

  @Override
  public void turnRight() {
    direction = direction.turnRight();
  }

  @Override
  public boolean moveForward(IPlateau plateau) {
    int newX = position.getX();
    int newY = position.getY();

    // Calculate the new position based on the current direction
    switch (direction.getValue()) {
      case "N":
        newY++;
        break;
      case "E":
        newX++;
        break;
      case "S":
        newY--;
        break;
      case "W":
        newX--;
        break;
    }

    // Check if the new position is valid on the plateau
    if (plateau.isValidPosition(newX, newY)) {
      position.setX(newX);
      position.setY(newY);
      return true;
    }

    return false;
  }
  @Override
  public String getPositionReport() {
    return position.getX() + " " + position.getY() + " " + direction.getValue();
  }
}
