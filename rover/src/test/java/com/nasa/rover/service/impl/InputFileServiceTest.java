package com.nasa.rover.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.nasa.rover.model.IPlateau;
import com.nasa.rover.model.IRoverCommand;
import com.nasa.rover.model.impl.Direction;
import com.nasa.rover.service.IInputFileService;

/** Unit tests for the InputFileService implementation. */
public class InputFileServiceTest {

  private IInputFileService inputFileService;

  @TempDir Path tempDir;

  @BeforeEach
  public void setUp() {
    inputFileService = new InputFileService();
  }

  @Test
  public void testReadPlateauFromValidFile() throws Exception {
    // Given
    String content = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM";
    File testFile = createTestFile(content);

    // When
    IPlateau plateau = inputFileService.readPlateauFromFile(testFile.getAbsolutePath());

    // Then
    assertNotNull(plateau, "Plateau should not be null");
    assertEquals(5, plateau.getWidth(), "Plateau width should match the input");
    assertEquals(5, plateau.getHeight(), "Plateau height should match the input");
  }

  @Test
  public void testReadPlateauFromEmptyFile() {
    // Given
    File testFile = createTestFile("");

    // When/Then
    Exception exception =
        assertThrows(
            Exception.class,
            () -> inputFileService.readPlateauFromFile(testFile.getAbsolutePath()),
            "Empty file should throw exception");

    assertTrue(
        exception.getMessage().contains("empty") || exception.getMessage().contains("invalid"),
        "Exception message should mention empty or invalid file");
  }

  @Test
  public void testReadPlateauFromInvalidFile() {
    // Given
    String content = "invalid content";
    File testFile = createTestFile(content);

    // When/Then
    Exception exception =
        assertThrows(
            Exception.class,
            () -> inputFileService.readPlateauFromFile(testFile.getAbsolutePath()),
            "Invalid content should throw exception");

    assertTrue(
        exception.getMessage().contains("format") || exception.getMessage().contains("invalid"),
        "Exception message should mention format issue");
  }

  @Test
  public void testReadPlateauFromNegativeDimensions() {
    // Given
    String content = "-5 5\n1 2 N\nLMLMLMLMM";
    File testFile = createTestFile(content);

    // When/Then
    Exception exception =
        assertThrows(
            Exception.class,
            () -> inputFileService.readPlateauFromFile(testFile.getAbsolutePath()),
            "Negative dimensions should throw exception");

    assertTrue(
        exception.getMessage().contains("negative") || exception.getMessage().contains("invalid"),
        "Exception message should mention invalid dimensions");
  }

  @Test
  public void testReadRoverCommandsFromValidFile() throws Exception {
    // Given
    String content = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM";
    File testFile = createTestFile(content);

    // When
    List<IRoverCommand> commands =
        inputFileService.readRoverCommandsFromFile(testFile.getAbsolutePath());

    // Then
    assertNotNull(commands, "Commands list should not be null");
    assertEquals(2, commands.size(), "Should have 2 rover commands");

    // First rover
    IRoverCommand command1 = commands.get(0);
    assertEquals(1, command1.getRover().getPosition().getX(), "First rover X position should be 1");
    assertEquals(2, command1.getRover().getPosition().getY(), "First rover Y position should be 2");
    assertEquals(
        Direction.N, command1.getRover().getDirection(), "First rover direction should be N");
    assertEquals("LMLMLMLMM", command1.getCommands(), "First rover commands should match");

    // Second rover
    IRoverCommand command2 = commands.get(1);
    assertEquals(
        3, command2.getRover().getPosition().getX(), "Second rover X position should be 3");
    assertEquals(
        3, command2.getRover().getPosition().getY(), "Second rover Y position should be 3");
    assertEquals(
        Direction.E, command2.getRover().getDirection(), "Second rover direction should be E");
    assertEquals("MMRMMRMRRM", command2.getCommands(), "Second rover commands should match");
  }

  @Test
  public void testReadRoverCommandsFromInvalidRoverPosition() {
    // Given
    String content = "5 5\n15 20 N\nLMLMLMLMM";
    File testFile = createTestFile(content);

    // When/Then
    Exception exception =
        assertThrows(
            Exception.class,
            () -> inputFileService.readRoverCommandsFromFile(testFile.getAbsolutePath()),
            "Invalid rover position should throw exception");

    assertTrue(
        exception.getMessage().contains("position") || exception.getMessage().contains("invalid"),
        "Exception message should mention position issue");
  }

  @Test
  public void testReadRoverCommandsFromInvalidDirection() {
    // Given
    String content = "5 5\n1 2 X\nLMLMLMLMM";
    File testFile = createTestFile(content);

    // When/Then
    Exception exception =
        assertThrows(
            Exception.class,
            () -> inputFileService.readRoverCommandsFromFile(testFile.getAbsolutePath()),
            "Invalid direction should throw exception");

    assertTrue(
        exception.getMessage().contains("direction") || exception.getMessage().contains("invalid"),
        "Exception message should mention direction issue");
  }

  @Test
  public void testReadRoverCommandsFromInvalidCommands() {
    // Given
    String content = "5 5\n1 2 N\nLMLX";
    File testFile = createTestFile(content);

    // When/Then
    Exception exception =
        assertThrows(
            Exception.class,
            () -> inputFileService.readRoverCommandsFromFile(testFile.getAbsolutePath()),
            "Invalid commands should throw exception");

    assertTrue(
        exception.getMessage().contains("command") || exception.getMessage().contains("invalid"),
        "Exception message should mention command issue");
  }

  @Test
  public void testReadRoverCommandsFromFileWithNonExistentRovers() {
    // Given
    String content = "5 5";
    File testFile = createTestFile(content);

    // When/Then
    Exception exception =
        assertThrows(
            Exception.class,
            () -> inputFileService.readRoverCommandsFromFile(testFile.getAbsolutePath()),
            "No rovers should throw exception");

    assertTrue(
        exception.getMessage().contains("rover") || exception.getMessage().contains("found"),
        "Exception message should mention no rovers found");
  }

  @Test
  public void testReadRoverCommandsFromNonExistentFile() {
    // Given
    String nonExistentFilePath = "non_existent_file.txt";

    // When/Then
    Exception exception =
        assertThrows(
            Exception.class,
            () -> inputFileService.readRoverCommandsFromFile(nonExistentFilePath),
            "Non-existent file should throw exception");

    assertTrue(
        exception.getMessage().contains("find") || exception.getMessage().contains("exist"),
        "Exception message should mention file not found");
  }

  /**
   * Helper method to create a test file with the given content.
   *
   * @param content the content to write to the file
   * @return the created file
   */
  private File createTestFile(String content) {
    try {
      File file = tempDir.resolve("test_input.txt").toFile();
      Files.writeString(file.toPath(), content);
      return file;
    } catch (Exception e) {
      throw new RuntimeException("Failed to create test file", e);
    }
  }
}
