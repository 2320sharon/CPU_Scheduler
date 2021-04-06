package cpu_scheduler.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.*;
import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SchedulerTest {
  
  //Queue to be filled with testing values
  LinkedList<Process> queue;

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
  public void test_get_wait() throws FileNotFoundException{
     //Arrange
     final int expected_wait= 28;

     //test
     System.out.println("Calling get_wait()");
    PrintWriter outputtest= new PrintWriter("output_test.txt");
    final int actual_wait= Scheduler.get_wait(queue, outputtest ,  'f');

    //Assert
    assertEquals(expected_wait, actual_wait);
    System.out.println("Finished get_wait() test");
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
