package com.nasa.rover.service.impl;

import com.nasa.rover.model.IPlateau;
import com.nasa.rover.model.IRover;
import com.nasa.rover.service.IRoverControlService;

/**
 * Implementation of the service that manages rover movements by executing commands. This service
 * interprets and executes L (turn left), R (turn right) and M (move forward) commands on a rover.
 */
public class RoverControlService implements IRoverControlService {

  @Override
  public void executeCommands(IRover rover, String commands, IPlateau plateau) {
    if (commands == null || commands.isEmpty()) {
      return; // Nothing to do with empty commands
    }

    for (char command : commands.toCharArray()) {
      switch (command) {
        case 'L':
          rover.turnLeft();
          break;
        case 'R':
          rover.turnRight();
          break;
        case 'M':
          rover.moveForward(plateau);
          break;
        default:
          throw new IllegalArgumentException("Invalid command: " + command);
      }
    }
  }
}
