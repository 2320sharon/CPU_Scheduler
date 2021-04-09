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

    //add an option to change the input and output file

    //add a go again option

    //maybe consolidate some of the functions into a single function.

     App.read_inputfile(process_queue);

     //create the output file
      PrintWriter outputFile= new PrintWriter("output.txt");
     
      Scheduler cpu_schedule= new Scheduler(); //do I need an object for this?

      //FCFS test
      cpu_schedule.get_FCHS_schedule(process_queue,outputFile,'f');


      //HPF test
      LinkedList<Process> sorted_queue=new LinkedList<>();
      sorted_queue = cpu_schedule.sort_queue(process_queue);//need to put this cpu_schedule
      cpu_schedule.print_queue(sorted_queue,outputFile,'h');
      cpu_schedule.get_wait(sorted_queue,outputFile,'h');
      cpu_schedule.get_turn_around_time(sorted_queue,outputFile,'h');
      cpu_schedule.get_throughput(sorted_queue,outputFile,'h');

      cpu_schedule.RoundRobin_Schedular(process_queue, outputFile);

      outputFile.close();
  }
}