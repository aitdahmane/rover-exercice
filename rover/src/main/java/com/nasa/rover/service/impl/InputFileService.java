package com.nasa.rover.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.nasa.rover.model.*;
import com.nasa.rover.model.impl.Direction;
import com.nasa.rover.model.impl.Plateau;
import com.nasa.rover.model.impl.Position;
import com.nasa.rover.model.impl.Rover;
import com.nasa.rover.model.impl.RoverCommand;
import com.nasa.rover.service.IInputFileService;

/** Service that processes Mars Rover instruction files. */
public class InputFileService implements IInputFileService {

  // Regular expressions for validation
  private static final Pattern PLATEAU_PATTERN = Pattern.compile("^-?\\d+\\s+-?\\d+$");
  private static final Pattern ROVER_POSITION_PATTERN = Pattern.compile("^\\d+\\s+\\d+\\s+.$");
  private static final Pattern ROVER_COMMAND_PATTERN = Pattern.compile("^[LRM]+$");

  @Override
  public IPlateau readPlateauFromFile(String filePath) throws Exception {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
      String line = reader.readLine();

      if (line == null || line.trim().isEmpty()) {
        throw new Exception("Input file is empty");
      }

      if (!PLATEAU_PATTERN.matcher(line).matches()) {
        throw new Exception("Invalid plateau format. Expected two integers separated by space");
      }

      String[] dimensions = line.split("\\s+");
      int width = Integer.parseInt(dimensions[0]);
      int height = Integer.parseInt(dimensions[1]);

      if (width < 0 || height < 0) {
        throw new Exception("Plateau dimensions cannot be negative");
      }

      return new Plateau(width, height);
    } catch (Exception e) {
      if (e.getMessage() != null && !e.getMessage().isEmpty()) {
        throw e;
      } else {
        throw new Exception("Error reading plateau from file: " + e.getClass().getName(), e);
      }
    }
  }

  @Override
  public List<IRoverCommand> readRoverCommandsFromFile(String filePath) throws Exception {
    List<IRoverCommand> roverCommands = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
      // Skip the first line (plateau dimensions)
      String line = reader.readLine();
      if (line == null) {
        throw new Exception("Input file is empty");
      }

      // Read plateau dimensions to validate rover positions
      String[] dimensions = line.split("\\s+");
      int maxX = Integer.parseInt(dimensions[0]);
      int maxY = Integer.parseInt(dimensions[1]);
      IPlateau plateau = new Plateau(maxX, maxY);

      // Process rover positions and commands
      String positionLine;
      String commandLine;
      boolean foundRovers = false;

      while ((positionLine = reader.readLine()) != null) {
        positionLine = positionLine.trim();
        if (positionLine.isEmpty()) {
          continue;
        }

        if (!ROVER_POSITION_PATTERN.matcher(positionLine).matches()) {
          throw new Exception("Invalid rover position format: " + positionLine);
        }

        // Read the next line for commands
        commandLine = reader.readLine();
        if (commandLine == null || commandLine.trim().isEmpty()) {
          throw new Exception("Missing commands for rover at position: " + positionLine);
        }

        commandLine = commandLine.trim();
        if (!ROVER_COMMAND_PATTERN.matcher(commandLine).matches()) {
          throw new Exception("Invalid rover command format: " + commandLine);
        }

        // Parse rover position and direction
        String[] positionParts = positionLine.split("\\s+");
        int x = Integer.parseInt(positionParts[0]);
        int y = Integer.parseInt(positionParts[1]);
        String directionValue = positionParts[2];

        // Validate position
        if (!plateau.isValidPosition(x, y)) {
          throw new Exception(
              "Invalid rover position: position ("
                  + x
                  + ","
                  + y
                  + ") is outside of plateau boundaries");
        }

        // Create rover with position and direction
        IPosition position = new Position(x, y);
        IDirection direction;
        try {
          direction = Direction.valueOf(directionValue);
        } catch (IllegalArgumentException e) {
          throw new Exception(
              "Invalid direction: direction '"
                  + directionValue
                  + "' is not valid (must be one of N, E, S, W)");
        }

        IRover rover = new Rover(position, direction);
        IRoverCommand roverCommand = new RoverCommand(rover, commandLine);
        roverCommands.add(roverCommand);
        foundRovers = true;
      }

      if (!foundRovers) {
        throw new Exception("No rover instructions found in the file");
      }

      return roverCommands;
    } catch (Exception e) {
      if (e.getMessage() != null && !e.getMessage().isEmpty()) {
        throw e;
      } else {
        throw new Exception("Error reading rover commands from file: " + e.getClass().getName(), e);
      }
    }
  }
}
