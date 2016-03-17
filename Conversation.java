

import java.util.Iterator;

public class Conversation implements Iterable<Email> {

	private DoublyLinkedList<Email> conv;
	private int pointer;

	public Conversation(Email e) {
		conv = new DoublyLinkedList<Email>();
		conv.add(e);
		pointer = 0;

	}

	public int getCurrent() {
		return pointer;
	}

	public void moveCurrentBack() {
		if(pointer!=0){
			pointer--;}
	}

	public void moveCurrentForward() {
		if(pointer!=conv.size()-1){
			pointer++;}//TODO
	}

	public int size() {
		return conv.size();//TODO
	}

	public Email get(int n) {
		return conv.get(n);//TODO
	}

	public void add(Email e) {
		conv.add(0,e);
		pointer=conv.size()-1;//TODO
	}

	public Iterator<Email> iterator() {
		return conv.iterator(); //TODO
	}

}
