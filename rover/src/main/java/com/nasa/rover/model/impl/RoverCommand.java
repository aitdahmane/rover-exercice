package com.nasa.rover.model.impl;

import com.nasa.rover.model.IRover;
import com.nasa.rover.model.IRoverCommand;

/** Implementation of IRoverCommand that associates a rover with its command string. */
public class RoverCommand implements IRoverCommand {
  private final IRover rover;
  private final String commands;

  /**
   * Creates a new RoverCommand with the specified rover and commands.
   *
   * @param rover the rover to be controlled
   * @param commands the commands to execute on the rover
   */
  public RoverCommand(IRover rover, String commands) {
    this.rover = rover;
    this.commands = commands;
  }

  @Override
  public IRover getRover() {
    return rover;
  }

  @Override
  public String getCommands() {
    return commands;
  }
}
