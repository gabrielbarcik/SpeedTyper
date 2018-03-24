package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import com.dineshkrish.*;

public class test {
	
	public static AtomicInteger getMaxPoints() {

        // The name of the file to open.
        String fileName = "best_score.txt";

        // This will reference one line at a time
        String line = null;
        int max_score = 0;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                max_score = Integer.parseInt(line);
            }   

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        return new AtomicInteger(max_score);
	}
	
	public static void updateBestPlayer(String newBestPlayer) {
		// The name of the file to open.
        String fileName = "best_player.txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(newBestPlayer);

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
	}
	
	public static void updateBestScore(int newBestScore) {
		// The name of the file to open.
        String fileName = "best_score.txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(Integer.toString(newBestScore));
            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
	}
	
	
	public static String getBestPlayer() {
		// The name of the file to open.
        String fileName = "best_player.txt";

        // This will reference one line at a time
        String line = null;
        
        String best_player = "";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	best_player = line;
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        return best_player;
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		AtomicInteger maxPoints = getMaxPoints();
		String bestPlayer = getBestPlayer();
		
		String playerName = "Matheus"; // todo: pegar nome do usuario
		
		AtomicInteger points = new AtomicInteger(0);

		Scanner scanner = new Scanner(System.in);

		// You can change your file path accordingly.
		MySpellChecker spellChecker = new MySpellChecker("dictionary/my_dictionary.txt");
		
		System.out.println("Best player of the history: " + bestPlayer);
		System.out.println("Best score of all times: " + Integer.toString(maxPoints.get()) +"\n\n");

		System.out.println("Enter the Sentence with Spelling Mistake..");

		// Reading Input from User
		// todo: Esse linha precisa ser mudada dps q o Barcik pegar cada palavra
		String line = scanner.nextLine();
		
		String []mots = line.split(" ");
		Thread []threads = new Thread[mots.length];
		
		for (int i = 0; i < mots.length; i++) {
			threads[i] = new Thread(new CheckWord (maxPoints, points, mots[i], spellChecker));
			threads[i].start();
			
			threads[i].join(1000);
			/*System.out.println("Palavra : " + mots[i] + "  points: " + Integer.toString(points.get()) +
					"  maxPoints:  " + Integer.toString(maxPoints.get()));*/
			
		}		
		

		System.out.println("Before Correction : " + line);
		
		
		for (int i = 0; i < mots.length; i++) {
			threads[i].join();
		}
		
		// Method Invocation for Spelling Correction
		line = spellChecker.doCorrection(line);

		System.out.println("After Correction : " + line);
		
		
		if(points.get() > maxPoints.get()) {
			updateBestPlayer(playerName);
			updateBestScore(points.get());
			
			System.out.println("\n\n\nWooow, a new reccord! Congratz, " + playerName);
			System.out.println("maxPoints = " + Integer.toString(points.get()));
		}
		
		else {
			System.out.println("\n\n\nWell done, " + playerName + "! Your points: " + points);
			System.out.println("maxPoints: " + maxPoints);
		}

		scanner.close();

	}
}
