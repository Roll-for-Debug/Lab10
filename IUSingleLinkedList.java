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
	public void addToFront(E element) { // Colin
		LinearNode<E> node = new LinearNode<E>(element);
		if (isEmpty()) {
			front = rear= node;
			count++;
			return;
		}
		node.setNext(front);
		front = node; // One for the garbage man
		count++;
		modCount++;
	}

	@Override
	public void addToRear(E element) { // Zion
		LinearNode<E> newNode = new LinearNode<E>(element);
		rear.setNext(newNode);
		rear = rear.getNext();
		newNode = null;
		count++;
	}

	@Override
	public void add(E element) {
		// Review Tyler
		addToRear(element);
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
	public E removeFirst() { // Colin
		if (front == null) { throw new NoSuchElementException(); }
		front = front.getNext();
		modCount++;
		return front.getElement();
	}

	@Override
	public E removeLast() { // Zion
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
		// TODO Kelsi
	}

	@Override
	public void set(int index, E element) {
		// TODO Tyra
		if (isEmpty()) { throw new IndexOutOfBoundsException(); }
        if (index < 0 || index > count) { throw new IndexOutOfBoundsException(); }
        LinearNode<E> current = front;
        int i = 0;
        while (current != null && i < index){
            current = current.getNext();
            i++;
        }
        if (current == null) { throw new IndexOutOfBoundsException(); }
        current.setElement(element);
    }

	@Override
	public E get(int index) { // Colin
		if (isEmpty()) { throw new IndexOutOfBoundsException(); }
		if (index < 0 || index > count) { throw new IndexOutOfBoundsException(); }
		LinearNode<E> current = front;
		int i = 0;
		while (current != null && i < index){
			current = current.getNext();
			i++;
		}
		if (current == null) { throw new IndexOutOfBoundsException(); } // Not sure if this is necessary - Colin
		modCount++;
		return current.getElement();
	}

	@Override
	public int indexOf(E element) { // Zion
		if ( isEmpty() ) { throw new RuntimeException(); } // Need to replace with correct exception, unless it should return -1 "I think it's just -1" - Colin

		LinearNode<E> temp = this.front;
		int indexCounter = 0;

		do {
			if (temp.equals(element)) {
				return indexCounter;
			}
			temp = temp.getNext();
			indexCounter++;
		} while (!(temp == null));

		return -1;
	}

	@Override
	public E first() {
		// Review Tyler
		return front.getElement();
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
	public boolean isEmpty() { // Colin
		return count == 0;
	}

	@Override
	public int size() { // Zion
		return count;
	}

	@Override
	public String toString() {
		// Review Tyler
		String result = "[";
		LinearNode<E> current = front;
		while (current != null) {
			result += current.getElement();
			current = current.getNext();
			if (current != null) {
				result += ", ";
			}
		}
		return result += "]";
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
		private boolean didNext;

		/** Creates a new iterator for the list */
		public SLLIterator() {
			previous = null;
			current = null;
			next = front;
			iterModCount = modCount;
			didNext = false;
		}

		@Override
		public boolean hasNext() {
			// TODO Kelsi
		}

		@Override
		public E next() {
			// TODO Tyra

			didNext = true; // Allow remove
		}

		@Override
		public void remove() { // Colin
			if (!didNext) { throw new ConcurrentModificationException(); }
			previous.setNext(next);
			current = previous; // GC old current
			didNext = false; // disallow remove until next, next()
			iterModCount++;
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
