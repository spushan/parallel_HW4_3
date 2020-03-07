import java.io.*;
import java.util.concurrent.*;
import java.util.*;
@SuppressWarnings("unchecked")
 class Reverse implements Callable<String> {
  String inputString = "";

  public Reverse(String inputString) {
    this.inputString = inputString;
  }

  @Override
  public String call() throws Exception {

    StringBuilder output = new StringBuilder();
    String[] words = inputString.split(" ");

    for (int i = words.length - 1; i >= 0; i--) {
        output.append(words[i]);
        output.append(" ");
    }
    return output.toString().trim();
  }
}

public class Declaration {
  
  public static void main(String[] args) throws FileNotFoundException, IOException {

    long start = System.currentTimeMillis();
    int threadCount = 1;
    ExecutorService service = Executors.newFixedThreadPool(threadCount);

    //File file = new File("DeclarationIndependence.txt");
    Scanner sc = new Scanner(new File("DeclarationIndependence.txt"));
    List wordList = new ArrayList();
    Future[] allFuture = new Future[1024];
    int counter = 0;
    String finalAnswer = "";

    while (sc.hasNextLine()) {
      wordList.add(sc.next());
    }

    Iterator wordIter = wordList.iterator();
    while(wordIter.hasNext()) {
      int count = 0;
      String inputString = "";

      while (count !=10 && wordIter.hasNext()) {
        inputString += wordIter.next() +" ";
        count++;
      }
      
      Future<String> future = service.submit(new Reverse(inputString));
      allFuture[counter] = future;
      counter++;
    }
    
    service.shutdown();
    
    for(int i=0; i<allFuture.length/2; i++) {
      Future temp = allFuture[i];
      allFuture[i] = allFuture[allFuture.length -i -1];
      allFuture[allFuture.length -i -1] = temp;
    }
    
    File file = new File("backwards.txt");
    FileWriter writer = new FileWriter(file); 


    for(Future i:allFuture) {
         try {
          if(i==null) {
            continue;
          } else {                    
              writer.write( (String) i.get());
          }
        } catch (Exception e) {
           e.printStackTrace();
         }
       }
    writer.close();
    
    long end = System.currentTimeMillis();
    System.out.println("\n\nHW 4 Problem 3.1");
    System.out.println("Time performed using " + threadCount + " thread is " + (end - start) +" milliseconds");
  }
}
