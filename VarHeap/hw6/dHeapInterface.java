package hw6;
/**
 * Desc: Defines the methods that must be included in dHeap class
 * @author Mauro Chavez
 * @version 1.0
 * @since 11/6/2015
*/
public interface dHeapInterface<T extends java.lang.Comparable<? super T>>
{
    /**
     * Adds an element to the dHeap object
     * @param T data to add
     */
	public void add( T o );
    /**
     * Removes the smallest element from the dHeap object
     * @return T removed
     */
	public T removeSmallest();
    /**
     * Gets the size of the current dHeap
     * @int int elements in dHeap
     */
	public int size();

}
