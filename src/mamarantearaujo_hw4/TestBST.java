package mamarantearaujo_hw4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import jdk.nashorn.internal.parser.Scanner;

public class TestBST {

	public static void main(String[] args) {
		//checkConstructor();
		//checkCopy();
		//checkIdenticalStructure();
		//checkMirrorImage();
	}
	
	static void checkCopy() {
		BST<Double> bst = new BST<Double>();
		
		Double[] data = CompareBSTandHeap.generateData(5);
		for (double d : data) {
			bst.insert(d);
		}
		
		bst.preorder();
		StdOut.println("---");
		bst.copy().preorder();
	}
	
	static void checkIdenticalStructure() {
		BST<Integer> bst1 = new BST<Integer>();
		BST<Integer> bst2 = new BST<Integer>();
		
		bst1.insert(4);bst1.insert(5);bst1.insert(6);bst1.insert(1);bst1.insert(7);
		bst2.insert(7);bst2.insert(5);bst2.insert(10);bst2.insert(11);bst2.insert(12);
	
		StdOut.println(bst1.identicalStructure(bst2));
	}
	
	static void checkMirrorImage() {
		BST<Integer> bst1 = new BST<Integer>();
		BST<Integer> bst2 = new BST<Integer>();
		
		bst1.insert(4);bst1.insert(2);bst1.insert(6);bst1.insert(1);
		bst2.insert(7);bst2.insert(8);bst2.insert(4);bst2.insert(9);
	
		StdOut.println(bst1.mirrorImage(bst2));
	}

	static void checkConstructor() {

		for(int k = 2; k <= 10; k++) {
			Integer[] array = new Integer[(int)(Math.pow(2, k)-1)];

			for(int i = 0; i < array.length; i++) array[i] = i+1;

			BST<Integer> bst = new BST<Integer>(array);
			int h = bst.height();

			StdOut.printf("k = %d; h = %d;\n", k, h);
			//bst.inorder();

			if(h != k-1) {
				StdOut.println("Wrong height");
				return;
			}
		}
	}

}
