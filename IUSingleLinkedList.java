import java.util.*;

/**
 * Single-linked node implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported.
 * 
 * @author
 * 
 * @param <E> type to store
 */
public class IUSingleLinkedList<E> implements IndexedUnsortedList<E> {
	private LinearNode<E> front, rear;
	private int count;
	private int modCount;

	/** Creates an empty list */
	public IUSingleLinkedList() {
		front = rear = null;
		count = 0;
		modCount = 0;
	}

	@Override
	public void addToFront(E element) {
		// TODO
		LinearNode<E> node = new LinearNode<E>(element);
		node.setNext(front.getNext());
		front = node; // One for the garbage man
	}

	@Override
	public void addToRear(E element) {
		LinearNode<E> node = new LinearNode<E>(element);
		if (isEmpty()) {
			front = node;
		} else {
			rear.setNext(node);
		}
		rear = node;
		count++;
	}

	@Override
	public void add(E element) {
		addToRear(element);

	}

	@Override
	public void addAfter(E element, E target) {
		// TODO

	}

	@Override
	public void add(int index, E element) {
		// TODO
		LinearNode<E> node = new LinearNode<E>(element);

	}

	@Override
	public E removeFirst() {
		return remove(front.getElement());
	}

	@Override
	public E removeLast() {
		return remove(rear.getElement());
	}

	@Override
	public E remove(E element) { // this was given to us do not touch
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		LinearNode<E> current = front, previous = null;
		while (current != null && !current.getElement().equals(element)) {
			previous = current;
			current = current.getNext();
		}
		// Matching element not found
		if (current == null) {
			throw new NoSuchElementException();
		}
		return removeElement(previous, current);
	}

	@Override
	public E remove(int index) {
		return remove(get(index));
	}

	@Override
	public void set(int index, E element) {
		// TODO
		checkEmpty();
		LinearNode<E> current = front;
		for (int i = 0; i <= index; i++) {
			current = current.getNext();
		}
		current.setElement(element);

	}

	@Override
	public E get(int index) {
		checkEmpty();
		LinearNode<E> current = front;
		for (int i = 0; i <= index; i++) {
			current = current.getNext();
		}
		return current.getElement();
	}

	@Override
	public int indexOf(E element) {
		LinearNode<E> current = front, previous = null;
		int index = 0;
		while (current != null && !current.getElement().equals(element)) {
			previous = current;
			current = current.getNext();
			index++;
		}
		if (current == null) {
			return -1;
		}
		return index;
	}

	@Override
	public E first() {
		checkEmpty();
		return front.getElement();
	}

	@Override
	public E last() {
		checkEmpty();
		return rear.getElement();
	}

	@Override
	public boolean contains(E target) {
		return indexOf(target) != -1;
	}

	@Override
	public boolean isEmpty() {
		// TODO
		return size() == 0;
	}

	@Override
	public int size() {
		// TODO
		return count;
	}

	@Override
	public String toString() {
		// TODO
		return "";
	}

	private E removeElement(LinearNode<E> previous, LinearNode<E> current) {
		// Grab element
		E result = current.getElement();
		// If not the first element in the list
		if (previous != null) {
			previous.setNext(current.getNext());
		} else { // If the first element in the list
			front = current.getNext();
		}
		// If the last element in the list
		if (current.getNext() == null) {
			rear = previous;
		}
		count--;
		modCount++;

		return result;
	}

	@Override
	public Iterator<E> iterator() {
		return new SLLIterator();
	}

	/** Iterator for IUSingleLinkedList */
	private class SLLIterator implements Iterator<E> {
		private LinearNode<E> previous;
		private LinearNode<E> current;
		private LinearNode<E> next;
		private int iterModCount;

		/** Creates a new iterator for the list */
		public SLLIterator() {
			previous = null;
			current = null;
			next = front;
			iterModCount = modCount;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			if (hasNext()) {
				throw new NoSuchElementException();
			}
			previous = current;
			current = current.getNext();
			next = current.getNext();
			return previous.getElement();
		}

		@Override
		public void remove() {
			// TODO
			previous.setNext(next); // Current should be GC'd? Might have to set current to something.
		}
	}

	private void checkEmpty() {
		if (!isEmpty()) {
			throw new NoSuchElementException(); // Might need to double check the exception to throw and if we are expecting a
																					// different one
		}
	}

	// IGNORE THE FOLLOWING CODE
	// DON'T DELETE ME, HOWEVER!!!
	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}
}
