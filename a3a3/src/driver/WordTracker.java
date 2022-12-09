package driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import bst.BSTree;
import bst.Iterator;
import word.Word;

public class WordTracker {
public static void main(String[] args) {
	if (args.length<2) {
		printHelp();
    return;
	}
	String inputFileName=args[0];
	String commd = args[1].toLowerCase();
	String indctor = null;
	if(args.length==4) {
		indctor = args[2].toLowerCase();
		
	}
	
	if(!commd.equals("-pf")&&!commd.equals("-po")&&!commd.equals("-pl")) {
		
		printHelp();
		return ;
	}
	
	BSTree<Word> trackList = new BSTree<>();
	ArrayList<Word> w = new ArrayList<>();
	int lineNumber = 1;
	
		try {
			FileInputStream fileRead= new FileInputStream("repository.ser");
			try {
				ObjectInputStream in = new ObjectInputStream(fileRead);
				 
				 w=(ArrayList)in.readObject();  
				
				in.close(); 
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				;
			}
			fileRead.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			;
		}
		for (Word a:w) {
			trackList.add(a);
		}

	
	try {
	      File myObj = new File(inputFileName);
	      String fileName=myObj.getName();
	      Scanner myReader = new Scanner(myObj);
	     
	      while (myReader.hasNextLine()) {
	    	  
	      String data = myReader.nextLine().toLowerCase();
	      String[] b =data.split("[^a-zA-Z]");
	      for (String a :b) {
	    	HashMap<String,ArrayList<Integer>> twoMap=new HashMap<>();
	    	
	    	  if(!a.isEmpty()&&(a!="")) {
	    		  if(!trackList.isEmpty()) {
	    			  if(!trackList.contains(new Word(a))) {
	    	    		  twoMap.put(fileName, new ArrayList<>(lineNumber));
	    	    		  trackList.add(new Word(a,twoMap));
	    	    	  }else {
	    	    		  Word toAdd= trackList.search(new Word(a)).getElement();
	    	    		  if (toAdd.getFileToLine().containsKey(fileName)){
	    	    			  if(!toAdd.getFileToLine().get(fileName).contains(lineNumber)) {
	    	    				  toAdd.getFileToLine().get(fileName).add(lineNumber);
	    	    			  }
	    	    			  
	    	    			  
	    	    			  
	    	    		  }else {
	    	    			  toAdd.getFileToLine().put(fileName, new ArrayList<>(lineNumber));
	    	    		  }
	    	    	  }
	    		  }else {
	    			  twoMap.put(fileName, new ArrayList<>(lineNumber));
    	    		  trackList.add(new Word(a,twoMap));
	    		  }
	    	    	 
	    	      }
	    	      }
	      lineNumber++;
	    	      }
	      
	      
	      myReader.close();
	      
	      if(commd.equals("-pf")) {
	    	Iterator<Word> it = trackList.postorderIterator();
	  		while(it.hasNext()) {
	  			Word curr=it.next();
	  			System.out.printf("%s appears in File:",curr.getWord());
	  			for (String key:curr.getFileToLine().keySet()) {
	  				System.out.print(key+", ");
	  			}
	  			System.out.println();
	  	}
	  		}
	      else if(commd.equals("-pl")) {
	    	  Iterator<Word> it = trackList.postorderIterator();
		  		while(it.hasNext()) {
		  			Word curr=it.next();
		  			System.out.printf("%s appears in File:",curr.getWord());
		  			for (String key:curr.getFileToLine().keySet()) {
		  				System.out.print(key+"Line:");
		  				for (int i:curr.getFileToLine().get(key)) {
		  					System.out.print(i+",");
		  				}
		  				System.out.println();
		  			}
		  			
		  	}
		  		
	      }else if(commd.equals("-po")) {
	    	  Iterator<Word> it = trackList.postorderIterator();
		  		while(it.hasNext()) {
		  			Word curr=it.next();
		  			System.out.printf("%s appears in File:",curr.getWord());
		  			for (String key:curr.getFileToLine().keySet()) {
		  				System.out.print(key+"Line:");
		  				for (int i:curr.getFileToLine().get(key)) {
		  					System.out.print(i+",");
		  					
		  					
		  				}
		  				System.out.println();
		  				System.out.println("times of occurrence: "+curr.getFileToLine().get(key).size());
		  			}
		  			
		  	}
	      }
	      
	      
	      
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	
	
	
	
	
	
		
		try {
			FileOutputStream fileOut= new FileOutputStream("repository.ser");
			ArrayList<Word> wordList=new ArrayList<>();
			Iterator<Word> it = trackList.preorderIterator();
			while(it.hasNext()) {
				
				wordList.add(it.next());
				System.out.println(wordList.get(wordList.size()-1).getWord());
			}
			try {
				ObjectOutputStream out=new ObjectOutputStream(fileOut);
				out.writeObject(wordList);
				out.close();
	           
				fileOut.close();  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if(args.length==4) {
			if(indctor.equals("-f")) {
				String outPath=args[3];
				FileWriter fileWriter;
				try {
					fileWriter = new FileWriter(outPath);
					PrintWriter printWriter = new PrintWriter(fileWriter);
					  
				      if(commd.equals("-pf")) {
				    	Iterator<Word> it = trackList.postorderIterator();
				  		while(it.hasNext()) {
				  			Word curr=it.next();
				  			 printWriter.printf("%s appears in File:",curr.getWord());
				  			for (String key:curr.getFileToLine().keySet()) {
				  				 printWriter.print(key+", ");
				  			}
				  			 printWriter.println();
				  	}
				  		}
				      else if(commd.equals("-pl")) {
				    	  Iterator<Word> it = trackList.postorderIterator();
					  		while(it.hasNext()) {
					  			Word curr=it.next();
					  			 printWriter.printf("%s appears in File:",curr.getWord());
					  			for (String key:curr.getFileToLine().keySet()) {
					  				 printWriter.print(key+"Line:");
					  				for (int i:curr.getFileToLine().get(key)) {
					  					 printWriter.print(i+",");
					  				}
					  				 printWriter.println();
					  			}
					  			
					  	}
					  		
				      }else if(commd.equals("-po")) {
				    	  Iterator<Word> it = trackList.postorderIterator();
					  		while(it.hasNext()) {
					  			Word curr=it.next();
					  			 printWriter.printf("%s appears in File:",curr.getWord());
					  			for (String key:curr.getFileToLine().keySet()) {
					  				 printWriter.print(key+"Line:");
					  				for (int i:curr.getFileToLine().get(key)) {
					  					 printWriter.print(i+",");
					  					
					  					
					  				}
					  				 printWriter.println();
					  				 printWriter.println("times of occurrence: "+curr.getFileToLine().get(key).size());
					  			}
					  			
					  	}
				      }
				
					 printWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
				
			}
		}
	
	
}
public static void printHelp() {
	System.out.println("Arguments are wrong: Examlpe should be:java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>] ");
}
}
