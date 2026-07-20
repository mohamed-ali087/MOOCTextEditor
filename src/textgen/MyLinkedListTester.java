/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		/* Test procedures
		 * 1- check if the last element has the added value
		 * 2- check if size is incremented by 1
		 * 3- check if the next element is tail
		 * 4- check if prev is the previous value (for non-empty list)
		 * 5- check if prev.next = the new node.
		 */
		
		/*
		 * Check for empty list (corner case)
		 */
		emptyList.add(3);
		assertEquals("Check size for empytList", 1, emptyList.size());
		assertEquals("Check new element value for empytList", 3, emptyList.get(0).intValue());
		assertEquals("Check if the new element's next node is the tail for empytList", emptyList.tail, emptyList.getNode(0).next);
		assertEquals("Check the link between the new node and it's previous node for empytList", emptyList.getNode(0), emptyList.head.next);
		
		/*
		 * Check for regular list (short list)
		 */
		
		shortList.add("C");
		assertEquals("Check size for shortList", 3, shortList.size());
		assertEquals("Check new element value for shortList", "C", shortList.get(2));
		assertEquals("Check if the new element's next node is the tail for shortList", shortList.tail, shortList.getNode(2).next);
		assertEquals("Check the link between the new node and it's previous node for shortList", shortList.getNode(2), shortList.getNode(2).prev.next);
		assertEquals("Check the previous value", "B", shortList.get(1));
		
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// Corner case: emptyList
		assertEquals("Check emptyList size", 0, emptyList.size());
		// shortList
		assertEquals("Check shortList size", 2, shortList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		/*	procedures
		 * 	1- check if the value was added in the right index
		 * 	2- check for linking
		 * 		prev = oldNode.prev
		 * 		next = oldNode
		 * 		oldNode.next = the newNode
		 * 	3- check that oldNode is pushed the right direction
		 * 	4- check if size is incremented by 1
		 *  
		 */
		
		/*
		 * Test for list1
		 * corner cases: add in index: 0
		 * 						       2
		 * 
		 * regular: add in index: 1
		 */
		
		list1.add(2, 11);
		assertEquals("check if the value was added in the right index for index 2",
				11, list1.get(2).intValue());
		
		list1.add(0, 22);
		assertEquals("check if the value was added in the right index for index 0",
				22, list1.get(0).intValue());
		
		list1.add(1, 33);
		assertEquals("check that nodes are pushed the right direction, for the prev node",
				22, list1.get(0).intValue());
		
		assertEquals("check that nodes are pushed the right direction, for the next node",
				21, list1.get(2).intValue());
		
		assertEquals("Check for size increment", 6, list1.size());
		
		
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
