package word;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Word implements Comparable<Word>,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7523764206614450402L;
	
	private String word;
	private HashMap<String,ArrayList<Integer>> fileToLine = new HashMap<>();
	
	public Word(String word,HashMap<String,ArrayList<Integer>> fileToLine  ) {
		this.word=word;
		this.setFileToLine(fileToLine);
	}
	public Word(String word) {
		this.word=word;
		
	}
	@Override
	public int compareTo(Word o) {
		return this.word.compareTo(o.word);
	}
	public HashMap<String,ArrayList<Integer>> getFileToLine() {
		return fileToLine;
	}
	public void setFileToLine(HashMap<String,ArrayList<Integer>> fileToLine) {
		this.fileToLine = fileToLine;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
}
