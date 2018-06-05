/*
 * NAME: Mauro Chavez
 * ID: A12150388
 * LOGIN: cs15xku
 */

package hw6;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Desc: Takes in a file name as a parameter and uses it to populate a MyPriorityQueue object
 * and prioritizes tasks based upon specific parameter. In this case reads file passed in on 
 * command line to create record objects. Will throw errors if the file provided on command line
 * fails to open or is entered incorrectly. 
 * @author Mauro Chavez
 * @version 1.0
 * @since 11/6/2015
*/
public class EDF {

	public static void main(String[] args) {
		if(args.length != 1)
		{
			 System.err.println("Incorrect number of arguments: "+args.length);
			 System.err.println("java hw6.EDF <input-file>");
			 System.exit(1); 
		}
		File file = new File(args[0]);
		MyPriorityQueue<Record> queue = new MyPriorityQueue<Record>(10);
		long current_time=0;
		try{
		    Scanner txt2String = new Scanner(file);
		    String firstWord = null;
		    String process = null;
		    long deadline;
		    long duration;
		    long time = 0;
		    long currTime = 0;
		    boolean hasFinished;
		    boolean hasBeenAdded;
		    Record recToAdd = null;
		    Record currRec = null;
			while(txt2String.hasNext()) {
		        firstWord = txt2String.next();
			
		        if( firstWord.equals("schedule") ) {
		        	process = txt2String.next();
		        	deadline = Long.parseLong( txt2String.next() );
		        	duration = Long.parseLong( txt2String.next() );
		        	
		        	recToAdd = new Record (process, deadline, duration);
		        	
		        	queue.add(recToAdd);
		        	
		        	System.out.println( currTime + ": adding " + recToAdd.toString() );	
		        	
		        }
		        
		        if( firstWord.equals("run") ) {
		        	time = Long.parseLong( txt2String.next() );
		        }
			
			while( currTime < time && queue.size() > 0 && time > 0) {
				hasBeenAdded = false;
				currRec = queue.poll();
				System.out.println( currTime + ": busy with " + currRec.toString() );
				currTime = currTime + currRec.GetDuration();
					if( currTime > time ) {
						currTime = currTime - currRec.GetDuration();
						currRec = new Record(currRec, (currRec.GetDuration() - (time - currTime)) );
						queue.add(currRec);
						currTime = time;
						System.out.println( currTime + ": adding " + currRec.toString() );
						hasBeenAdded = true;
					}
					if( currTime < currRec.GetDeadline() && hasBeenAdded == false ) {
						System.out.println( currTime+": done with " + currRec.GetProcess() );
					}
					if( currTime > currRec.GetDeadline() && hasBeenAdded == false )  {
						System.out.println(currTime+": done with "+currRec.GetProcess()+" (late)");
					}
					
			}	    
		  }
		}		
		catch(FileNotFoundException e)
		{
			System.err.println("Failed to open "+file);
			System.exit(1);
		}
		System.exit(0);
		
	}

}
