package junior.global.linkedlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Custom_LinkedList<A> implements Iterable<Node<A>> {

	private Node<A> root;

	// Using to make sure everything is solid, and also a good use for an iterator.
	// As well as saves time when trying to make a for loop
	private List<Node<A>> rawList;

	public Custom_LinkedList(Node<A> root) {
		this.root = root;
		this.rawList = new ArrayList<Node<A>>();

		rawList.set(0, root);
	}

	public Node<A> get(int index) { try { return rawList.get(index); } catch(IndexOutOfBoundsException e) { throw new IndexOutOfBoundsException(e.getMessage()); }}
	public Node<A> getFront() { return root; }
	public Node<A> getLast() { return rawList.get(rawList.size()-1); }

	/** Adds a node to a specific location */
	public void add(int index, Node<A> node) {
		if(index < 0) throw new IndexOutOfBoundsException(index); 
		if(index == 0) addFront(node);
		else if(index >= rawList.size()) addLast(node);

		Node<A> prev = get(index-1);
		Node<A> current = get(index);

		prev.setNext(node);
		current.setPrev(node);

		node.setPrev(prev);
		node.setNext(current);

		rawList.add(index, current);
	}

	/** Effectively makes the incoming node root */
	public void addFront(Node<A> node) {
		node.setNext(root);
		root.setPrev(node);

		root = node;
		rawList.add(0, node);
	}

	/** Adds a new node to the end of the list */
	public void addLast(Node<A> node) {
		Node<A> last = getLast();

		last.setNext(node);
		node.setPrev(last);

		rawList.add(node);
	}

	public void set(int index, Node<A> node) { get(index).setItem(node.fetchItem()); }

	public int size() { return rawList.size(); }
	public boolean contains(Object object) { return rawList.contains(object); }

	/** You cannot directly modify the classes list handler. This returns a direct replica, but will not update if any changes are made.
	 * You must recall this method for <b> ANY UPDATES </b> to the original linked list. */
	public List<Node<A>> fetchRawList() { return new ArrayList<Node<A>>(rawList); }

	@Override public Iterator<Node<A>> iterator() { return rawList.iterator(); }
}
