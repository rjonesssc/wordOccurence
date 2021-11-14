//Author Robert Jones
//10/13/21
//Program Name: WordOccurences
//Purpose: Count number of words in a document. Show top 20.

package Occurence;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Robert Jones
 * 
 */

public class occurence {

/**
 * program for counting number of words in a file
 * @param args main program
 
 */
	public static void main(String[] args) throws IOException {
		/**
		 *  @throws IOException javadoc says this doesn't exist when it clearly does
		 * 
		 */
		String wFile; //create variables to store filenames  
		
		Scanner console = new Scanner(System.in);		
		System.out.println("Enter File for Words:");
		wFile = console.next();  //stores the file location entered
		/**
		 * stores the file name using the Scanner class
		 * @wFile is a String to hold location file information
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(wFile))); 
		
		ArrayList<String> wList = new ArrayList<String>(); //create arraylist to hold all words
		String wLine = null; //creates variable to hold lines as the file is read
		/**
		 * @wList creates an ArrayList to hold all words from @wFile
		 * @wLine holds each line from @wFile to letter separate
		 */
		
		while((wLine = br.readLine())!= null) { //while the line is not null (end of file)
			wLine = wLine.replaceAll("\\<.*?\\>|[^a-zA-Z0-9\s]", ""); //removes all html tags and any non alphanumeric characters.
			wLine = wLine.toLowerCase();  //converts all text to lowercase to prevent dupes like The vs the.
			String [] words = wLine.split(" +"); //splits lines into words
			/**
			 * @wLine Reads in a single line from the file
			 * remove any non alpha numeric characters
			 * converts all text to lower case, to properly compare strings
			 * splits the line into words based on a single space
			 */
			
			for(int i = 0; i < words.length; i++) { 
				String wAdd = words[i]; //variable for each word
				wList.add(wAdd); //adds the word into the ArrayList
			}
		}
		
		wList.removeAll(Arrays.asList("",null)); //removes any blank items due to line breaks
		TreeSet<String> wSet = new TreeSet<>(wList); //treeset to create ordered list
		TreeMap<Integer, String> wMap = new TreeMap<Integer, String>(Collections.reverseOrder());  //map to create pair with word and counts in highest to lowest.
		/**
		 * @wMap Treemap created from set in order to hold both word and count
		 */
		for (String i : wSet) { //for each word in the set
			int wCount = Collections.frequency(wList, i); //count the number of that word in the list
			wMap.put(wCount, i); //puts the number count of words in Integer, then the word itself in String
		}
		
		System.out.println(wMap.firstEntry()); //Prints first value in map		
		int key = wMap.firstKey(); //sets first key value from the map
		
		for (int i=1; i<20; i++) { //starts at 1 instead of 0 since we already printed the first value.
			System.out.println(wMap.higherEntry(key)); //higher entry is finding the next highest key value.
			key = wMap.higherKey(key); //sets the key value to current key value used.
		}
		/**
		 * Finds the next highest key (the word count) and prints the word.
		 * Cycles through 20 times to obtain top 20 words by word count
		 */
		br.close();
		console.close();
	}
}