package mamarantearaujo.hw4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import jdk.nashorn.internal.parser.Scanner;

public class TestBST {

	public static void main(String[] args) {
		checkConstructor();
		checkCopy();
		checkIdenticalStructure();
		checkMirrorImage();
	}
	
	static void checkCopy() {
		BST<Double> bst = new BST<Double>();
		
		Double[] data = CompareBSTandHeap.generateData(5);
		for (double d : data) {
			bst.insert(d);
		}
		StdOut.println("---");
		bst.preorder();
		StdOut.println("---");
		bst.copy().preorder();
		StdOut.println("---");
	}
	
	static void checkIdenticalStructure() {
		BST<String> bst1 = new BST<String>();
		BST<String> bst2 = new BST<String>();
		
		bst1.insert("E");bst1.insert("R");bst1.insert("A");bst1.insert("C");
		bst2.insert("X");bst2.insert("Z");bst2.insert("D");bst2.insert("E");
		
		StdOut.println(bst1.identicalStructure(bst2));
	}
	
	static void checkMirrorImage() {
		BST<String> bst1 = new BST<String>();
		BST<String> bst2 = new BST<String>();
		
		bst1.insert("E");bst1.insert("A");bst1.insert("R");bst1.insert("D");bst1.insert("T");bst1.insert("B");
		bst2.insert("M");bst2.insert("L");bst2.insert("T");bst2.insert("A");bst2.insert("O");bst2.insert("S");
	
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
