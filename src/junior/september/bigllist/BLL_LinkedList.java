package junior.september.bigllist;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import junior.global.linkedlist.Node;

public class BLL_LinkedList<E extends Comparable<E>> implements Iterable<E> {
	
	private class BLL_LinkedListIterator<X> implements Iterator<X> {
		private Node<X> current;
		public BLL_LinkedListIterator(Node<X> head) { this.current = head; }
		
		@Override public boolean hasNext() { return current != null; }
		@Override public X next() { X data = current.fetchItem(); current = current.fetchNext(); return data; }
	}
	
	private Node<E> root;
	
	public BLL_LinkedList() { this.root = null; }
	
	public Node<E> fetchRoot() { return root; }
	private void setRoot(Node<E> node) { root = node; }
	
	public boolean addFront(Node<E> node) {
		if(fetchRoot() == null) { 
			setRoot(node); 
		} else {
			// Root is no longer becoming root, gaining the previous node of the incoming node
			// The incoming node will now have a next, being root.
			fetchRoot().setPrev(node); 
			node.setNext(root);
		
			// Root is now set to the incoming node.
			setRoot(node); 
		}
		return true;
	}
	
	public boolean addLast(Node<E> node) {
		if(fetchRoot() == null) setRoot(node); else {
			Node<E> last = fetchLast(); 
			if(last == null) return false;
			
			last.setNext(node);
			node.setPrev(last);
		}
		return true;
	}
	
	public boolean add(Node<E> node, int index) {
		if(fetchRoot() == null) setRoot(node); else {
			Node<E> last = fetch(index);
			if(last == null) return false;
			Node<E> currentNext = last.fetchNext();
			
			last.setNext(node);
			node.setPrev(last);
			
			node.setNext(currentNext);
			currentNext.setPrev(node);
		}
		return true;
	}
	
	public boolean isThere(Node<E> node) { return fetchList().contains(node); }
	public boolean isThere(E item) { return fetchList().contains(convertToNode(item)); }
	
	public Node<E> fetchLast() { 
		List<Node<E>> list = fetchList();
		if(list == null) return null;
		return list.get(list.size()-1);
	}
	
	/** @deprecated Use {@link #fetchRoot()} instead */
	@Deprecated public Node<E> fetchFirst() { return fetchRoot(); }
	
	public Node<E> fetch(int index) throws IndexOutOfBoundsException {
		if(fetchList() == null) return null;
		List<Node<E>> list = fetchList();
		if(index >= list.size()) 
			throw new IndexOutOfBoundsException("index cannot exceed size of list [" + list.size() + "]");
		
		return list.get(index);
	}
	
	public Node<E> removeFirst() { 
		Node<E> remove = fetchRoot();
		setRoot(remove.fetchNext());
		root.setPrev(null);
		return remove;
	}
	
	public Node<E> removeLast() { 
		Node<E> remove = fetchLast();
		remove.fetchPrev().setNext(null);
		return remove;
	}
	
	public Node<E> remove(int index) {
		Node<E> remove = fetch(index);
		if(remove == null) return null;
		Node<E> prev = remove.fetchPrev(), next = remove.fetchNext();
		prev.setNext(next); next.setPrev(prev); return remove;
	}
	
	@SuppressWarnings("unchecked")
	public void sort() {
		if(root == null) return;
		Node<E> root2 = null;
		
		if(!(root.fetchItem() instanceof Number)) return;
		
		List<Node<E>> list = fetchList();
		list.sort((Comparator<? super Node<E>>) new Comparator<E>() {
			@Override public int compare(E o1, E o2) { 
				return o1.compareTo(o2);
			}
		});
		
		int slot = 0;
		Node<E> current = new Node<E>(list.get(slot).fetchItem());
		Node<E> last = null;
		while(current != null) {
			try {
				if(root2 == null) {
					root2 = current;
				} else {
					last.setNext(current);
					current.setPrev(last);
				}
			
				last = current;
				current = new Node<E>(list.get(slot++).fetchItem());
			} catch(IndexOutOfBoundsException e) {
				current = null;
			}
		}
		
		setRoot(root2);
	}
	
	public void clear() { setRoot(null); }
	public int size() { return fetchList().size(); }
	public boolean isEmpty() { return (fetchList() == null || root == null); }
	
	@SuppressWarnings("deprecation")
	public void printList() {
		if(fetchList() == null) { System.err.println("List returned null, either root is null or is not actually root."); return; }
		
		System.out.print("[");
		for(Node<E> node : fetchRoot().fetchAllNodes()) System.out.print(node.toString() + ", ");
		System.out.print("]");
	}
	
	@SuppressWarnings("deprecation")
	public void printListReverse() {
		List<Node<E>> list = fetchListReverse();
		if(list == null) { System.err.println("List returned null, either root is null or is not actually root."); return; }
		
		System.out.print("[");
		for(Node<E> node : fetchRoot().fetchAllNodes()) System.out.print(node.toString() + ", ");
		System.out.print("]");
	}
	
	@SuppressWarnings("deprecation") public List<Node<E>> fetchList() { if(fetchRoot() != null && fetchRoot().isRoot()) return root.fetchAllNodes(); else return null; }
	@SuppressWarnings("deprecation") public List<Node<E>> fetchListReverse() { if(fetchRoot() != null && fetchRoot().isRoot()) { List<Node<E>> temp = fetchList(); temp.sort(Collections.reverseOrder()); return temp; } else return null; }
	@Override public Iterator<E> iterator() { return new BLL_LinkedListIterator<E>(root); }
	
	public Node<E> convertToNode(E item) { return new Node<E>(item); }
	
	// Number specific [region]
	public int num_CountLess10() {
		if(root == null || !(root.fetchItem() instanceof Number)) return -1;
		
		int count = 0;
		for(Node<E> node : fetchList()) if(((Number) node.fetchItem()).intValue() < 10) count++;
		return count;
	}
	
	public int num_deleteValue58() {
		if(root == null || !(root.fetchItem() instanceof Number)) return -1;
		
		int count = 0;
		for(Node<E> node : fetchList()) if(((Number) node.fetchItem()).intValue() == 58) {
			count++;
			node.deleteNode();
		}
		return count;
	}
	
	public boolean num_checkForNum(int value) {
		if(root == null || !(root.fetchItem() instanceof Number)) return false;
		
		for(Node<E> node : fetchList()) if(((Number) node.fetchItem()).intValue() == value) return true;
		return false;
	}
	
	public int num_getNum(int position) {
		if(root == null || !(root.fetchItem() instanceof Number)) return -1;
		
		try { return ((Number) fetch(position).fetchItem()).intValue(); } 
		catch(IndexOutOfBoundsException e) { return -1; }
	}
	
	public Double num_average() {
		if(root == null || !(root.fetchItem() instanceof Number)) return null;
		
		double count = 0;
		for(Node<E> node : fetchList()) count+=((Number) node.fetchItem()).intValue();
		return count / size();
	}
	
	public Integer num_min() {
		if(root == null || !(root.fetchItem() instanceof Number)) return null;
		
		int min = Integer.MAX_VALUE;
		for(Node<E> node : fetchList()) min = Math.min(min, ((Number) node.fetchItem()).intValue());
		return min;
	}
	
	public Integer num_max() {
		if(root == null || !(root.fetchItem() instanceof Number)) return null;
		
		int min = Integer.MIN_VALUE;
		for(Node<E> node : fetchList()) min = Math.max(min, ((Number) node.fetchItem()).intValue());
		return min;
	}
	
	public int num_findSlotMaxFirst() {
		if(root == null || !(root.fetchItem() instanceof Number)) return -1;
		
		List<Node<E>> list = fetchList();
		int max = num_max();
		for(int i=0; i<list.size(); i++) if(((Number) list.get(i).fetchItem()).intValue() == max) return i;
		return -1;
	}
	
	public int num_findSlotMaxLast() {
		if(root == null || !(root.fetchItem() instanceof Number)) return -1;
		
		List<Node<E>> list = fetchList();
		int max = num_max();
		int slot = -1;
		
		for(int i=0; i<list.size(); i++) 
			if(((Number) list.get(i).fetchItem()).intValue() == max)
				slot = i;
		
		return slot;
	}
	
	public int num_getEvenCount() {
		if(root == null || !(root.fetchItem() instanceof Number)) return -1;
		
		List<Node<E>> list = fetchList();
		int count = 0;
		
		for(int i=0; i<list.size(); i++) 
			if(((Number) list.get(i).fetchItem()).intValue() % 2 == 0)
				count++;
		
		return count;
	}
	
	public void num_killOdds() {
		if(root == null || !(root.fetchItem() instanceof Number)) return;
		
		List<Node<E>> list = fetchList();
		for(int i=0; i<list.size(); i++) 
			if(((Number) list.get(i).fetchItem()).intValue() % 2 == 1)
				list.get(i).deleteNode();
	}
	// Number specific [end region]
}