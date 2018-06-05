package hw8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellChecker implements ISpellChecker {
	
	private HashTable hT;
	
	public SpellChecker() {
		hT = new HashTable();
	}

	/** Read a dictionary from the specified reader
	 * 
	 * import java.io.FileReader
	 * 
	 * spellChecker.ReadDictionary(new FileReader(filename));
	 * 
	 * @param reader a character stream
	 */
	@Override
	public void ReadDictionary(Reader reader) {
		// Populate hash 

		BufferedReader bR = new BufferedReader( reader );
		Boolean isFileRead = false;
		String currentWord = null;
		
		while( isFileRead == false ) {
			
			try {
				currentWord = bR.readLine();
			} catch (IOException e) {}
			
			if( currentWord ==null ) {
				return;
			}
			
				hT.Insert(currentWord);
		}
		
	}		

	/** Check if the given word is properly spelled
	 * 
	 * If the given word is properly spelled return null.
	 * If the word is not properly spelled, return all variants as
	 * described by the homework write up. 
	 * 
	 * If no variants were found return an empty array.
	 * 
	 * @param word word to check
	 * @return null (word spelled correctly), array of variants (if not), or empty array (if no variants)
	 */
	@Override
	public String[] CheckWord(String word) {
		word = word.toLowerCase();
		Boolean isCorrect;
		Boolean modWordCorrect;
		int arrayLength = 0;
		ArrayList correctWords = new ArrayList<String>();
		isCorrect = hT.Lookup( word );
		
		if( isCorrect == true ) {
			return null;
		}
		
		//Check wrong letter
		for( int i = 0; i < word.length(); i++ ) {
			
			char currChar = word.charAt(i);
			String modWord = "";
			
			for( int charVal = 97; charVal < 123; charVal++) {
				//newChar will go from a-z
				char newChar = (char) charVal;
				//Char to change is not at front or end of word
				if( i != 0 && i != word.length() - 1){
					modWord = word.substring(0,i) + newChar + word.substring(i+1,word.length());
					modWord.toLowerCase();
				}
				//Char to change is at front of word
				if( i == 0){
					modWord = newChar + word.substring(1,word.length());
					modWord.toLowerCase();
				}
				//Char to change is at end of word
				if( i == word.length() - 1) {
					modWord = word.substring(0,word.length()-1) + newChar;
					modWord.toLowerCase();
				}
				//System.out.println( modWord );
				modWordCorrect = hT.Lookup( modWord );
				//modified word is correct
				if( modWordCorrect == true ) {
					correctWords.add( modWord );		
				}
				//modified word is not correct
				if( modWordCorrect == false) {
					//Do nothing 
				}			
			}
		}
		
		//Check if extra letter
		for(int i = 0; i < word.length(); i++ ) {
			String modWord = "";
			//Extra letter is in middle of word
			if( i != 0 && i != word.length() - 1){
				modWord = word.substring(0,i) + word.substring(i+1,word.length());
				modWord.toLowerCase();
			}
			//Extra letter is the first in the word
			if( i == 0){
				modWord = word.substring(1,word.length());
				modWord.toLowerCase();
			}
			//Extra letter is the last in the word
			if( i == word.length() - 1) {
				modWord = word.substring(0,word.length()-1);
				modWord.toLowerCase();
			}
			//System.out.println( modWord );
			modWordCorrect = hT.Lookup( modWord );
			//modified word is correct
			if( modWordCorrect == true ) {
				correctWords.add( modWord );		
			}
			//modified word is not correct
			if( modWordCorrect == false) {
				//Do nothing 
			}	
			
		}
		
		//Check if missing letter (Adds from first spot to second to last spot)
		for( int i = 0; i < word.length(); i++ ) {
			
			char currChar = word.charAt(i);
			String modWord = "";
			
			for( int charVal = 97; charVal < 123; charVal++) {
				//newChar will go from a-z
				char newChar = (char) charVal;

					modWord = word.substring(0,i) + newChar + word.substring(i,word.length());
					modWord.toLowerCase();

				//System.out.println( modWord );
				modWordCorrect = hT.Lookup( modWord );
				//modified word is correct
				if( modWordCorrect == true ) {
					correctWords.add( modWord );		
				}
				//modified word is not correct
				if( modWordCorrect == false) {
					//Do nothing 
				}			
			}
		}
		//Add letter to end of word (last spot)
		for( int charVal = 97; charVal < 123; charVal++) {
			//newChar will go from a-z
			String modWord = "";
			char newChar = (char) charVal;

			modWord = word.substring(0,word.length()) + newChar;
			modWord.toLowerCase();

			//System.out.println( modWord );
			modWordCorrect = hT.Lookup( modWord );
			//modified word is correct
			if( modWordCorrect == true ) {
				correctWords.add( modWord );		
			}
			//modified word is not correct
			if( modWordCorrect == false) {
				//Do nothing 
			}			
		}
		
		//Check if two adjacent characters are swapped
		for( int i = 1; i < word.length(); i++ ) {
			char leftChar = word.charAt(i-1);
			char rightChar = word.charAt(i);
			String modWord = "";
			//If two characters are in middle of word
			if( i != 1 && i != word.length() - 1){
				modWord = word.substring(0,i-1) + rightChar + leftChar + word.substring(i+1,word.length());
				modWord.toLowerCase();
			}
			//If two characters are at front of word
			if( i == 1){
				modWord = "" + (char) rightChar + (char) leftChar + word.substring(2,word.length());
				modWord.toLowerCase();
			}
			//If two characters are at the end of the word
			if( i == word.length() - 1) {
				modWord = word.substring(0,word.length()-2) + rightChar + leftChar;
				modWord.toLowerCase();
			}
			//System.out.println( modWord );
			modWordCorrect = hT.Lookup( modWord );
				//modified word is correct
				if( modWordCorrect == true ) {
					correctWords.add( modWord );		
				}
				//modified word is not correct
				if( modWordCorrect == false) {
					//Do nothing 
				}			
		}
			
	  //Put suggestions into string array from arrayList
	  String[] correctWordsArr = new String[ correctWords.size() ];
	  for( int i = 0; i < correctWords.size(); i++ ) {
		  correctWordsArr[i] = (String) correctWords.get(i);
	  }	  
	  return correctWordsArr;	
	}
	
	public void produceStatsFile() throws FileNotFoundException, UnsupportedEncodingException {
	
		PrintWriter writer = new PrintWriter("HW8.txt", "UTF-8");
		writer.println( hT.getStats() );
		writer.close();
	
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException  // This is used for Part 2
	{
		SpellChecker sC = new SpellChecker();
		
		File dictionary = new File( args[0] );
		
		try {
			
			FileReader dictReader = new FileReader(dictionary);
			sC.ReadDictionary( dictReader );
			
		} catch (FileNotFoundException e) {
			
			System.err.println("Failed to open "+ dictionary);
			System.exit(1);
			
		}
		
		File words2Check = new File( args[1] );
		
		try{
			Scanner txt2String = new Scanner( words2Check );
			String[] corrections = new String[0];
			while(txt2String.hasNext()) {
				String word = txt2String.next();
				corrections = sC.CheckWord(word);
				System.out.print( word + ": " );
				if( corrections != null && corrections.length > 0 ) {
					for( int i = 0; i < corrections.length; i++) {
						
						if( i != corrections.length - 1) {
							System.out.print( corrections[i] + ", ");
						}
						
						if( i == corrections.length - 1) {
							System.out.print( corrections[i] );
						}
						
					}
				}
				System.out.println("");
			}
			
		}		
		catch(FileNotFoundException e)
		{
			System.err.println("Failed to open "+ words2Check);
			System.exit(1);
		}
		
		sC.produceStatsFile();

	}

}
