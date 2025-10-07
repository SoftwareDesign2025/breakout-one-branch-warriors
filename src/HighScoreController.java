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
	 * This class holds the functionality of recording, obtaining and managing the overall highest scores of the game.
	 */
	
	
	
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
	
	//getter
	public String[] getHighScores() {
		return this.highScores;
	}
	
	
	
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
			String name = splitName(scoreAndName);
			int score = splitScore(scoreAndName);
			System.out.println(name +": " + score);
			
		}
	
		}
		catch (FileNotFoundException fnfe) {	
		}	
	}
	
	
	//Adds an inputed name,score to the array highScores
	public void addToHighScores(int score, String name) {
		for(int i = 0;i<this.highScores.length;i++) {
			if(this.highScores[i] == null) {
				this.highScores[i] = name + "," + score;
				break;
			}
		}
		if(this.highScores[this.highScores.length-1] != null) {
			
			for(int i=1;i<findCurrentNumberOfScores();i++) {
				
				if(score>splitScore(this.highScores[i-1])) {
					this.highScores[this.highScores.length - 1] = "";
					for(int j = i; j<this.highScores.length; j++) {
						this.highScores[this.highScores.length-j-1] = this.highScores[this.highScores.length-j];
					}
					this.highScores[i] = name+","+score;
				
				}
				
				
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
	public String splitName(String nameAndScore) {
		String[] nameAndScoreArr = nameAndScore.split(",");
		 
		return(nameAndScoreArr[0]);
		
		
		 
	}
	public int splitScore(String nameAndScore) {
		String[] nameAndScoreArr = nameAndScore.split(",");
		
			return Integer.parseInt(nameAndScoreArr[1]);
		
	}
	
	
	//sort high score array
	public void sortHighScores() {
		System.out.println("enter sort");
		int i = 1;
		while(i != 10) {
			if(this.highScores[i] == null) {
				i+=1;
				continue;
			}
			if(splitScore(this.highScores[i-1]) >= splitScore(this.highScores[i])) {
				
				
				i+=1;
			}
			else {
				String temp = this.highScores[i];
				this.highScores[i] = this.highScores[i-1];
				this.highScores[i-1] = temp;
				i = 1;
			}
		}
		System.out.println("leaving sort");
			writeScores();
		}
		
	}
	
