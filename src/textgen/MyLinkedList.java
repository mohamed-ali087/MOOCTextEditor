package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		
		head.data = null;
		tail.data = null;
		
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E data) /*(E element )*/
	{
		/* add(size, data);
		return false; */
		return super.add(data);
	}

	
	/* helper method
	 * get the node instead of getting only the data it's holding
	 * This method would be protected in a linked list package
	 */

	protected LLNode<E> getNode(int index) throws IndexOutOfBoundsException{


		if(index > (size - 1) || index < 0) {

			throw new IndexOutOfBoundsException();

		}
		
		/*
		 * 	start from either the head or the tail depending on which one is closer to the element
		 */

		LLNode<E> node;

		if(index + 1 <= size()/2) {
			node = head;
			
			int currentIndex = -1;
			
			// dereference next node till reaching the element index
			while(currentIndex < index && node.next != tail) {
				node = node.next;
				
				currentIndex++;
			}
		} else {
			node = tail;
			
			int currentIndex = size;
			
			// dereference next node till reaching the element index
			while(currentIndex > index && node.prev != head) {
				node = node.prev;
				
				currentIndex--;
			}
			
		}
		
		
		return node;
	}
	
	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		return getNode(index).data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	@Override
	public void add(int index, E data ) throws NullPointerException
	{
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		if(data == null) {
			throw new NullPointerException();
		}
		
		/*
		 * add the new node before the old node with that index.
		 */

		LLNode<E> node = new LLNode<E>(data);
		LLNode<E> oldNode;
		if(size > 0 && index < (size - 1) ) {
			oldNode = getNode(index);
		} else {
			oldNode = tail;
		}

		node.next = oldNode;
		node.prev = oldNode.prev;
		oldNode.prev.next = node;
		oldNode.prev = node;
		
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */

	/* Helper method */ /* protected in data structures package*/
	private LLNode<E> removeNode(int index){
		LLNode<E> node = getNode(index); // this holds the reference of the data
		
		node.next.prev = node.prev;
		node.prev.next = node.next;
		
		node.next = null;
		node.prev = null;
		
		size--;

		return node; // returning the reference of the removed data, garbage collector won't delete data from memory if this reference is stored.
		
	}

	
	public E remove(int index) 
	{
		return removeNode(index).data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E data) throws IndexOutOfBoundsException /*E element) */
	{
		LLNode<E> node = getNode(index); // get method throws IndexOutOfBoundsException
		
		E oldData = node.data;
		
		node.data = data; // alter data
		
		return oldData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	

}
