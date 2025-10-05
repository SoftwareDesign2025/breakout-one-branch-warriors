import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class HighScoreController { 
	
	//Constructor
	public HighScoreController() {
		 this.scoreFile = new File(this.filePath); 
		
		}
		
		
	
	//Class Variables
	private int highScore = 0;
	private File scoreFile;
	private String[] highScores = new String[10];
	private int scoreLimit = 10;
	private String filePath = "scoreFile.txt";
	
	
	
	
	
	//Creates the score file titled scoreFile.txt
	public void createScoreFile() {
		
		 try {
		 this.scoreFile.createNewFile();}
		 catch(Exception e) {
			
		 }
	}
	//Boolean to see if scoreFile.txt exists
	public boolean doesExist() {
		return(this.scoreFile.exists());
	}
	
	
	//Prints the scores using the "Name: point_total" format
	public void printScore() {
		try {
		Scanner scnr = new Scanner(this.scoreFile);
		if(!doesExist()) {
			createScoreFile();	
		}
		for(int i = 0; i< findCurrentNumberOfScores();i++) {
			String scoreAndName = scnr.next();
			String name = splitNameAndScore(scoreAndName, "Name");
			String score = splitNameAndScore(scoreAndName, "Score");
			System.out.println(name +": " + score);
			
		}
	
		}
		catch (FileNotFoundException fnfe) {	
		}	
	}
	
	
	//Adds an inputed name,score to the array highScores
	public void addToHighScores(String score) {
		for(int i = 0;i<this.highScores.length;i++) {
			if(this.highScores[i] == null) {
				this.highScores[i] = score;
				break;
			}
		}
		writeScores();
		
	}
	
	//Writes the array of highScores to the scoreFile.txt file
	public void writeScores() {
		if(underOrAtScoreLimit()) {
		try {
		String file = this.filePath;
		FileWriter fWriter = new FileWriter(filePath);
		
		for(int i=0;i<this.highScores.length;i++) {
			if(this.highScores[i] == null) {
				continue;
			}
			fWriter.write(this.highScores[i] + "\n");
			
		}
		fWriter.close();
		
		
		}
		catch(IOException e) {
			 
		 }}
	}
	
	//checks to see if the recorded number of scores is under the preset scoreLimit
	public boolean underOrAtScoreLimit() {
		
		if(findCurrentNumberOfScores() <= this.scoreLimit) {
			return true;
		}
		return false;
	}
	
	//Finds the current number of scores in the scoreFile.txt file
	public int findCurrentNumberOfScores() {
		Scanner scnr = new Scanner(this.filePath);
		
		int count = 0;
		while(scnr.hasNext()) {
			
			count += 1;
			scnr.next();
		}
		return count;
	}
	
	//splits the format name,score so that we can single out the name of the person or the score that person got
	public String splitNameAndScore(String nameAndScore, String requestedData) {
		String[] nameAndScoreArr = nameAndScore.split(",");
		if(requestedData == "Name") {
			return(nameAndScoreArr[0]);
		}
		return(nameAndScoreArr[1]);
		 
	}
	}
	
