import java.io.*;
import java.util.*;

public class SingleThread {
  
  public static void main(String[] args) throws IOException {
    long start = System.currentTimeMillis();
    Scanner sc = new Scanner(new File("DeclarationIndependence.txt"));
    ArrayList<String> wordList = new ArrayList<String>();

    while (sc.hasNextLine()) {
      wordList.add(sc.next());
    }
    File file = new File ("backwards.txt");
    FileWriter writer = new FileWriter(file);
    
    while(!wordList.isEmpty()){
      
      writer.write(wordList.get(wordList.size()-1));
      wordList.remove(wordList.size()-1);
    }
    writer.close();
    long end = System.currentTimeMillis();
    System.out.println("Time = " + (end - start) );
  }
}
