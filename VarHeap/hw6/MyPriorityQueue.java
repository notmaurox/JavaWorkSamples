/*
 * NAME: Mauro Chavez
 * ID: A12150388
 * LOGIN: cs15xku
 */

package hw6;
/**
 * Desc: Uses a binary dHeap object to provide the functionality of a priority queue with
 * add and poll methods.
 * @author Mauro X Chavez
 * @version 1.0
 * @since 11/6/2015
*/
public class MyPriorityQueue<T> {

	private dHeap biHeap = null;
	/**
	 * Creates my priority queue object wih size of parameter
	 * @param queueSize
	 */
	public MyPriorityQueue ( int queueSize ) {
		biHeap = new dHeap(queueSize);
	}
	/**
	 * Getter method for getting the current size of the heap
	 * @return size of heap
	 */
	public int size() {
		return biHeap.size();
	}
	/**
	 * Adds element passed in as parameter to heap
	 * @param data
	 * @return true
	 */
	public boolean add( T data ) {
		if( data.equals(null) ) {
			throw new NullPointerException();
		}
		biHeap.add((Comparable) data);
		return true;
	}
	/**
	 * Removes the smallest element from the heap and returns it
	 * @return smallest element
	 */
	public T poll() {
		T removedData = (T) biHeap.removeSmallest();
		return removedData;
	}
}
