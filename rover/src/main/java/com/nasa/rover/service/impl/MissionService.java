package com.nasa.rover.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.nasa.rover.model.IPlateau;
import com.nasa.rover.model.IRoverCommand;
import com.nasa.rover.service.IInputFileService;
import com.nasa.rover.service.IMissionService;
import com.nasa.rover.service.IRoverControlService;

/**
 * Service that orchestrates a complete Mars Rover mission. Reads input files, processes commands,
 * and returns results.
 */
public class MissionService implements IMissionService {

  private final IInputFileService inputFileService;
  private final IRoverControlService roverControlService;

  /**
   * Creates a new MissionService with the specified dependencies.
   *
   * @param inputFileService the service for processing input files
   * @param roverControlService the service for controlling rovers
   */
  public MissionService(
      IInputFileService inputFileService, IRoverControlService roverControlService) {
    this.inputFileService = inputFileService;
    this.roverControlService = roverControlService;
  }

  @Override
  public String executeMission(String inputFilePath) throws Exception {
    try {
      // Read the plateau dimensions from the file
      IPlateau plateau = inputFileService.readPlateauFromFile(inputFilePath);

      // Read rover commands from the file
      List<IRoverCommand> roverCommands = inputFileService.readRoverCommandsFromFile(inputFilePath);

      if (roverCommands.isEmpty()) {
        throw new Exception("No rover commands found in the input file");
      }

      // Execute commands for each rover
      for (IRoverCommand command : roverCommands) {
        roverControlService.executeCommands(command.getRover(), command.getCommands(), plateau);
      }

      // Collect and format the final positions of all rovers
      String result =
          roverCommands.stream()
              .map(command -> command.getRover().getPositionReport())
              .collect(Collectors.joining("\n"));

      return result;
    } catch (Exception e) {
      // Préserver l'exception d'origine comme cause
      throw new Exception("No rover commands found in the input file", e);
    }
  }
}
