package cpu_scheduler;
import java.util.ArrayList;

import cpu_scheduler.app.RR_Process;

public class Circular_Queue {
    private int size, front, rear;

    private ArrayList<RR_Process> queue= new ArrayList<>();

    public Circular_Queue(int size){
        this.size=size;
        this.front=this.rear=-1;//nothing is in the queue, so set both front and rear to -1
    }

    public boolean is_empty(){
        if(front == -1)
            return true;
         else
            return false;   
    }

    public boolean is_full()
    {
        if((front == 0 && rear == size - 1) || (rear == (front - 1) % (size - 1)))
        return true;
     else
        return false;   
    }

    public void enQueue(RR_Process rr_proc)
    {

        //check if queue is full: if front is at position 0 and rear is at the last position  OR
        //if rear == the placement of the front index -1 the queue is full
        if(is_full())
      {
          System.out.print("Queue is Full");
      }
      else if(front == -1){//insert into empty queue
        front = rear=0;
        queue.add(rr_proc);
      }else if(rear == size-1 && front != 0) //index 0 is the last empty place in the queue
      {  
        rear=0;
        queue.set(rear,rr_proc);
      }else{
        rear+=1;    //increament the rear to have room in the queue to insert into 
        if(front <= rear){
            queue.add(rr_proc);
        }
        else{
            queue.set(rear,rr_proc);
        }
      }



    }
    
    public RR_Process deQueue()
    {
        RR_Process rr_proc= new RR_Process(-1,-1);

       if(is_empty())//empty queue
       {
        System.out.print("Queue is Empty");
        // Return rr_proc filled with -1 for both id and time in case of empty queue
        return rr_proc; 
       }

       rr_proc= queue.get(front);//may need to build a copy constructor

       if(front == rear)//single item in the queue
       {
            front = rear = -1;//removed the single item in the queue so now it is empty
       }
       else if(front == (size-1)){  //there are two items in the queue
            front=0;                //front is now equal to the only item left in the queue
       }
       else{    //more than two items in the queue
            front = front+1;    //the new front will be the second item in the queue
       }
       
        return rr_proc;
    }

    public void displayQueue()
{
      
    // Condition for empty queue.
    if(front == -1)
    {
        System.out.print("Queue is Empty");
        return;
    }
  
    // If rear has not crossed the max size
    // or queue rear is still greater then
    // front.
    System.out.print("Elements in the " +
                     "circular queue are: ");
  
    if(rear >= front)
    {
      
        // Loop to print elements from
        // front to rear.
        for(int i = front; i <= rear; i++)
        {
            System.out.print(queue.get(i));
            System.out.print(" ");
        }
        System.out.println();
    }
  
    // If rear crossed the max index and
    // indexing has started in loop
    else
    {
          
        // Loop for printing elements from
        // front to max size or last index
        for(int i = front; i < size; i++)
        {
            System.out.print(queue.get(i));
            System.out.print(" ");
        }
  
        // Loop for printing elements from
        // 0th index till rear position
        for(int i = 0; i <= rear; i++)
        {
            System.out.print(queue.get(i));
            System.out.print(" ");
        }
        System.out.println();
    }
}
}
