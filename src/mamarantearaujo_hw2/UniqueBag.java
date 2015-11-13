package mamarantearaujo_hw2;

/**
 * This data type offers Bag-like behavior with the added constraint that all elements
 * stored are guaranteed to be unique within the bag, based on the equals() method.
 *
 * In all of the performance specifications, N refers to the number of items in the 
 * UniqueBag.
 * 
 * Once you complete this implementation, you will need to provide empirical evidence 
 * to support the performance specifications of each method.
 * 
 * CHANGE LOG:
 * 1. Added logic that each Item must extend Comparable<Item>. This means that you can
 *    fully compare items with each other
 *    
 * 2. Added default constructor so you can create an empty bag more easily.
 * 
 * 3. Cleaned up the description of the Node inner class, which doesn't need generics
 *    because the outer class provides this.
 *    
 * 4. Added remove method, which I somehow had forgotten to include in the template. You need
 *    to implement this (as a complement to add).
 * 
 * 
 * Final Comments:
 * 
 * 1. To receive maximum points you are to implement all methods without using the existing
 *    java.util.* classes that would otherwise be useful. The point of this programming exercise
 *    is to gain experience in working with linked-list structures where the focus is on achieving
 *    the highest performance of the code.
 * 
 * @param <Item>
 */
public class UniqueBag<Item extends Comparable<Item>> {

	Node first = null;
	int N = 0;

	/** You must use this Node class as part of a LinkedList to store the UniqueBag items. */
	class Node {
		private Item   item;
		private Node   next;

		Node() {
			this.item = null;
			this.next = null;
		}

		Node(Item item, Node next) {
			this.item = item;
			this.next = next;
		}
	}

	/** Default constructor to create an empty initial bag. */
	public UniqueBag() {
		this.first = null;
		this.N = 0;
	}

	/**
	 * Initialize the bag to contain the unique elements found in the initial list.
	 * 
	 * Performance must be dependent of the number of items in initial, or ~ N.
	 */
	public UniqueBag(Item[] initial) {
		//the bag is initially empty
		for(Item item: initial)
			this.add(item);
	}

	/** 
	 * Return the number of items in the UniqueBag.
	 * 
	 * Performance must be independent of the number of items in the UniqueBag, or ~ 1.
	 */
	public int size() {
		return this.N;
	}

	/** 
	 * Determines equality with another UniqueBag objects.
	 * 
	 * Performance must be linearly dependent on the number of items in the UniqueBag, or ~ N.
	 */
	public boolean identical (UniqueBag<Item> other) {
		if(this.size() == other.size())
		{
			Node thisNode = this.first;
			Node otherNode = other.first;
			for(int i = 0; i < N; i++) {
				if(!thisNode.item.equals(otherNode.item))
					return false;
				thisNode = thisNode.next;
				otherNode = otherNode.next;
			}
			return true;
		}else
			return false;
	}

	/** 
	 * Return an array that contains the items from the UniqueBag.
	 * 
	 * Performance must be linearly dependent on the number of items in the UniqueBag, or ~ N.
	 */
	public Item[] toArray() {
		Item[] array = (Item[]) new Comparable[N];

		Node node = this.first;

		for	(int i = 0; i < N; i++)
		{
			array[i] = node.item;
			node = node.next;
		}
		return array;
	}

	/** 
	 * Add an item to the UniqueBag; return false if already contained.
	 * 
	 * Performance can be linearly dependent on the number of items in the UniqueBag, or ~ N.
	 */
	public boolean add (Item it) {

		if(this.size() == 0){
			this.first = new Node(it, null);
		}else { //size()>=1
			Node node = first;
			Node lastnode = first;

			int i = 0;
			for(i = 0; i < this.N; i++)
			{
				if(node.item.equals(it)){
					return false; //unique
				}
				if(it.compareTo(node.item) > 0) {//it > node.item
					lastnode = node;
					node = node.next;
				}else {//it < node.item
					if(i == 0){
						Node newNode = new Node(it,this.first);
						this.first = newNode;
					}else{
						lastnode.next = new Node(it,node);
					}
					break;
				}
			}

			if(i == this.N) {
				lastnode.next = new Node(it, null);
			}
		}

		this.N++;

		return true;
	}

	/** 
	 * Remove an item to the UniqueBag; return false if not contained within, true on success.
	 * 
	 * Performance can be linearly dependent on the number of items in the UniqueBag, or ~ N.
	 */
	public boolean remove (Item it){
		Node node = this.first;
		Node lastnode = this.first;
		for(int i = 0; i < this.N; i++)
		{

			if(node.item.equals(it)) {
				if(i == 0) {
					Node newFirst = this.first.next;
					this.first = newFirst;
				}else
					lastnode.next = node.next;

				N--;
				return true;
			}else if (it.compareTo(node.item) < 0) //it < node.item
				return false;
			else {//it > node.item
				lastnode = node;
				node = node.next;
			}
		}
		return false;
	}

	/** 
	 * Determine whether the item is contained by the UniqueBag.
	 * 
	 * Performance must be linearly dependent on the number of items in the UniqueBag, or ~ N.
	 */
	public boolean contains(Item it) {
		Node node = this.first;

		for(int i = 0; i < this.N; i++)
		{
			if(node.item.equals(it)){
				return true;
			}else if (node.item.compareTo(it) > 0){ //it < node.item
				return false;
			}else //it < node.item
				node = node.next;
		}

		return false;
	}
	/** 
	 * Return a UniqueBag which represents intersection with existing UniqueBag.
	 * 
	 * Performance must be linearly dependent on the number of items in both UniqueBag 
	 * objects, or in otherwords ~ M + N where M is the number of items in other and N
	 * is the number of items in this UniqueBag.
	 */
	public UniqueBag<Item> intersects(UniqueBag<Item> other) {
		UniqueBag<Item> newBag= new UniqueBag<Item>();

		int min_size = Math.min(this.size(), other.size());

		Node this_node = this.first;
		Node other_node = other.first;

		for	(int i = 0; i < min_size; i++) {
			if(this_node.item.equals(other_node.item))
				newBag.add(this_node.item);
			else if(this_node.item.compareTo(other_node.item) < 0) //this_item < other_item
				this_node = this_node.next;
			else //this_item > other_item
				other_node = other_node.next;
		}

		return newBag;
	}

	/** 
	 * Return a UniqueBag which represents union with existing UniqueBag.
	 * 
	 * Performance must be linearly dependent on the number of items in both UniqueBag 
	 * objects, or in otherwords ~ M + N where M is the number of items in other and N
	 * is the number of items in this UniqueBag.
	 */
	public UniqueBag<Item> union(UniqueBag<Item> other) {
		UniqueBag<Item> newBag= new UniqueBag<Item>();

		Node this_node = this.first;
		Node other_node = other.first;

		while(this_node!=null || other_node != null) {

			if(this_node == null) {
				newBag.add(other_node.item);
				other_node = other_node.next;
			}else if(other_node == null) {
				newBag.add(this_node.item);
				this_node= this_node.next;
			}else if(this_node.item.equals(other_node.item)) {
				newBag.add(this_node.item);
				this_node = this_node.next;
				other_node = other_node.next;
			}else{ 
				if(this_node.item.compareTo(other_node.item) < 0) {//this_item < other_item
					newBag.add(this_node.item);
					this_node = this_node.next;
				}else {//this_item > other_item
					newBag.add(other_node.item);
					other_node = other_node.next;
				}
			}
		}



		return newBag;
	}


}
