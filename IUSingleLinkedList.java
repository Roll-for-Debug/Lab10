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
		// TODO Colin
	}

	@Override
	public void addToRear(E element) {
		// TODO Zion
	}

	@Override
	public void add(E element) {
		// TODO Tyler

	}

	@Override
	public void addAfter(E element, E target) {
		// TODO Kelsi
	}

	@Override
	public void add(int index, E element) {
		// TODO Tyra
        if (index < 0 || index > count) { throw new IndexOutOfBoundsException(); }
        if (isEmpty()) {
            front = rear = new LinearNode<E>(element);
            count++;
            return;
        }
        LinearNode<E> current = front;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        if (current == null) {
            current = new LinearNode<E>(element);
            return;
        }
        LinearNode<E> add = new LinearNode<E>(element);
        add.setNext(current);
        if (current == front) {
            front = add;
        }
        count++;

    }

	@Override
	public E removeFirst() {
		// TODO Colin
	}

	@Override
	public E removeLast() {
		// TODO Zion
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
		// TODO Kelsi
	}

	@Override
	public void set(int index, E element) {
		// TODO Tyra
		indexOf(index).setElement(element);
		return;
	}

	@Override
	public E get(int index) {
		// TODO Colin
	}

	@Override
	public int indexOf(E element) {
		// TODO Zion
	}

	@Override
	public E first() {
		// TODO Tyler
	}

	@Override
	public E last() {
		// TODO Kelsi
	}

	@Override
	public boolean contains(E target) {
		// TODO Tyra
		return indexOf(target) != -1;
	}

	@Override
	public boolean isEmpty() {
		// TODO Colin
	}

	@Override
	public int size() {
		// TODO Zion
	}

	@Override
	public String toString() {
		// TODO Tyler
	}

	private E removeElement(LinearNode<E> previous, LinearNode<E> current) { // given, don't much
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
			// TODO Kelsi
		}

		@Override
		public E next() {
			// TODO Kyra
		}

		@Override
		public void remove() {
			// TODO Colin
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
