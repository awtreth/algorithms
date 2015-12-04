package mamarantearaujo_hw4;

import edu.princeton.cs.algs4.StdOut;

// Several questions in HW4 assume you start with the following BST.

public class BST<Key extends Comparable<Key>> {

	Node root;               // root of the tree

	class Node {
		Key    key;        
		Node   left, right;  // left and right subtrees

		public Node(Key key) {
			this.key = key;
		}

		/** Helpful debugging method. */
		public String toString() { return "[" + key + "]"; }
	}

	/** Default constructor still used for empty BST. Leave as is. */
	public BST () { }
	
	/** 
	 * Given an ordered array of keys in ascending order of length 2^k-1, construct a perfectly 
	 * balanced BST. 
	 *  
	 * @param keys    keys are in ascending order
	 */
	public BST(Key[] keys) {
		insert2(keys, 0,keys.length-1);
	}
	
	private void insert2(Key[] keys, int lo, int hi) {
		
		if(hi<lo) return;
		
    	int mid = (lo+hi)/2;
		
		this.insert(keys[mid]);
		insert2(keys, lo, mid-1);
		insert2(keys, mid+1, hi);
	}
	
	
	public int height() { 
		return height(this.root);
	}
	
	public int height(Node n) {
		if(n == null)
			return -1;
		else
			return 1 + Math.max(height(n.left), height(n.right)); //generic version
			//return 1 + height(n.left);//just for the specified BSTs
	}
	
	
	public BST<Key> copy () {
		BST<Key> newBST = new BST<Key>();
		newBST.root = copy(root);
		return newBST;
	}
	private	Node copy (Node	n) {
		
		if(n==null)
			return null;
		
		Node newNode = new Node(n.key);
		newNode.left = copy(n.left);
		newNode.right = copy(n.right);
		
		return newNode;
	}
	
	public	boolean	identicalStructure(BST<Key> bst) {
		return identicalStructure(this.root, bst.root);
	}
	
	private	boolean	identicalStructure (Node one, Node two) {
		if(one==null || two==null) {
			if(one==two) return true;
			else return false;
		}
			
		return identicalStructure(one.left, two.left) && identicalStructure(one.right, two.right);
	}
	
	public boolean mirrorImage(BST<Key> bst) {
		return mirrorImage(this.root, bst.root);
	}
	
	private	boolean	mirrorImage (Node one, Node two) {
		if(one==null || two==null) {
			if(one==two) return true;
			else return false;
		}
			
		return mirrorImage(one.left, two.right) && mirrorImage(one.right, two.left);
	}
	
	public boolean isEmpty() { return root == null; }

	// One-line method for containment. 
	public boolean contains(Key key) { return get(root, key); }

	private boolean get(Node parent, Key key) {
		if (parent == null) return false;

		int cmp = key.compareTo(parent.key);

		if      (cmp < 0) return get(parent.left, key);
		else if (cmp > 0) return get(parent.right, key);
		else              return true;
	}

	/** Insert key into BST. */
	public void insert(Key key) {
		root = insert(root, key);
	}

	private Node insert(Node parent, Key key) {
		if (parent == null) return new Node(key);

		int cmp = key.compareTo(parent.key);
		if (cmp <= 0) {
			parent.left  = insert(parent.left,  key);
		} else {
			parent.right = insert(parent.right, key);
		}

		return parent;
	}

	public Key min() { return min(root).key; }

	private Node min (Node parent) {
		if (parent.left == null) { return parent; }
		return min(parent.left);
	}

	public Key nonRecursiveMin() {
		Node n = root;

		while (n.left != null) {
			n = n.left;
		}

		return n.key;
	}

	public Key floor(Key key) {
		Node rc = floor(root, key);
		if (rc == null) return null;
		return rc.key;
	} 

	private Node floor(Node parent, Key key) {
		if (parent == null) return null;

		int cmp = key.compareTo(parent.key);
		if (cmp == 0) return parent;                   // found? Then this is floor
		if (cmp <  0) return floor(parent.left, key);  // smaller? must be in left subtree

		Node t = floor(parent.right, key);             // greater? we might be floor, but
		if (t != null) return t;                       // only if 
		else return parent; 
	} 

	// traversal ideas
	// invoke an inorder traversal of the tree
	public void inorder() { inorder(root); }
	private void inorder(Node n) {
		if (n != null) {
			inorder (n.left);
			StdOut.println (n.key);
			inorder (n.right);
		}
	}

	// traversal ideas
	// invoke a pre-order traversal of the tree
	public void preorder() { preorder(root); }
	private void preorder(Node n) {
		if (n != null) {
			StdOut.println (n.key);

			preorder (n.left);
			preorder (n.right);
		}
	}

	/** Implement method to return Value when removing largest element. */
	public void deleteMin() {
		if (root != null) { root = deleteMin(root);	}
	}

	Node deleteMin(Node parent) {
		if (parent.left == null) {
			return parent.right;
		}

		parent.left = deleteMin(parent.left);
		return parent;
	}

	// new methods for discussion
	public Key max() { return max(root).key; }

	private Node max (Node parent) {
		if (parent.right == null) { return parent; }
		return max(parent.right);
	}

	int inspectedElementCount = 0;
	
	public void	deleteMax() {
		//inspectedElementCount = 0;
		inspectedElementCount++;
		if (root != null) { root = deleteMax(root);	}
	}
	
	Node deleteMax(Node parent) { 
		inspectedElementCount++;
		if (parent.right == null) {
			return parent.left;
		}

		parent.right = deleteMax(parent.right);
		return parent;
	}
	
	
	
	public void delete(Key key) { root = delete(root, key); }

	private Node delete(Node parent, Key key) {
		if (parent == null) return null;

		// recurse until you find parent with this key.
		int cmp = key.compareTo(parent.key);
		if      (cmp < 0) parent.left  = delete(parent.left,  key);
		else if (cmp > 0) parent.right = delete(parent.right, key);
		else { 
			// handle easy cases first:
			if (parent.right == null) return parent.left;
			if (parent.left  == null) return parent.right;

			// has two children: Plan on returning min of our right child
			Node old = parent;
			parent = min(old.right);     // will eventually be "new parent"

			// Note this is a simpler case: Delete min from right subtree
			// and DON'T FORGET to stitch back in the original left child
			parent.right = deleteMin(old.right);   
			parent.left = old.left;
		} 

		// as recursions unwind, pass back potential new parent
		return parent;
	}

	

}
