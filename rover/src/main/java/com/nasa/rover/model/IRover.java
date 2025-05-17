package com.nasa.rover.model;

/**
 * Interface representing a rover on Mars.
 */
public interface IRover {
    /**
     * Gets the current position of the rover.
     *
     * @return the current position
     */
    IPosition getPosition();

    /**
     * Gets the current direction of the rover.
     *
     * @return the current direction
     */
    IDirection getDirection();

    /**
     * Turns the rover left.
     */
    void turnLeft();

    /**
     * Turns the rover right.
     */
    void turnRight();

    /**
     * Moves the rover forward one grid point in its current direction.
     *
     * @param plateau the plateau on which the rover is moving
     * @return true if the movement was successful, false otherwise
     */
    boolean moveForward(IPlateau plateau);
}
