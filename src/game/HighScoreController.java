/**
 * @author Aidan Spoerndle
 */
package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class HighScoreController {
	/*
	 * This class holds the functionality of recording, obtaining and managing the
	 * overall highest scores of the game.
	 */

	// Class Variables
	private File scoreFile; // The scoreFile.txt reference
	private int SCORELIMIT = 10; // The maximum number of scores that can be added to scoreFile
	private String[] highScores = new String[SCORELIMIT]; // An array of Strings that holds the data of the high scores
	private String filePath; // The file path of scoreFile.txt

	// Constructor
	public HighScoreController() {
		this.filePath = "score.txt";
		this.scoreFile = new File(this.filePath);
		if (!doesExist()) {
			createScoreFile();
			this.highScores[0] = "Placeholder,0";
			writeScores();
		} else {
			readScores();
		}
	}
	public HighScoreController(String game) {
		this.filePath = game;
		this.scoreFile = new File(this.filePath);
		if (!doesExist()) {
			createScoreFile();
			this.highScores[0] = "Placeholder,0";
			writeScores();
		} else {
			readScores();
		}

	}


	// Getter
	public String[] getHighScores() {

		return this.highScores;
	}

	// Input: None
	// Output: None
	// Purpose: Creates the score file titled scoreFile.txt
	public void createScoreFile() {
		try {
			this.scoreFile.createNewFile();
		} catch (Exception e) {
			System.out.println("Error caught: " + e);
		}
	}

	// Input: None
	// Output: Boolean
	// Purpose: A boolean output to see if scoreFile.txt exists
	public boolean doesExist() {
		return (this.scoreFile.exists());
	}

	// Input: none
	// Output: None
	// Purpose: Prints the scores using the "Name: point_total" format
	public void printScore() {
		try {
			Scanner scnr = new Scanner(this.scoreFile);
			if (!doesExist()) {
				createScoreFile();
			}
			for (int i = 0; i < findCurrentNumberOfScores(); i++) {
				String scoreAndName = scnr.next();
				String name = splitName(scoreAndName);
				int score = splitScore(scoreAndName);
				System.out.println(name + ": " + score);

			}

		} catch (FileNotFoundException fnfe) {
			System.out.println("Error caught: " + fnfe);
		}
	}

	// Input: int score, String name
	// Output: None
	// Purpose: Creates a String (name,score) and adds it to the array highScores
	public void addToHighScores(int score, String name) {
		for (int i = 0; i < this.highScores.length; i++) {
			if (this.highScores[i] == null) {
				this.highScores[i] = name + "," + score;
				break;
			}
		}
		if(this.highScores[SCORELIMIT-1] != null) {
			
			if(splitScore(highScores[SCORELIMIT-1]) < score) {
				highScores[SCORELIMIT-1] = name + "," + score;
			}
		}
		sortHighScores();
	}

	// Input: None
	// Output: None
	// Purpose: Writes the String array, highScores, to the scoreFile.txt file
	public void writeScores() {
		if (underOrAtSCORELIMIT()) {
			try {
				//String file = this.filePath;
				FileWriter fWriter = new FileWriter(filePath);

				for (int i = 0; i < this.highScores.length; i++) {
					if (this.highScores[i] == null) {
						continue;
					}
					fWriter.write(this.highScores[i] + "\n");

				}
				fWriter.close();
			} catch (IOException e) {
				System.out.println("Error caught: " + e);
			}
		}
	}

	// Input: None
	// Output: None
	// Purpose: reads in scores from the scoreFile.txt file and adds it to the null
	// filled Array
	public void readScores() {
		try {
			Scanner scnr = new Scanner(this.scoreFile);
			int i = 0;
			while (scnr.hasNext()) {
				highScores[i] = scnr.next();
				i++;
			}
			scnr.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Input: None
	// Output: Boolean
	// Purpose: checks to see if the recorded number of scores is under the preset
	// SCORELIMIT value
	public boolean underOrAtSCORELIMIT() {
		return findCurrentNumberOfScores() <= this.SCORELIMIT;
	}

	// Input: None
	// Output int currentNumberofScores
	// Finds the current number of scores in the scoreFile.txt file
	public int findCurrentNumberOfScores() {
		Scanner scnr = new Scanner(this.filePath);

		int currentNumberOfScores = 0;
		while (scnr.hasNext()) {

			currentNumberOfScores += 1;
			scnr.next();
		}
		return currentNumberOfScores;
	}

	// Input: String nameAndScore
	// Output: String name
	// Purpose: Takes in the name,score formatted String and returns the name of
	// that String
	public String splitName(String nameAndScore) {
		String[] nameAndScoreArr = nameAndScore.split(",");
		return (nameAndScoreArr[0]);
	}

	// Input: String nameAndScore
	// Output: int score
	// Purpose: Takes in the name,score formatted String and returns the score
	public int splitScore(String nameAndScore) {
		String[] nameAndScoreArr = nameAndScore.split(",");
		return Integer.parseInt(nameAndScoreArr[1]);

	}

	// Input: None
	// Return: None
	// Purpose: sorts the highScores array to make sure the score with the largest
	// value is at the front
	public void sortHighScores() {
		int i = 1;
		while (i != SCORELIMIT) {
			if (this.highScores[i] == null) {
				i += 1;
				continue;
			}
			if (splitScore(this.highScores[i - 1]) >= splitScore(this.highScores[i])) {
				i += 1;
			} else {
				String temp = this.highScores[i];
				this.highScores[i] = this.highScores[i - 1];
				this.highScores[i - 1] = temp;
				i = 1;
			}
		}
		writeScores();
	}
}
