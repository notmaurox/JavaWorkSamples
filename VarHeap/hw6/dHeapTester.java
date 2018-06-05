/*
 * NAME: Mauro Chavez
 * ID: A12150388
 * LOGIN: cs15xku
 */

package hw6;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.*;
/**
 * Desc: Used to test functionality of dHeap tester on various conditions
 * in a tree.
 * @author Mauro Chavez
 * @version 1.0
 * @since 11/6/2015
*/
public class dHeapTester {

	private dHeap dHeapDof5;
	private dHeap dHeapObj;
	private dHeap dHeapTenOrderedElements;
	private String dHeapTenOrderedElementsPlus0 = 
			"[0, 1, 3, 4, 2, 6, 7, 8, 9, 10, 5, null, null, null, null, null, null, null, null, null]";
	private String dHeapTenOrderedElementsRemoveSmallest =
			"[2, 4, 3, 8, 5, 6, 7, 10, 9, null]";
	private String dHeap3AfterRemove = 
			"[1, 3, null, null, null, null, null, null, null, null]";
	//Adds enough elements to heap to make array expand in size
	@Before
	public void setUp() {
		dHeapObj = new dHeap<Integer>(10);
		dHeapObj.add(1);
		dHeapTenOrderedElements = new dHeap<Integer>(10);
		dHeapTenOrderedElements.add(1);
		dHeapTenOrderedElements.add(2);
		dHeapTenOrderedElements.add(3);
		dHeapTenOrderedElements.add(4);
		dHeapTenOrderedElements.add(5);
		dHeapTenOrderedElements.add(6);
		dHeapTenOrderedElements.add(7);
		dHeapTenOrderedElements.add(8);
		dHeapTenOrderedElements.add(9);
		dHeapTenOrderedElements.add(10);	
		
	}
	//Checks remove smallest on a d = 5 heap
	@Test
	public void findSmallestDof5() {
		dHeapDof5 = new dHeap<Integer>(5,10);
		dHeapDof5.add(0);
		dHeapDof5.add(2);
		dHeapDof5.add(3);
		dHeapDof5.add(5);
		dHeapDof5.add(1);
		dHeapDof5.removeSmallest();
		assertEquals("checks size after removal", dHeapDof5.size(), 4);
	}
	// Checks the size of the heap after addition of element
	@Test
	public void size()
    {
	    dHeapObj.add(0);
		assertEquals("Check size of dHeapObj", 2, dHeapObj.size());
    }
	// Adds element to full array forcing extention and checks to make sure the
	//results match what a properly functioning heap would provide
	@Test
	public void add() 
	{
	    dHeapTenOrderedElements.add(0);
	    assertEquals("Check if swapps appropriately occured", dHeapTenOrderedElementsPlus0, 
	    		dHeapTenOrderedElements.heapAsString());
	}
	//Checks remove smallest on a full heap
	@Test
	public void removeSmallest()
	{
		dHeapTenOrderedElements.removeSmallest();
		assertEquals("Check if swapps appropriately occured", dHeapTenOrderedElementsRemoveSmallest, 
	    		dHeapTenOrderedElements.heapAsString());
	}
	//Checks remove smallest on heap of 2 elements
	@Test
	public void removeSmallestOnTwo()
	{
		dHeapObj.add(0);
		dHeapObj.removeSmallest();
		assertEquals("Check size of dHeapObj", 1, dHeapObj.size());
		
	}
	//Tests remove smallest on heap of 3 elements
	@Test
	public void removeSmallestOnThree()
	{
		dHeapObj.add(0);
		dHeapObj.add(3);
	    System.out.print(dHeapObj.heapAsString());
		dHeapObj.removeSmallest();
		assertEquals("Check size of dHeapObj", dHeap3AfterRemove, dHeapObj.heapAsString() );
	}
	
	
	//Tests to make sure improperly intitialized dHeap throws error
	@Test
	public void testDequalsZero()
	{
		try{ 
			dHeapObj = new dHeap<Integer>(0,10);
			fail();
		}
		catch(IllegalArgumentException e){}
	}
	//Checks to make sure improper type cant be added to dHeap
	@Test
	public void addWrongType()
	{
		try{
			dHeapObj.add("Wrong Obj");
			fail();
		}
		catch( ClassCastException e){}
	}
	
}
