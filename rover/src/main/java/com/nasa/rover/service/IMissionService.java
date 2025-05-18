package com.nasa.rover.service;

/** Interface for the service that manages an entire Mars Rover mission. */
public interface IMissionService {

  /**
   * Executes a complete mission from an instruction file.
   *
   * @param inputFilePath path to the instruction file
   * @return the final position reports of the rovers
   * @throws Exception if an error occurs during mission execution
   */
  String executeMission(String inputFilePath) throws Exception;
}
