package com.nasa.rover;

import com.nasa.rover.service.IInputFileService;
import com.nasa.rover.service.IMissionService;
import com.nasa.rover.service.IRoverControlService;
import com.nasa.rover.service.impl.InputFileService;
import com.nasa.rover.service.impl.MissionService;
import com.nasa.rover.service.impl.RoverControlService;

/** Classe principale de l'application Rover Mars. Point d'entrée pour l'exécution du programme. */
public class RoverApplication {

  /**
   * Méthode principale qui est exécutée au démarrage de l'application.
   *
   * @param args Arguments de la ligne de commande. Devrait contenir le chemin vers le fichier
   *     d'entrée.
   */
  public static void main(String[] args) {

    try {
      // Vérification des arguments de la ligne de commande
      if (args.length < 1) {
        System.err.println("Usage: java -jar rover.jar <chemin-du-fichier-d-entree>");
        System.exit(1);
      }

      String inputFilePath = args[0];

      // Création des instances de services
      IInputFileService inputFileService = new InputFileService();
      IRoverControlService roverControlService = new RoverControlService();
      IMissionService missionService = new MissionService(inputFileService, roverControlService);

      // Exécution de la mission
      String result = missionService.executeMission(inputFilePath);

      // Affichage des résultats
      System.out.println(result);

    } catch (Exception e) {
      // Gestion des erreurs
      System.err.println("Erreur lors de l'exécution de la mission: " + e.getMessage());
      if (e.getCause() != null) {
        System.err.println("Cause: " + e.getCause().getMessage());
      }
      System.exit(1);
    }
  }
}
