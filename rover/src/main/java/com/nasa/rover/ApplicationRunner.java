package com.nasa.rover;

import com.nasa.rover.service.IInputFileService;
import com.nasa.rover.service.IMissionService;
import com.nasa.rover.service.IRoverControlService;
import com.nasa.rover.service.impl.InputFileService;
import com.nasa.rover.service.impl.MissionService;
import com.nasa.rover.service.impl.RoverControlService;

public class ApplicationRunner {

  public int run(String[] args) {
    try {
      if (args.length < 1) {
        System.err.println("Usage: java -jar rover.jar <chemin-du-fichier-d-entree>");
        return RoverApplication.EXIT_CODE_ERROR;
      }

      String inputFilePath = args[0];

      IInputFileService inputFileService = new InputFileService();
      IRoverControlService roverControlService = new RoverControlService();
      IMissionService missionService = new MissionService(inputFileService, roverControlService);

      String result = missionService.executeMission(inputFilePath);
      System.out.println(result);
      return RoverApplication.EXIT_CODE_SUCCESS;

    } catch (Exception e) {
      System.err.println("Erreur lors de l'exécution de la mission: " + e.getMessage());
      if (e.getCause() != null) {
        System.err.println("Cause: " + e.getCause().getMessage());
      }
      return RoverApplication.EXIT_CODE_ERROR;
    }
  }
}
