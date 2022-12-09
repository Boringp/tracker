package bst;

public class TreeException extends RuntimeException {
	static String errorMessage="Empty tree";
	public TreeException() {
        super(errorMessage);
    }
}
