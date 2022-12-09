package bst;

import java.util.ArrayList;
import java.util.NoSuchElementException;



public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {

	private BSTreeNode<E> root;
	int size=0;
	public BSTree() {
		root=null;
	}
	@Override
	public BSTreeNode<E> getRoot() throws TreeException {
		if(root==null)
			throw new TreeException(); 
	
		return root;
	}

	@Override
	public int getHeight() {
	return getHeight(root);
	}
	
	private	int getHeight(BSTreeNode n) {
		if(n==null) {
			return 0;
		}
		
		int leftHeight=getHeight(n.getLeft());
		int rightHeight=getHeight(n.getRight());
		return Math.max(leftHeight,rightHeight)+1;
		
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return root==null;
	}

	@Override
	public void clear() {
		root=null;
		this.size=0;
		
	}

	@Override
	public boolean contains(E entry) throws TreeException {
	BSTreeNode curr;
	curr=root;
	boolean find=false;
	if(root==null)
		throw new TreeException(); 

	while(curr!=null) {
		if(curr.noChild()&&entry.compareTo((E) curr.getElement())!=0) {
			break;
		}else {
			if(entry.compareTo((E) curr.getElement())<0) {
				curr=curr.getLeft();
			}
			else if (entry.compareTo((E) curr.getElement())>0) {
				curr=curr.getRight();
			}
			else if (entry.compareTo((E) curr.getElement())==0) {
				find=true;
				break;
			}
		}
	}
	return find;	
	}

	@Override
	public BSTreeNode<E> search(E entry) throws TreeException {
		BSTreeNode curr;
		curr=root;
		BSTreeNode<E> find=null;
		if(root==null)
			throw new TreeException(); 
	
		while(true) {
			if(curr.noChild()&&entry.compareTo((E) curr.getElement())!=0) {
				break;
			}else {
				if(entry.compareTo((E) curr.getElement())<0) {
					curr=curr.getLeft();
				}
				else if (entry.compareTo((E) curr.getElement())>0) {
					curr=curr.getRight();
				}
				else if (entry.compareTo((E) curr.getElement())==0) {
					find=curr;
					break;
				}
			}
		}
		return find;	
	}

	@Override
	public boolean add(E newEntry) throws NullPointerException {
		if (newEntry==null)
			throw new NullPointerException();
		if (isEmpty()) {
			root=new BSTreeNode(newEntry);
		}
		else {
			BSTreeNode curr;
			curr=root;
			
			while(true) {
				if(newEntry.compareTo((E) curr.getElement())<0) {
					if(curr.getLeft()!=null) {
						curr=curr.getLeft();
					}
					else {
						curr.setLeft(new BSTreeNode(newEntry));
						break;
					}
				}
				else if(newEntry.compareTo((E) curr.getElement())>0) {
					if(curr.getRight()!=null) {
						curr=curr.getRight();
					}
					else {
						curr.setRight(new BSTreeNode(newEntry));
						break;
					}
				}
				else {
					return false;
				}
				
			}
				
		}
		this.size++;
		return true;
	}

	@Override
	public Iterator<E> inorderIterator()  {
		return new inIterator();
	}

	@Override
	public Iterator<E> preorderIterator() {
		return new preIterator();
	

	}
	

	@Override
	public Iterator<E> postorderIterator() {
		// TODO Auto-generated method stub
		return new postIterator();
	}

	class inIterator<E> implements Iterator<E> {
		private BSTreeNode<E> next =(BSTreeNode<E>) getRoot();
		private ArrayList<E> list= new ArrayList();
		int cursor=0;
		public inIterator() {
			inorder(next);
		}
		void inorder(BSTreeNode p) {
			 if (p!=null) {
				inorder(p.getLeft()); 	
				list.add((E) p.getElement());		
				inorder(p.getRight());} 	
				}	
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cursor<list.size();
		}

		@Override
		public E next() throws NoSuchElementException {
			if (cursor>=list.size())
					throw new NoSuchElementException();
			return list.get(cursor++);
			
	}
		}
	
	class preIterator<E> implements Iterator<E> {

		private BSTreeNode<E> next =(BSTreeNode<E>) getRoot();
		private ArrayList<E> list= new ArrayList();
		int cursor=0;
		public preIterator() {
			preorder(next);
		}
		void preorder(BSTreeNode p) {
			 if (p!=null) {
				 list.add((E) p.getElement());
				 preorder(p.getLeft()); 	
						
				preorder(p.getRight());} 	
				}	
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cursor<list.size();
		}

		@Override
		public E next() throws NoSuchElementException {
			if (cursor>=list.size())
				throw new NoSuchElementException();
			return list.get(cursor++);
			
	}
	}
	class postIterator<E> implements Iterator<E> {

		private BSTreeNode<E> next =(BSTreeNode<E>) getRoot();
		private ArrayList<E> list= new ArrayList();
		int cursor=0;
		public postIterator() {
			postorder(next);
		}
		void postorder(BSTreeNode p) {
			 if (p!=null) {
				 
				 postorder(p.getLeft()); 	
						
				 postorder(p.getRight());
				 list.add((E) p.getElement());
				 } 	
			 
				}	
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cursor<list.size();
		}

		@Override
		public E next() throws NoSuchElementException {
			if (cursor>=list.size())
				throw new NoSuchElementException();
			return list.get(cursor++);
			
	}
		
	}
}
