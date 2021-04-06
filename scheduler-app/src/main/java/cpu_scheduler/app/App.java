package cpu_scheduler.app;
import java.io.*;
import java.util.*;

public class App 
{

 public static void read_inputfile(LinkedList<Process> queue) throws IOException {

   try{
    File file = new File("input.txt");
    Scanner inputFile= new Scanner(file);
    //Hello this is a change
    
    try{
      while(inputFile.hasNext())
      {
          String input= inputFile.nextLine();
          String[] tokens= input.split("\\s+");
        //  System.out.println(" id: " + tokens[0]);
       //   System.out.println("time: "+ tokens[1]);
       //   System.out.println("priority: "+  tokens[2]);
          //parse into three variables 
          Process process = new Process();
          process.id=Integer.parseInt(tokens[0]);
          process.time=Integer.parseInt(tokens[1]);
          process.priority=Integer.parseInt(tokens[2]);
  
         queue.add(process);
          System.out.println();
      }
    }catch(InputMismatchException mismatch_exception)
    {
      System.out.println("Invalid data found!");
    }
    finally{
      inputFile.close();
    }
  }
  catch(FileNotFoundException exception_file){
    System.out.println("Input file Not Found");
  }
 
   
  }
  public static void main( String[] args )throws IOException
  {
    //create the input queue
    LinkedList<Process> process_queue=new LinkedList<>();
     App.read_inputfile(process_queue);

     //create the output file
      PrintWriter outputFile= new PrintWriter("output.txt");
     
      Scheduler cpu_schedule= new Scheduler(); //do I need an object for this?
      cpu_schedule.get_wait(process_queue,outputFile,'f');
      cpu_schedule.get_turn_around_time(process_queue,outputFile,'f');
      cpu_schedule.get_throughput(process_queue,outputFile,'f');
      
      outputFile.close();
     /*
      // Example 2 - Sorting LinkedList using Collection.sort() and Comparator in Java
       Collections.sort(process_queue, new Comparator<Process>() { 
         @Override 
         public int compare(Process p1, Process p2)
          { return p1.priority - p2.priority ;
           } } );

           System.out.println("LinkedList (after sorting in natural): " + process_queue);
*/



    


  }
}