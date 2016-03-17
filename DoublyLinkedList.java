///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  UWmail.java
// File:             DoublyLinkedList.java
// Semester:         CS 367 Fall 2015
//
// Author:           Han Jiang
// Email:            hjiang73@wisc.edu
// CS Login:         hjiang
// Lecturer's Name:  Jim Skrentny
// Lab Section:      02
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     You Wu
// Email:            wu278@wisc.edu
// CS Login:         ywu
// Lecturer's Name:  Jim Skrentny
// Lab Section:      01
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;

/**
 * The DoublyLinkedList class is the data structure of the program. 
 * <p>Bugs: None known
 * @author Han Jiang & You Wu
 */
public  class DoublyLinkedList<E> implements ListADT<E>{
	//Data fields
	private int N = 0;        
	private Listnode<E> head = null;  
	//Non-argument constructor

	/**
	 * Adds item to the end of the List.
	 * @param E item
	 */
	public void add(E item){
		if (item == null) throw new IllegalArgumentException();
		//if item is not existent, throw IllegalArgumentException
		Listnode<E> newnode = new Listnode<E>(item);
		if(head==null){
			head = newnode;
		}
		//if no node is in the list,head refers to the new node
		else{
			Listnode<E> curr = head;
			while(curr.getNext()!= null)
				curr = curr.getNext();//iterate curr to the lastnode
			curr.setNext(newnode);
			newnode.setPrev(curr);//link the node to the end of the list
		}
		N++;//increment the size of list
	}

	/**
	 * Adds item at position pos in the List, 
	 * moving the items originally in positions pos 
	 * through size() - 1 one place to the right to make room.
	 * @param interger pos
	 * @param E item
	 */
	public void add(int pos, E item){
		if (item == null) throw new IllegalArgumentException();
		//if item is not existent, throw IllegalArgumentException
		if (pos < 0 || pos >= N)
			throw new IndexOutOfBoundsException();
		//the pos should be between 0 and N-1
		Listnode<E> curr = head;
		Listnode<E> newnode = new Listnode<E>(item);
		if(pos == 0){
			newnode.setNext(head);
			head.setPrev(newnode);
			head = newnode;
			N++; //increment size
		}//if add to the front of the list

		else{
			for(int i = 0;i<pos;i++){
				curr = curr.getNext();
			}//iterate to the pos
			newnode.setPrev(curr.getPrev());
			curr.getPrev().setNext(newnode);
			newnode.setNext(curr);
			curr.setPrev(newnode);
			N++;}//increment size
	} 

	/**
	 * Returns true iff item is in the List 
	 * (i.e., there is an item x in the List such that x.equals(item))
	 * @param E item
	 * @return boolean
	 */
	public boolean contains(E item){
		if (item == null) throw new IllegalArgumentException();
		//if item is not existent, throw IllegalArgumentException
		Listnode<E> curr = head;
		if(head==null){
			return false;
		}
		//if no listnode in the list,return false

		else{
			boolean found = false;
			while(curr!= null&& !found){
				if(curr.getData().equals(item)){
					found= true;
				}

				else curr = curr.getNext();
			}
			return found;//iterate to check if the list contain item
		}

	}

	/**
	 * Returns the item at position pos in the List.
	 * @param int pos
	 * @return E
	 */
	public E get(int pos) {

		if (pos < 0 || pos >= N)
		{throw new IndexOutOfBoundsException();}
		//the pos should be between 0 and N-1
		Listnode<E> curr = head;
		for(int i = 0;i<pos;i++){
			curr = curr.getNext();
		}//iterate to the pos
		return curr.getData();//get E
	}

	/**
	 * Returns true iff the List is empty.
	 * @return boolean
	 */
	public boolean isEmpty() {
		return N == 0;
	}

	/**
	 * Removes and returns the item at position pos in the List,
	 * moving the items originally in positions pos+1 
	 * through size() - 1 one place to the left to fill in the gap.
	 * @param int pos
	 * @return E
	 */
	public E remove(int pos) {
		E data = null;
		if (pos < 0 || pos >= N)
			throw new IndexOutOfBoundsException();
		//the pos should be between 0 and N-1
		if(N==0) {
			throw new NullPointerException();
		}//if there is no listnode in the list,
		//throw NullPointerException

		if(!isEmpty()) {
			if(N==1){//if only one listnode in the list
				data = head.getData();
				head = null;
				N--;
			}
			else{
				Listnode<E> curr = head;
				for(int i=0;i<pos;i++) {
					curr = curr.getNext(); 
				}//iterate to the pos
				data = curr.getData();//return E
				if(curr.getPrev()==null){
					curr.getNext().setPrev(null);
					head = curr.getNext();
				}//if pos ==0
				else if(curr.getNext()==null){

					curr.getPrev().setNext(null);
					curr.setPrev(null);

				}//if pos == N-1
				else{
					curr.getPrev().setNext(curr.getNext());
					curr.getNext().setPrev(curr.getPrev());
				}
				N--;//decrement the size

			}
		}
		return data;
	}

	/**
	 * Return the number of items in the List.
	 *
	 * @return integer N (size of the list)
	 */
	public int size() {

		return N;     
	}

	/**
	 * Returns an Iterator<E>, 
	 * per the Iterable<E> interface.
	 *
	 * @return E iterator
	 */
	public Iterator<E> iterator() {

		return new DoublyLinkedListIterator<E>(head);
	}
}
