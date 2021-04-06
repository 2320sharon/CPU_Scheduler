package cpu_scheduler.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SchedulerTest {
  
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
