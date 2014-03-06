//Read files
import java.io.File;
//Arraylist for storing users and hashed passwords
import java.util.ArrayList;
//Buffered reader
import java.io.BufferedReader;
//File reader
import java.io.FileReader;
//Io exception
import java.io.IOException;
//File not found
import java.io.FileNotFoundException;




/**
 * Class PasswordCrack is the password cracking main program.
 */
public class PasswordCrack {
	/**
	 * Main program.
	 */
	public static void main(String[] args) throws Exception {
		//Insure proper amount of arguments
		if (args.length != 2) {
			System.err.println("Usage: java PasswordCrack <dictionaryFile> " + 
				"<databaseFile>");
			//Were done here
			return;
		}
		
		File dictionaryFile = new File(args[0]);
		File databaseFile = new File(args[1]);
		
		//Reader for reading the input files
		BufferedReader reader;
		//Store the passwords
		ArrayList<String> passwords = new ArrayList<String>();
		
		//Read the dictionary first 
		try {
			reader = new BufferedReader
				(new FileReader(dictionaryFile));
		}
		catch(FileNotFoundException e) {
			System.err.println("File " + dictionaryFile.getName() + 
				" does not exist");
			System.exit (1);
			//End the program
			return;
		}
		
		//Go line by line and put the password into the passwords array
		String line = "";
		try {
			while ((line = reader.readLine()) !=null) {
					passwords.add(line.trim());
			}
		}
		
		//Something went wrong reading the file, tell the user and give up
		catch(IOException e) {
			System.err.println("Error reading " + dictionaryFile.getName());
			System.exit (1);
		}
		
		//Read the database next
		try {
			reader = new BufferedReader
				(new FileReader(databaseFile));
		}
		
		//File not found, tell the user and give up
		catch(FileNotFoundException e) {
			System.err.println("File " + databaseFile.getName() + 
				" does not exist");
			System.exit (1);
			//End the program
			return;
		}
		
		//Turn object
		Turn turn = new Turn();
		//Dictionary for storing the hashes
		Dictionary dictionary  = new Dictionary();
		dictionary.setCount(passwords.size());
		
		//Arraylist of user threads
		ArrayList<Thread> group2 = new ArrayList<Thread>();
		//Parse input and start thread group 2 (users)
		try {
			//The threads id (goes from 0 to size of users)
			int i = 0;
			while((line = reader.readLine()) !=null) {
				//Split into tokens at any whitespace
				String[] tokens = line.split("\\s+");
				//Create and run a new thread
				Thread t = new Thread(new Group2Thread(i, tokens[0],tokens[1], dictionary, turn));
				//Start the thread
				t.start();
				//Add the thread to the group
				group2.add(t);
				//Incriment the threads id
				i++;
				
			}
		}
		
		//Something went wrong reading the file, tell the user and give up
		catch(IOException e) {
			System.err.println("Error reading " + databaseFile.getName());
			System.exit (1);
		}
		
		//Arraylist of the hashers threads
		ArrayList<Thread> group1 = new ArrayList<Thread>();
		for (String password:passwords) {
			Thread t = new Thread(new Group1Thread(password, dictionary));
			t.start();
			group1.add(t);
		}
		
		//Join all the threads, no further actions may be done in this thread per requirements
		for (Thread t: group2) {
			t.join();
		}
		
		for (Thread t: group1) {
			t.join();
		}
		

	}
}
