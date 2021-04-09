package cpu_scheduler.app;
import java.io.*;
import java.util.*;
import cpu_scheduler.Circular_Queue;

public class Scheduler {
      public static int get_wait(LinkedList<Process> queue, PrintWriter output, char mode ) {
      String schedule_type="null";
      int total_wait=0;
      int sum_wait=0;

       if(mode == 'f')//FCFS scheduling wait
         schedule_type= "fcfs";
       else
       schedule_type= "hpf";

           for(int i =0; i< queue.size() ; ++i)
           {
            output.println(schedule_type +" wait of p "+ (i+1) + " = " + total_wait );
            sum_wait += total_wait;
            if (i != (queue.size()-1) )              //Do not want to include the last wait time.
             { 
               total_wait += queue.get(i).time;         //increase the waiting time each time a process completes
             }
           }
    
           double average_wait =get_average((double)sum_wait,queue.size());
         
           output.printf("average wait time for " + queue.size() + " procs = %.4f", average_wait, "\n" );
           output.println();
           return sum_wait;
       
      }

      public static int get_throughput(LinkedList<Process> queue, PrintWriter output, char mode ) {
        String schedule_type="null";
        int total_time=0;
  
         if(mode == 'f')//FCFS scheduling wait
           schedule_type= "fcfs";
         else
          schedule_type= "hpf";

             for(int i =0; i< queue.size() ; ++i)
              total_time += queue.get(i).time;         //increase the waiting time each time a process completes
      
             double throughput =  calc_throughput((double)total_time,queue.size());
             output.printf(schedule_type +" throughput for "+ queue.size() + " procs = %.4f proc/ms  \n " , throughput);
            // output.println( );
             output.println(" <><> end " + schedule_type +" schedule <><>" );
            return total_time;
        }

      public static int get_turn_around_time(LinkedList<Process> queue, PrintWriter output, char mode ) {
        String schedule_type="null";
        int sum_turnaround=0;
        int turnaround=0;
       
         if(mode == 'f')//FCFS scheduling wait
           schedule_type= "FCFS";
         else if (mode == 'h')
          schedule_type= "HPF";
          else
           schedule_type= "RR";

             for(int i =0; i< queue.size() ; ++i)
             {
              turnaround+=queue.get(i).time;
              sum_turnaround+= turnaround;          //add the turn around times to the sum 
              output.println(schedule_type +" turn-around time for p"+ (i+1) + " = " + turnaround );
            }
             double average_turn_around_time =get_average((double)sum_turnaround,queue.size());
            
             output.printf("average turn-around time for " + queue.size() + " procs =%.4f " , average_turn_around_time, " \n" );
             output.println();
             return sum_turnaround;
        }
 
      public static double get_average(double total_wait,int size )
      {
            return total_wait/size ;
      }

      public static double calc_throughput(double total_wait,int num_procs )
      {
            return num_procs/total_wait ;
      }

      public  void RoundRobin_Schedular(LinkedList<Process> queue, PrintWriter output){
        int quantum=5;
        int overhead=0;
        for(int q=1 ; q <= quantum; ++q)
        {
            for( overhead=0; overhead<=q; ++overhead)
            {
              System.out.println("preemptive RR schedule, quantum = "+q+ " overhead = "+ overhead);
              output.println("preemptive RR schedule, quantum = "+q+ " overhead = "+ overhead);
              //call the copy constructor 
              Circular_Queue rr_queue =  copy_queue(queue);
              //call the robin robin
              RoundRobin(queue,output, q,overhead,rr_queue);
              System.out.println();
              output.println();
             
            }
            System.out.println();
        }
        System.out.println( "<><> end preemptive RR schedule <><>");
        output.println( "<><> end preemptive RR schedule <><>");
      }

      public static  Circular_Queue copy_queue(LinkedList<Process> queue )
      {
        System.out.println("Given queue.size() is: "+ queue.size());
        Circular_Queue rr_queue= new Circular_Queue(queue.size()); //make a new queue
        RR_Process  rr_proc;

        System.out.println(" rr_queue.size() is: "+ rr_queue.get_size());

        for(int i =0; i< queue.size() ; ++i)
        {
           rr_proc= new  RR_Process( queue.get(i).id, queue.get(i).time) ;
           rr_queue.enQueue( rr_proc);
        }
        System.out.println(" rr_queue.size() is: "+ rr_queue.get_size());
        return rr_queue;
      }

      private  void RoundRobin(LinkedList<Process> queue, PrintWriter output, int quantum,  int overhead, Circular_Queue rr_queue)
      {
        System.out.println("Entered Round Robin");
        int total_time=0; 
        int sum_wait_time=0;
        int size= queue.size();
        RR_Process curr_proc;

       // output.println();
       // output.println("preemptive RR schedule, quantum = "+ quantum  + " overhead " + overhead );
       // LinkedList<RR_Process>rr_queue =  copy_queue(queue);
        while(!rr_queue.is_empty())
        {
          System.out.println();
          System.out.println("Entered the loop");
          System.out.println(" Before dequeue");
         
           
           //curr_proc = rr_queue.peek();           //take the first process for the queue and modify it
           curr_proc = rr_queue.deQueue();           //take the first process for the queue and modify it
          System.out.println("After dequeue");
        

          System.out.println("p"+curr_proc.id + "time left "+curr_proc.time_left + ", needs " + curr_proc.time);
          
            if(curr_proc.time_left <=quantum)       //will not need the full CPU burst time and the process is finished so it will not be reinserted
            {
              System.out.println(" p"+curr_proc.id + "time left  = "+curr_proc.time_left);
              total_time+= curr_proc.time_left;       //add the process' time to the total, since its less than quantum
              ++curr_proc.timeslices;
              output.println("RR TA time for finished p"+curr_proc.id + " = "+total_time + ", needed: " + curr_proc.time + " ms, and: "+ curr_proc.timeslices + " time slices.");
              System.out.println("RR TA time for finished p"+curr_proc.id + " = "+total_time + ", needed: " + curr_proc.time + " ms, and: "+ curr_proc.timeslices + " time slices.");
              sum_wait_time+=total_time;
              curr_proc.time_left-=quantum;
              System.out.println("sum_wait_time: "+ sum_wait_time);
              total_time+=overhead;
              System.out.println("Total time after overhead is "+ total_time);
             // curr_proc = rr_queue.deQueue();
            }
            else{
              ++ curr_proc.timeslices;                 //the process has used a time slice
              total_time += quantum;
              //System.out.println("Total time for proc: "+ curr_proc.id + " is "+ total_time);
              curr_proc.time_left-=quantum;

              total_time+=overhead;
              System.out.println("Total time for proc: "+ curr_proc.id + " after overhead is "+ total_time);
             // System.out.println("Curr size before enqueue : "+rr_queue.get_curr_size());
              rr_queue.enQueue(curr_proc);             //put the process back into the queue at the rear
             // System.out.println("Curr proc: "+ curr_proc.id );
             // System.out.println("Curr size after enqueue : "+rr_queue.get_curr_size());
            }
        }
          //total_time-=overhead;//decrease the total time to account for the extra context switch at the end
        double average_rr= get_average(sum_wait_time,size);
        double throughput_rr=  calc_throughput((total_time-overhead),size);
      //  System.out.println("Total time Elapsed "+ total_time);
 
         output.printf("RR Throughput, "+ size + "  p with q: "+ quantum+ ", o: "+overhead + ", is: %.4f p/ms, or  %.4f p/us",throughput_rr ,(throughput_rr *1000)  );
         System.out.printf("\nRR Throughput, "+ size + "  p with q: "+ quantum+ ", o: "+overhead + ", is: %.4f p/ms, or  %.4f p/us",throughput_rr ,(throughput_rr *1000)  );

         output.printf("\nAverage RR TA, "+ size + "  p with q: "+ quantum+ ", o: "+overhead + ", is: %.4f \n", average_rr);
         System.out.printf("\nAverage RR TA, "+ size + "  p with q: "+ quantum+ ", o: "+overhead + ", is: %.4f \n ", average_rr);

      }

      public static void print_queue(LinkedList<Process> queue, PrintWriter output, char mode )
      {
        String schedule_type="null";
        output.println();                   //print a blank line in the output file

        if(mode == 'f')
        {
        schedule_type= "FCFS";
        output.println("Process list in "+ schedule_type +" order as entered: " );
        }
       else if(mode == 'h')
       {
        schedule_type= "HPF";
        output.println("Process list in "+ schedule_type +" order: " );
       }
       else
       {
        schedule_type= "RR";
        output.println("Process list for "+ schedule_type +"in order entered: " );
       }

        for(int i =0; i< queue.size() ; ++i)
        {
          output.println( queue.get(i).id + " " +  queue.get(i).time + " "+ queue.get(i).priority );
        }
        output.println( "End of list." );
        output.println();
      }

      public static LinkedList<Process> sort_queue(LinkedList<Process> process_queue)
{
  //make a copy of process_queue to sort
  LinkedList<Process> queue= new LinkedList<Process>() ;
  for(int i=0; i< process_queue.size(); ++i)
  {
    Process process = new Process();
    process.id=process_queue.get(i).id;
    process.time=process_queue.get(i).time;
    process.priority=process_queue.get(i).priority;
    queue.add(process);
  }

  Collections.sort(process_queue, new Comparator<Process>() { 
    @Override 
    public int compare(Process p1, Process p2)
     { return p1.priority - p2.priority ;
      } } );

      System.out.println("LinkedList (after sorting by priorty): " + process_queue);
      return queue;
}

    }
