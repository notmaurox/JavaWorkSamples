package hw6;
/**
 * Desc: Class used to store tasks with process name, duration, and deadline. Also contains methods
 * for comparing. Also contains getter methods. 
 * @author Mauro Chavez
 * @version 1.0
 * @since 11/6/2015
*/
public class Record implements Comparable<Record> {

	private String process;
	private long deadline; 
	private long duration; 

	// constructor to create a new record
	// given the name of the process,
	// deadline and duration
    /**
     * Constructor for Record object
     * @param String process, long deadline, long duration
     */
	Record (String process, long deadline, long duration)
	{
		this.process = process;
		this.deadline = deadline;
		this.duration = duration;
	}
    /**
     * Constructor for Record object using old Record object
     * @param Record r, long duration
     */
	// constructor to create a new record
	// from the existing record and new
	// duration
	Record (Record r, long duration)
	{
		this.process = r.GetProcess();
		this.deadline = r.GetDeadline();
		this.duration = duration;
	}
    /**
     * Getter method to retrieve duration
     * @return long duration
     */
	public long GetDuration()
	{
		return duration;
	}
    /**
     * Getter method to retrieve process name
     * @return String process
     */
	public String GetProcess() {
		return process;
	}
    /**
     * Converts data of Record object into string 
     * @return String Record data
     */
	public String toString()
	{
		return process+" with deadline "+deadline+" and duration "+duration;
	}
    /**
     * Converts data of Record object into string and prints in relation to current time
     * @return String Record data
     */
	public String toString(long current_time)
	{
		if(current_time > deadline) return process + " (late)";
		return process;
	}
    /**
     * Getter method to retrieve deadline time
     * @return long deadline
     */
	public long GetDeadline()
	{
		return deadline;
	}
    /**
     * Compare to method that follows conventions of Java compareTo method in comparing
     * the deadlines of two record objects
     * @return long deadline
     */
	@Override
    public int compareTo(Record o) 
	{
		if( this.deadline < o.GetDeadline() ) {
			return -1;
		}
		if( this.deadline > o.GetDeadline() ) {
			return 1;
		}
			return 0;
	
    }
}
