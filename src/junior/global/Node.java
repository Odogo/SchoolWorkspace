package junior.global;

import java.util.ArrayList;
import java.util.List;

/**
 * The start of a Linked List chain
 * @param <T> the object to store
 */
public class Node<T> implements Comparable<T> {
	
	private Node<T> prev;
	private T item;
	private Node<T> next;
	
	/**
	 * Creates a blank item, nothing before or after it.
	 * @param item the item itself
	 */
	public Node(T item) {
		this.item = item;
		
		prev = null;
		next = null;
	}
	
	/**
	 * Creates a new item in between two nodes
	 * @param prev the node before
	 * @param item the item itself
	 * @param next the node in front of it
	 */
	public Node(Node<T> prev, T item, Node<T> next) {
		this.prev = prev;
		this.item = item;
		this.next = next;
	}
	
	public Node<T> fetchPrev() { return prev; }
	public T fetchItem() { return item; }
	public Node<T> fetchNext() { return next; }
	
	public void setPrev(Node<T> item) { this.prev = item; }
	public void setNext(Node<T> item) { this.next = item; }
	
	public boolean isRoot() { return (fetchPrev() == null); }
    
	/**
	 * Fetches all of the nodes inside of the linked list.
	 * Requires node to be root.
	 * @returns a list of all nodes from root.
	 */
	public List<Node<T>> fetchAllNodes() {
		if(!isRoot()) return null;
		List<Node<T>> list = new ArrayList<Node<T>>();
		
		Node<T> current = this;
		while(current != null) { list.add(current); current = current.fetchNext(); }
		
		return list;
	}
	
	/**
	 * Literally prints out all nodes from root.
	 * Requires node to be root.
	 */
	public void printAllNodes() {
		if(!isRoot()) return;
		
		Node<T> current = this;
		while(current != null) {
			System.out.println(current.fetchItem().toString());
			current = current.fetchNext();
		}
	}
	
	/**
	 * Fetches the node at a specific index in the "LinkedList"
	 * Requires node to be root
	 * @param index the index to look for
	 * @return the node with at the given index otherwise null
	 * @throws IndexOutOfBoundsException when given index is higher than node count
	 */
	public Node<T> fromRootFetchItem(int index) throws IndexOutOfBoundsException {
		if(!isRoot()) return null;
		if(index == 0) return this;
		
		int i = 0;
		Node<T> temp = this.fetchNext();
		while(temp != null) {
			if(i==index) return temp;
			temp = this.fetchNext();
		}
		throw new IndexOutOfBoundsException(index);
	}
	
	/**
	 * Fetches the node that contains a given item
	 * Requires node to be root
	 * @param item the item to look for
	 * @return the node with the given item otherwise null
	 */
	public Node<T> fromRootFetchItem(T item) {
		if(!isRoot()) return null;
		
		Node<T> temp = this.fetchNext();
		while(temp != null) {
			if(temp.fetchItem().equals(item)) return temp;
			temp = this.fetchNext();
		}
		return null;
	}
	
	/**
	 * Deletes the entire node, setting the previous node to point to next, and vis-versa.
	 * @return the item stored in this node
	 */
	public T deleteNode() {
		Node<T> _prevNode = prev, _nextNode = next;
		
		if(_prevNode != null) _prevNode.setNext(next);
		if(_nextNode != null) _nextNode.setPrev(prev);
		return item;
	}
	
	@Override public String toString() { return item.toString(); }
	@Override public int compareTo(T o) { return Integer.compare(item.hashCode(), o.hashCode()); }
}
