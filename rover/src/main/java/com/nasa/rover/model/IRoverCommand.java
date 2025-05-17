package com.nasa.rover.model;

/** Represents an association between a rover and the commands intended for it. */
public interface IRoverCommand {
  /**
   * Gets the rover associated with these commands.
   *
   * @return the rover
   */
  IRover getRover();

  /**
   * Gets the commands to be executed on the rover.
   *
   * @return the command string
   */
  String getCommands();
}
