package cpu_scheduler.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.*;
import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SchedulerTest {
  
  //Queue to be filled with testing values
  LinkedList<Process> queue;
  PrintWriter outputtest;

  @BeforeAll
  public void make_outputfile() throws FileNotFoundException{
   outputtest= new PrintWriter("output_test.txt");
  }


  @BeforeAll
  public void make_queue(){
    System.out.println("Make a queue");
    queue = new LinkedList<>();
   Process process= new Process(1,10,5);
       queue.add(process);
  Process process2= new Process(2,8,1);
       queue.add(process2);
  Process process3= new Process(3,12,7);
       queue.add(process3);
       System.out.println("Finished a queue");
  }
  
 

  @Test
  public void test_copy_queue() {
     //Arrange
     final LinkedList<RR_Process> expected_rr_queue= new LinkedList<RR_Process>();
     RR_Process  rr_proc;

     for(int i =0; i< queue.size() ; ++i)
     {
        rr_proc= new  RR_Process( queue.get(i).id, queue.get(i).time) ;
        expected_rr_queue.add( rr_proc);
     }
     
     //test
     System.out.println("Calling get_copy_queue()");
    final LinkedList<RR_Process> actual_rr_queue = Scheduler.copy_queue( queue );

    //Assert
    //I will need to make a custom assert function

   //Custom test function to compare the two linked lists
     assert(true);

     // check if each of the values equal each other if they don't they we know its a fail so return assert false
     //otherwise assert(true) and maybe look into how to return a message
    System.out.println("Finished get_wait() test");
  }

  @Test
  public void test_get_wait() throws FileNotFoundException{
     //Arrange
     final int expected_wait= 28;

     //test
     System.out.println("Calling get_wait()");
    final int actual_wait= Scheduler.get_wait(queue, outputtest ,  'f');

    //Assert
    assertEquals(expected_wait, actual_wait);
    System.out.println("Finished get_wait() test");
  }

  @Test
  public void test_get_turn_around_time() throws FileNotFoundException{
     //Arrange
     final int expected_sum_turnaround= 58;

     //test
     System.out.println("Calling get_turn_around_time()");
    final int actual_sum_turnaround= Scheduler.get_turn_around_time(queue, outputtest ,  'f');

    //Assert
    assertEquals(expected_sum_turnaround, actual_sum_turnaround);
    System.out.println("Finished get_turn_around_time() test");
  }


  @Test
  public void test_get_throughput() throws FileNotFoundException{
     //Arrange
     final int expected_total_time= 30;

     //test
     System.out.println("Calling get_throughput()");
     final int actual_total_time= Scheduler.get_throughput(queue, outputtest ,  'f');

    //Assert
    assertEquals(expected_total_time, actual_total_time);
    System.out.println("Finished get_throughput() test");
  }

      @Test
    public void test_get_average() {    
        // Arrange
        final double expected = 2;
        double delta =0.1;
    
        // Act
        final double actual = Scheduler.get_average( 4.0 , 2  );
    
        // Assert
        assertEquals(expected, actual,delta);
      }   
      
      @Test
      public void test_calc_throughput() {    
          // Arrange
          final double expected = 0.5;
          double delta =0.1;
      
          // Act
          final double actual = Scheduler.calc_throughput( 4.0 , 2  );
      
          // Assert
          assertEquals(expected, actual,delta);
        }   
    

}
