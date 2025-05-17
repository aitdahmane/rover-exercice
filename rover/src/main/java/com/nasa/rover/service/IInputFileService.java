package com.nasa.rover.service;

import java.util.List;

import com.nasa.rover.model.IPlateau;
import com.nasa.rover.model.IRoverCommand;

/** Interface for the service that processes instruction files. */
public interface IInputFileService {

  /**
   * Reads and parses a plateau from a file.
   *
   * @param filePath path to the instruction file
   * @return the plateau created from the instructions
   * @throws Exception if an error occurs during file reading or parsing
   */
  IPlateau readPlateauFromFile(String filePath) throws Exception;

  /**
   * Reads rover deployment instructions from a file.
   *
   * @param filePath path to the instruction file
   * @return list of rover commands
   * @throws Exception if an error occurs during file reading or parsing
   */
  List<IRoverCommand> readRoverCommandsFromFile(String filePath) throws Exception;
}
