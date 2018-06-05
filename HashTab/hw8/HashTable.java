/*
 * NAME: Mauro Chavez
 * ID: A12150388
 * LOGIN: cs15xku
 */

package hw8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
/**
 * Desc: Contains methods for creating a hash table, populating it with data, and searching the hash table for that data.
 * Also contains methods for printing the hash table. Hash table automatically grows when loading factor exceeds 
 * .75.
 * @author Mauro Chavez
 * @version 1.0
 * @since 11/21/2015
*/
public class HashTable implements IHashTable {
	
	private int m;
	private int n;
	private double loadFactor;
	private int timesExpanded;
	private int collisions;
	private int longestChain;
	private Node[] arr; 
	
	public HashTable() {
		m = 223;
		n = 0;
		timesExpanded = 0;
		collisions = 0;
		loadFactor = (double) n/m;
		arr = new Node[m];
	}
	private int getTimesExpanded() {
		return timesExpanded;
	}
	private double getLoadFactor() {
		return loadFactor;
	}
	private int getCollisions() {
		return collisions;
	}
	private int getLongestChain() {
		return longestChain;
	}
	
	/** Insert the string value into the hash table
	 * 
	 * @param value value to insert
	 * @return true if the value was inserted, false if the value was already present
	 */
	@Override
	public boolean Insert(String value) {
		
		int valIndex = toHashVal(value);
		n++;
		loadFactor = (double) n/m;
		if( loadFactor >= .75 ) {
			extendArray();
		}
		//If there is already a node in the array at this spot
		if( arr[valIndex] != null ) {
			Node currNode = arr[valIndex];
			if( currNode.getElement().equals(value) && currNode.isDeleted() == false ) {
				//System.out.println("item "+ value + " already present.");
				return false;
			}
			int chainLength = 1;
			while( currNode.hasNext() == true) {
				currNode = currNode.getNext();
				chainLength++;
				if( currNode.getElement().equals(value) && currNode.isDeleted() == false ) {
					//System.out.println("item "+ value +" already present.");
					return false;
				}
			}
			
			
			Node topNode = arr[valIndex];
			Node newNode = new Node( value );
			newNode.setNext(topNode);
			arr[valIndex] = newNode;
			chainLength++;
			if( chainLength > longestChain ) {
				longestChain = chainLength;
			}
			//System.out.println("item " + value + " successfully inserted");
			collisions++;
			return true;
		}
		//If index is empty
		if( arr[valIndex] == null) {
			arr[valIndex] = new Node( value );
			//System.out.println("item " + value + " successfully inserted");
			return true;
		}
		n--;
		return false;
	}
	/** Delete the given value from the hash table
	 * 
	 * @param value value to delete
	 * @return true if the value was deleted, false if the value was not found
	 */
	@Override
	public boolean Delete(String value) {
		
		int valIndex = toHashVal(value);
		if( arr[valIndex] != null ) {
			Node currNode = arr[valIndex];
			if( currNode.getElement().equals(value) && currNode.isDeleted() == false) {
				currNode.setDeleted(true);
				//System.out.println("item "+ value + " successfully deleted");
				return true;
			}
			Node prevNode = arr[valIndex];
			currNode = currNode.getNext();
			while( currNode != null) {
				if( currNode.getElement().equals(value) && currNode.isDeleted() == false) {
					if( currNode.hasNext() == false) {
						prevNode.setNext(null);
						//System.out.println("item "+ value + " successfully deleted");
						return true;
					}
					if( currNode.hasNext() == true) {
						prevNode.setNext( currNode.getNext() );
						//System.out.println("item "+ value + " successfully deleted");
						return true;
					}
				}
				prevNode = currNode;
				currNode = currNode.getNext();
			}
		}
		//System.out.println("item "+ value + " not found");
		return false;
	}
	/** Check if the given value is present in the hash table
	 * 
	 * @param value value to look up
	 * @return true if the value was found, false if the value was not found
	 */
	@Override
	public boolean Lookup(String value) {
		int valIndex = toHashVal(value);
		if( arr[valIndex] != null ) {
			Node currNode = arr[valIndex];
			if( currNode.getElement().equals(value) && currNode.isDeleted() == false) {
				//System.out.println("item "+ value + " found");
				return true;
			}
			while( currNode.hasNext() == true) {
				currNode = currNode.getNext();
				if( currNode.getElement().equals(value) && currNode.isDeleted() == false ) {
					//System.out.println("item "+ value + " found");
					return true;
				}
			}
		}
		//System.out.println("item "+ value + " not found");
		return false;
	}
	/** Print the contents of the hash table to the given print stream
	 * 
	 * Call this function using hashTable.Print(System.out);
	 * 
	 * Inside this function use: out.println(key+": "+value);
	 * Do not use System.out.println in this function!
	 * 
	 * Example output for this function:
	 * 
	 * 0:
	 * 1:
	 * 2: marina, fifty
	 * 3: table
	 * 4:
	 * 
	 * @param out the output stream
	 */
	@Override
	public void Print(PrintStream out) {
		for( int i = 0; i < arr.length; i++) {
			out.print( i + ": " );
			if( arr[i] != null ) {
				Node currNode = arr[i];
				out.print( currNode.getElement() );
				while( currNode.hasNext() == true) {
					currNode = currNode.getNext();
					System.out.print( ", " + currNode.getElement() );
				}
			}
			out.println("");
		}
		
	}
	/**
	 * Gets stats for hash table (expanded,load factor, collisions, longest chain)
	 *  and returns them as a string. 
	 * @return String
	 */
	public String getStats() {
		String stats = "";
		
		stats = this.getTimesExpanded() + " resizes, load factor " +
				this.getLoadFactor() + ", " + 
				this.getCollisions() + " collisions, " +
				this.getLongestChain() + " longest chain";
		
		return stats;
	}
	//Used to turn strings into hash values
	private int toHashVal( String s ) {
		//From slides 1
		//Tested to work better
		int hashVal = 0;
		for(int i =0; i< s.length();i++)
		{
			int letter = s.charAt(i);
			hashVal = (hashVal *27 + letter)%m;
		}
		return hashVal;
		
		//CRC 
//		int hashVal = 0;
//		for( int i = 0; i < s.length(); i++) {
//			int leftShiftedValue = hashVal << 5;
//			int rightShiftedValue = hashVal >> 27;
//			hashVal = (leftShiftedValue | rightShiftedValue) ^ s.charAt(i);
//		}
//		if( hashVal < 0 ) {
//			hashVal = hashVal * -1;
//		}
//		return hashVal % this.m;
	}
	//Helper method to extend array
	private void extendArray() {
		 m = m*2;
		 n = 0;
		 longestChain = 0;
		 collisions = 0;
		 Node[] tempArr = this.arr;
		 this.arr = new Node[m];
		 
		 for( int i = 0; i < tempArr.length; i++ ) {
		
			 if( tempArr[i] != null) {
				Node currNode = tempArr[i];
				this.Insert( currNode.getElement() );
				
				if( currNode.hasNext() == true ) {
					currNode = currNode.getNext();
					this.Insert( currNode.getElement() );		
				}
			}
		 }
		 loadFactor = (double) n/m;
		 timesExpanded++;
	}

	public static void main(String[] args) // This is used only for Part 1
	{
		PrintStream ps = new PrintStream(System.out);
		HashTable ht = new HashTable();
		
		File file = new File(args[0]);
		try{
			
			Scanner txt2String = new Scanner(file);
			while(txt2String.hasNext()) {
				String word = txt2String.next();
				
				if( word.equals("insert") ) {
					String wrd2Use = txt2String.next();
					ht.Insert(wrd2Use);
				}
				if( word.equals("lookup") ) {
					String wrd2Use = txt2String.next();
					ht.Lookup(wrd2Use);
					
				}
				if( word.equals("delete") ) {
					String wrd2Use = txt2String.next();
					ht.Delete(wrd2Use);	
				}
				if( word.equals("print") ) {
					ht.Print(ps);
				}
			}
			
		}		
		catch(FileNotFoundException e)
		{
			System.err.println("Failed to open "+file);
			System.exit(1);
		}
		
	}
		
		protected class Node {
	    	String data;
	    	Node next;
	    	Boolean isDeleted;
	    
	    	/** 
	    	 * Creates a single node with no left and right pointers
	    	 * @param s
	    	 */
	    	public Node( String s )
	    	{
	    		this.data = s;
	    		this.isDeleted = false;
	    		this.next = null;
	    	}
	    	/**
	    	 * Checks current node to see if there is a next node in the list
	    	 * @param Node
	    	 * @return boolean
	    	 */
	    	public boolean hasNext() 
	    	{
	    		if( this.getNext() == null) {
	    			return false;
	    		} else {
	    			return true;
	    		}
	    	}	    	
	    	/** Set the next node data as node passed in as parameter
	    	 *  @param Node
	    	 */
	    	public void setNext(Node n)
	    	{
	    		this.next = n;
	    	}
	    
	    	/** Set the element 
	    	 *  @param String
	    	 */
	    	public void setElement(String s)
	    	{
	    		this.data = s;
	    	}
	    	
	    	/**
	    	 * Getter method that returns next node
	    	 * @return Node
	    	 */
	    	public Node getNext()
	    	{
	    		return this.next;
	    	}
	    	/**
	    	 * Getter method that returns String held in node
	    	 * @return String
	    	 */
	    	public String getElement()
	    	{
	    		return this.data;
	    	}
	    	/**
	    	 * Checks the nodes boolean flag to see if it has been deleted
	    	 * @return boolen
	    	 */
	    	public boolean isDeleted()
	    	{
	    		return this.isDeleted;
	    	}
	    	/**
	    	 * Sets the nodes boolean flag to true/false if it has been deleted
	    	 * @param b
	    	 */
	    	public void setDeleted( boolean b)
	    	{
	    		this.isDeleted = b;
	    	}
	    	/**
	    	 * Removes calling object node from list. 
	    	 */
	    	public void removeNode()
	    	{
	    	}
	    	
	  }
}
