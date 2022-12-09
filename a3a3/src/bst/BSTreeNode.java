package bst;

public class BSTreeNode<E> {
	private E element;
	private BSTreeNode<E> left, right;

	public BSTreeNode(E elem, BSTreeNode<E> left, BSTreeNode<E> right){
		this.setElement(elem);
		this.setLeft(left);
		this.setRight(right);
		} 
	public BSTreeNode(E elem){
		this.setElement(elem);
		this.setLeft(null);
		this.setRight(null);
		} 
	

	public E getElement() {
		return element;
	}
	public void setElement(E element) {
		this.element = element;
	}
	public BSTreeNode<E> getRight() {
		return right;
	}
	public void setRight(BSTreeNode<E> right) {
		this.right = right;
	}
	public BSTreeNode<E> getLeft() {
		return left;
	}
	public void setLeft(BSTreeNode<E> left) {
		this.left = left;
	}
	public boolean noChild() {
		if(this.getLeft()==null&&this.getRight()==null) {
			return true;
		}
		else {
			return false;
		}
	}
}
