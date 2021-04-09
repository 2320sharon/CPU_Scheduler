package cpu_scheduler.app;
import java.io.*;
import java.util.*;

public class App 
{
 public static void read_inputfile(LinkedList<Process> queue,String input_file) throws IOException {

   try{
    File file = new File(input_file);
    Scanner inputFile= new Scanner(file);
    
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
    String answer;
    String input_name = "input.txt";
    String output_name = "output.txt";
    char loop_choice, defaults;
    boolean loop_again, use_defaults = false;
    Scanner keyboard = new Scanner(System.in);

    LinkedList<Process> process_queue=new LinkedList<>();

    System.out.println("Would you like to use the default input.txt and output.txt file?\n If not enter 'n'");
    answer= keyboard.nextLine();
    defaults= answer.charAt(0);
    if(defaults=='n' ||defaults=='N' )
    {
      //options to change the input and output file
      System.out.println("Enter a filename to read input from: (example: input.txt)");
      input_name= keyboard.nextLine();

      System.out.println("Enter a filename to write output to: (example: output.txt)");
      output_name= keyboard.nextLine();
    }
     App.read_inputfile(process_queue,input_name );

     //create the output file
      PrintWriter outputFile= new PrintWriter(output_file);
     
      Scheduler cpu_schedule= new Scheduler(); //do I need an object for this?

      //FCFS Scheduler
      cpu_schedule.get_FCHS_schedule(process_queue,outputFile,'f');

      //HPF Scheduler
      cpu_schedule.get_HPF_schedule(process_queue,outputFile,'h');

     //Round Robin Scheduler
      cpu_schedule.RoundRobin_Schedular(process_queue, outputFile);

      outputFile.close();
  }
}