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
           output.println("average wait time for " + queue.size() + " procs = " + average_wait );
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
             output.println(schedule_type +" throughput for p"+ queue.size() + " = " + throughput );
            return total_time;
        }

      public static int get_turn_around_time(LinkedList<Process> queue, PrintWriter output, char mode ) {
        String schedule_type="null";
        int sum_turnaround=0;
        int turnaround=0;
        output.println();                           //print a black line to separate
         if(mode == 'f')//FCFS scheduling wait
           schedule_type= "fcfs";
         else
          schedule_type= "hpf";

             for(int i =0; i< queue.size() ; ++i)
             {
              turnaround+=queue.get(i).time;
              sum_turnaround+= turnaround;          //add the turn around times to the sum 
              output.println(schedule_type +" turn-around time for p"+ (i+1) + " = " + sum_turnaround );
            }
             double average_turn_around_time =get_average((double)sum_turnaround,queue.size());
             output.println("average turn-around time for " + queue.size() + " procs = " + average_turn_around_time );
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
}
