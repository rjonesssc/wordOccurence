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


public class occurence2 {

	public static void main(String[] args) throws IOException {
		//create variables to store filenames
		String wFile;   //string to hold file location to load into wArray
		
		Scanner console = new Scanner(System.in);		
		System.out.println("Enter File for Words:");
		wFile = console.next();  //stores the file location entered
				
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(wFile))); //
		
		ArrayList<String> wList = new ArrayList<String>(); //create arraylist to hold all words
		String wLine = null; //creates variable to hold lines as the file is read
		
		while((wLine = br.readLine())!= null) { //while the line is not null (end of file)
			wLine = wLine.replaceAll("\\<.*?\\>|[^a-zA-Z0-9\s]", ""); //removes all html tags and any non alphanumeric characters.
			wLine = wLine.toLowerCase();  //converts all text to lowercase to prevent dupes like The vs the.
			String [] words = wLine.split(" +"); //splits lines into words
			for(int i = 0; i < words.length; i++) { 
				String wAdd = words[i]; //variable for each word
				wList.add(wAdd); //adds the word into the ArrayList
			}
		}
		
		wList.removeAll(Arrays.asList("",null)); //removes any blank items due to line breaks
		TreeSet<String> wSet = new TreeSet<>(wList); //treeset to create ordered list
		TreeMap<Integer, String> wMap = new TreeMap<Integer, String>(Collections.reverseOrder());  //map to create pair with word and counts in highest to lowest.
		for (String i : wSet) { //for each word in the set
			int wCount = Collections.frequency(wList, i); //count the number of that word in the list
			wMap.put(wCount, i); //puts the number count of words in Integer, then the word itself in String
		}
		/*Set<wMap.Entry<Integer, String>> entries = wMap.entrySet();
		for( wMap.Entry<Integer, String> entry : entries ){
                        if(entry.getValue().equals("with")){
                System.out.println("Key for value " + strValue + " is: " + entry.getKey());
                break;
            }
		System.out.println(values);*/
		
		
		
		System.out.println(wMap.firstEntry()); //Prints first value in map		
		int key = wMap.firstKey(); //sets first key value from the map
		
		for (int i=1; i<20; i++) { //starts at 1 instead of 0 since we already printed the first value.
			System.out.println(wMap.higherEntry(key)); //higher entry is finding the next highest key value.
			key = wMap.higherKey(key); //sets the key value to current key value used.
		}
		
		br.close();
		console.close();
	}
	
	public TreeMap<Integer, String> xMap(TreeMap<Integer, String> t) {
		return t;
	}
		
		
}


