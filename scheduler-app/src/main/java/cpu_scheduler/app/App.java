package cpu_scheduler.app;
import java.io.*;
import java.util.*;
import cpu_scheduler.Circular_Queue;

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

public static void sort_queue(LinkedList<Process> process_queue)
{
  Collections.sort(process_queue, new Comparator<Process>() { 
    @Override 
    public int compare(Process p1, Process p2)
     { return p1.priority - p2.priority ;
      } } );

      System.out.println("LinkedList (after sorting by priorty): " + process_queue);
}

  public static void main( String[] args )throws IOException
  {
    //create the input queue
    LinkedList<Process> process_queue=new LinkedList<>();
     App.read_inputfile(process_queue);

     //create the output file
      PrintWriter outputFile= new PrintWriter("output.txt");
     
      Scheduler cpu_schedule= new Scheduler(); //do I need an object for this?

      //FCFS test
     /* cpu_schedule.print_queue(process_queue,outputFile,'f');
      cpu_schedule.get_wait(process_queue,outputFile,'f');
      cpu_schedule.get_turn_around_time(process_queue,outputFile,'f');
      cpu_schedule.get_throughput(process_queue,outputFile,'f');
*/
      //HPF test
      /*App.sort_queue(process_queue);//need to put this cpu_schedule
      cpu_schedule.print_queue(process_queue,outputFile,'h');
      cpu_schedule.get_wait(process_queue,outputFile,'h');
      cpu_schedule.get_turn_around_time(process_queue,outputFile,'h');
      cpu_schedule.get_throughput(process_queue,outputFile,'h');*/

      //Circular_Queue rr_queue =  cpu_schedule.copy_queue(process_queue);

      cpu_schedule.RoundRobin_Schedular(process_queue, outputFile);
     // cpu_schedule.RoundRobin(process_queue,outputFile, 5,2,rr_queue);


      outputFile.close();
  }
}