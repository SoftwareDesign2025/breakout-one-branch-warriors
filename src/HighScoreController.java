import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;       
public class HighScoreController {
	public HighScoreController() {
		 this.scoreFile = new File("src\\scoreFile.txt"); 
		 createScoreFile();
		 writeScores(this.highScores);
		 
		 writeScore(12);
		 readScore();
		
	}
	
	private int highScore = 0;
	private File scoreFile;
	private int[] highScores = new int[10];
	private int scoreLimit = 10;
	//name,score1
	//name,score2
	//name,score3
	
	
	
	
	
	public void createScoreFile() {
		
		 try {
		 this.scoreFile.createNewFile();}
		 catch(Exception e) {
			
		 }
	}
	
	public boolean doesExist() {
		return(this.scoreFile.exists());
	}
	
	
	
	public int readScore() {
		try {
		Scanner scnr = new Scanner(this.scoreFile);
		if(!doesExist()) {
			createScoreFile();
			return 0;
		}
		
		for(int i = 0; i< findCurrentNumberOfScores();i++) {
			
			this.highScore = Integer.parseInt(scnr.next());
			
		}
		
		return this.highScore;
	
		
		}
		catch (FileNotFoundException fnfe) {
			return -1;
		}
			
	}
	
	
	
	public void writeScore(int score) {
		if(underOrAtScoreLimit()) {
		try {
		String file = "src\\scoreFile.txt";
		FileWriter fWriter = new FileWriter(file);
		
		 
		 
		 fWriter.write(Integer.toString(score) + "\n");
		 fWriter.close();
		 }
		 catch(IOException e) {
			 
		 }
		}
		
	}
	
	
	public void writeScores(int[] highScores) {
		try {
		String file = "src\\scoreFile.txt";
		FileWriter fWriter = new FileWriter(file);
			
		for(int i=0;i<this.scoreLimit;i++) {
			if(highScores[i] == 0) {
				continue;
			}
			fWriter.write(highScores[i] + "\n");
			
		}
		fWriter.close();
		
		
		}
		catch(IOException e) {
			 
		 }
	}
	
	public boolean underOrAtScoreLimit() {
		
		if(findCurrentNumberOfScores() <= this.scoreLimit) {
			return true;
		}
		return false;
	}
	public int findCurrentNumberOfScores() {
		Scanner scnr = new Scanner("src\\scoreFile.txt");
		
		int count = 0;
		while(scnr.hasNext()) {
			
			count += 1;
			scnr.next();
		}
		return count;
	}
	
	public String splitNameAndScore(String nameAndScore) {
		String[] nameAndScoreArr = nameAndScore.split(",");
	}
}
	
