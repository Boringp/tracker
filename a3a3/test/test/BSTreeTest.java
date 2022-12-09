package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bst.BSTree;
import bst.Iterator;

class BSTreeTest {
	private BSTree<String> tree;
	@BeforeEach
	void setUp() throws Exception {
		tree = new BSTree();
	}

	@AfterEach
	void tearDown() throws Exception {
		tree=null;
	}



	@Test
	void testGetRoot() {
		tree.add("a");
		assertEquals( "a", tree.getRoot().getElement());
		assertNull( tree.getRoot().getLeft());
		assertNull( tree.getRoot().getRight());
	}

	@Test
	void testGetHeight() {
		assertEquals( 0, tree.getHeight());
		tree.add("b");
		tree.add("a");
		tree.add("c");
		tree.add("d");
		tree.add("e");
		assertEquals( 4, tree.getHeight());
	}

	@Test
	void testSize() {
		tree.add("b");
		tree.add("a");
		tree.add("c");
		tree.add("d");
		tree.add("e");
		assertEquals( 5, tree.size());
	}

	@Test
	void testIsEmpty() {
		assertTrue(tree.isEmpty());
		tree.add("b");
		assertFalse(tree.isEmpty());
	}

	@Test
	void testClear() {
		tree.add("b");
		tree.add("a");
		tree.add("c");
		tree.clear();
		assertNull( tree.isEmpty());
	}

	@Test
	void testContains() {
		tree.add("b");
		tree.add("a");
		tree.add("c");
		tree.add("d");
		tree.add("e");
		assertTrue(tree.contains("e"));
		assertFalse(tree.contains("f"));
	}

	@Test
	void testSearch() {
		tree.add("b");
		tree.add("a");
		tree.add("c");
		tree.add("d");
		tree.add("e");
		assertEquals( "e", tree.search("e").getElement());
		assertNull(  tree.search("f"));
	}

	@Test
	void testAdd() {
		tree.add("b");
		tree.add("a");
		tree.add("c");
		tree.add("d");
		tree.add("e");
		assertEquals( "b", tree.getRoot().getElement());
		assertEquals( "a", tree.getRoot().getLeft().getElement());
		assertEquals( "c", tree.getRoot().getRight().getElement());
		assertEquals( "d", tree.getRoot().getRight().getRight().getElement());
		assertEquals( "e", tree.getRoot().getRight().getRight().getRight().getElement());
	}

	@Test
	void testInorderIterator() {
	tree.add("b");
	tree.add("a");
	tree.add("c");
	tree.add("d");
	tree.add("e");
	Iterator in = tree.inorderIterator();
	assertEquals( "a", in.next());
	assertEquals( "b", in.next());
	assertEquals( "c", in.next());
	assertEquals( "d", in.next());
	assertEquals( "e", in.next());
	}

	@Test
	void testPreorderIterator() {
		tree.add("b");
		tree.add("a");
		tree.add("c");
		tree.add("d");
		tree.add("e");
		Iterator in = tree.preorderIterator();
		assertEquals( "b", in.next());
		assertEquals( "a", in.next());
		assertEquals( "c", in.next());
		assertEquals( "d", in.next());
		assertEquals( "e", in.next());
	}

	@Test
	void testPostorderIterator() {
		tree.add("b");
		tree.add("a");
		tree.add("c");
		tree.add("d");
		tree.add("e");
		Iterator in = tree.postorderIterator();
		assertEquals( "a", in.next());
		assertEquals( "e", in.next());
		assertEquals( "d", in.next());
		assertEquals( "c", in.next());
		assertEquals( "b", in.next());
	}

}
