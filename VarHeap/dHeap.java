/*
 * NAME: Mauro Chavez
 * ID: A12150388
 * LOGIN: cs15xku
 */

package hw6;

import java.util.*;
import java.lang.*;
/**
 * Desc: Implements dHeapInterface to create a class used for creating heaps with varying
 * numbers of children per node. Uses an array to create heap but tracks data as if it was
 * in a tree.
 * @author Mauro Chavez
 * @version 1.0
 * @since 11/6/2015
*/
class dHeap <T extends Comparable <? super T>> implements dHeapInterface<T> {
	
	private T[] array;
	private int size;
	private int numChildren;
	
	//The constructor  takes one argument: an initial capacity
	//Used when binary heap is needed.
	/**
	 * Constructor that makes binary heap and uses heap size to initialize array
	 * array will grow automatically
	 * @param heapSize
	 */
	public dHeap (int heapSize) {
		array = (T[]) new Comparable[heapSize];	
		size = 0;
		numChildren = 2;
	}

	//The constructor takes two arguments: an initial capacity
	//and the number of children, d
	//if d is less than one, throw IllegalArgumentException()
	/**
	 * dHeap constructor using paramter d to define how many children each node in tree has
	 * also uses heap size to initialize array
	 * @param d
	 * @param heapSize
	 */
	public dHeap (int d, int heapSize) { 
		if ( d < 1) {
			throw new IllegalArgumentException();
		}
		array = (T[]) new Comparable[heapSize];	
		size = 0;
		numChildren = d;
	}
    /**
     * Gets the size of the current dHeap
     * @int int elements in dHeap
     */
	public int size () { 
		return this.size;
	}
	/**
	 * Returns the current dHeap array as a string
	 * @return String array
	 */
	public String heapAsString() {
		return Arrays.toString(this.array);
	}
    /**
     * Adds an element to the dHeap object
     * @param T data to add
     */	
	public void add (T data) { 
		//Prevents null data from being added to tree
		if( data.equals(null) ) {
			throw new IllegalArgumentException();
		}
		//If array is full make longer
		if( this.size == array.length) {
			T[] arrayNew = (T[]) new Comparable[(array.length * 2)];
			for( int i = 0; i < array.length; i++) {
				arrayNew[i] = array[i];
			}
			this.array = arrayNew;
		}
		//Add data to the array
		array[size] = data;
		this.bubbleUp(size);	
		this.size ++;
	}
    /**
     * Removes the smallest element from the dHeap object
     * @return T removed
     */
	public T removeSmallest () { 
		T data = array[0];
		array[0] = array[size-1];
		array[size-1] = null;
		this.size--;
		if( size > 1) {
			this.trickleDown(0);
		}
		return data;
	}
	//Gets the smallest child attached to node at index
	private int getSmallestChild(int index) {
		T child;
		T childToCompare;
		int childIndex;
		boolean doesNextChildExist = true;
		//If first child is outside of array return null
		try { 
			child = array[ (index*numChildren + 1) ];
		} catch ( IndexOutOfBoundsException e) {
			return -1;
		}
		//If first child is null return null
		try { 
			child = array[ (index*numChildren + 1) ];
		} catch ( NullPointerException e) {
			return -1;
		}
		//If first child exists save its index and data
		childIndex = index*numChildren + 1;
		child = array[ childIndex ];
		if( child == null ) {
			return -1;
		}
		//Check other children of node at given index
		for( int i = 2; i <= numChildren; i++ ) {
			//If next child is out of bounds then it doesnt exist
			try { 
				childToCompare = array[ (index*numChildren + i) ];
			} catch ( IndexOutOfBoundsException e) {
				doesNextChildExist = false;
			}
			//If next child is null then it doesnt exist
			try { 
				childToCompare = array[ (index*numChildren + i) ];
			} catch ( NullPointerException e) {
				doesNextChildExist = false;
			}
			//if child does exist get its data
			if( doesNextChildExist == true ) {
				childToCompare = array[ (index*numChildren + i) ];
				//Compare current child to next child and save the next child
				//if childToCompare is less than current child
				if( childToCompare != null && 
					 childToCompare.compareTo(child) < 0) {
					child = childToCompare;
					childIndex = index*numChildren + i;
				}
			}			
		}
		return childIndex;
	}
	//Used to shift elements from higher position to lower position preserving min heap 
	//functionality
	private void trickleDown(int index) {
		T child;
		T childToCompare;
		int childIndex;
		T tempData = null;
		boolean isNull = false;
		//If child index doesn't exist it will return -1
		childIndex = this.getSmallestChild(index);
		if( childIndex == -1 ) {
			return;
		}
		//If current index is larger than child, swap data 
		//if not at the bottom of the tree call trickleDown again recursivly
		if( array[index].compareTo(array[childIndex]) > 0) {
			tempData = array[index];
			array[index] = array[childIndex];
			array[childIndex] = tempData;
			if( childIndex < size ) {
				this.trickleDown(childIndex);
			}
		}	
	}
	//Used to shift elements from the bottom up to preserve min heap functionality
	private void bubbleUp(int index) {
		T tempData = null;
		//Gets index of parent node
		double parentIndex = Math.floor( (index - 1) / numChildren );
		//If child is smaller than parent then they swap and bubble up recursivly called
		//again on parent index
		if( array[index].compareTo(array[(int) parentIndex]) < 0 )  {
			tempData = array[(int) parentIndex];
			array[(int) parentIndex] = array[index];
			array[index] = tempData;
			this.bubbleUp( (int) parentIndex );
		} else {
			return;
		}
	}

}

