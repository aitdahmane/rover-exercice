package com.nasa.rover.service;

import com.nasa.rover.model.IPlateau;
import com.nasa.rover.model.IRover;

/** Interface for the service that manages rover movements. */
public interface IRoverControlService {

  /**
   * Executes a series of commands on a rover.
   *
   * @param rover the rover to control
   * @param commands the command string (L, R, M)
   * @param plateau the plateau on which the rover is moving
   * @throws IllegalArgumentException if an invalid command is encountered
   */
  void executeCommands(IRover rover, String commands, IPlateau plateau);
}
