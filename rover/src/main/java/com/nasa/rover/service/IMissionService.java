package com.nasa.rover.service;

/**
 * Interface for the service that manages an entire Mars Rover mission.
 */
public interface IMissionService {

    /**
     * Executes a complete mission from an instruction file.
     *
     * @param inputFilePath path to the instruction file
     * @return the final position reports of the rovers
     * @throws Exception if an error occurs during mission execution
     */
    String executeMission(String inputFilePath) throws Exception;

    /**
     * Saves mission results to a file.
     *
     * @param outputFilePath path to the output file
     * @param results results to write to the file
     * @throws Exception if an error occurs during writing the results
     */
    void saveResults(String outputFilePath, String results) throws Exception;
}
