package cpu_scheduler.app;
import java.io.*;
import java.util.*;

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
             output.printf(schedule_type +" throughput for "+ queue.size() + " procs = %.4f " , throughput ,  " proc/ms  \n" );
             output.println( );
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
             output.printf("average turn-around time for " + queue.size() + " procs =%.4f " , average_turn_around_time, "\n" );
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

      public static  LinkedList<RR_Process> copy_queue(LinkedList<Process> queue )
      {
        LinkedList<RR_Process>rr_queue= new LinkedList<>(); //make a new queue 
        RR_Process  rr_proc;

        for(int i =0; i< queue.size() ; ++i)
        {
           rr_proc= new  RR_Process( queue.get(i).id, queue.get(i).time) ;
           rr_queue.add( rr_proc);
        }
        return rr_queue;
      }

      public static void RoundRobin(LinkedList<Process> queue, PrintWriter output, int quantum,  int overhead,  LinkedList<RR_Process> rr_queue)
      {

        //errors caused by fact this is a linked list and not a queue. Meaning the linked list must be copied to an actual queue

        System.out.println("Entered Round Robin");
        int total_time=0; 
        int index=0;
        int counter =0;
        int sum_wait_time=0;
        int size= queue.size();

        output.println();
        output.println("preemptive RR schedule, quantum = "+ quantum  + " overhead " + overhead );
       // LinkedList<RR_Process>rr_queue =  copy_queue(queue);
        while(!rr_queue.isEmpty())
        {
          System.out.println("Entered the loop");
          System.out.println("index: "+ index);
          
            if(rr_queue.get(index).time_left <=quantum)
            {
              total_time+= rr_queue.get(index).time_left;  //add the process' time to the total, since its less than quantum
              ++rr_queue.get(index).timeslices;
              output.println("RR TA time for finished p"+rr_queue.get(index).id + " = "+total_time + " needed: " + rr_queue.get(index).time + " ms, and: "+ rr_queue.get(index).timeslices + " time slices.");
              System.out.println("RR TA time for finished p"+rr_queue.get(index).id + " = "+total_time + " needed: " + rr_queue.get(index).time + " ms, and: "+ rr_queue.get(index).timeslices + " time slices.");
              sum_wait_time+=total_time;
              System.out.println("sum_wait_time: "+ sum_wait_time);
              rr_queue.remove(index);
              total_time+=overhead;
              System.out.println("Total time after overhead is "+ total_time);
            }
            else{
              ++ rr_queue.get(index).timeslices;      //the process has used a time slice
              total_time += quantum;
              System.out.println("Total time for proc: "+ rr_queue.get(index).id + " is "+ total_time);
              rr_queue.get(index).time_left-=quantum;
              total_time+=overhead;
              System.out.println("Total time for proc: "+ rr_queue.get(index).id + " after overhead is "+ total_time);
            }
            if(index >= (rr_queue.size()-1))
              index=0;
            else
            ++index;
        }
          //total_time-=overhead;//decrease the total time to account for the extra context switch at the end
        double average_rr= get_average(sum_wait_time,size);
         output.println("Average RR TA, "+ size + "  p with q: "+ quantum+ ", o: "+overhead + ", is: "+ average_rr);
         System.out.println("Average RR TA, "+ size + "  p with q: "+ quantum+ ", o: "+overhead + ", is: "+ average_rr);

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


    }
