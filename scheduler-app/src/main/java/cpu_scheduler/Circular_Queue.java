package cpu_scheduler;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import cpu_scheduler.app.RR_Process;

public class Circular_Queue {
    private int size, front, rear, curr_size;

    //private LinkedList<RR_Process> queue;
    RR_Process[] queue;

    public Circular_Queue(int given_size){
      //  System.out.println("Given Size given is : "+ given_size );
        size=given_size;
     //   System.out.println(" Size  is : "+ size );
       front=rear=-1;//nothing is in the queue, so set both front and rear to -1
        curr_size=0;

       // queue = new LinkedList<>();
       queue = new RR_Process[size];
    }

    public int get_size(){
        return size;
    }

    public boolean is_empty(){
        return(front == -1)  ;
    }

    public boolean is_full()
    {
        return(((rear+1)%size)== front);    //if the next avaiable position to insert is the front then the queue is full
    }

    public void enQueue(RR_Process rr_proc)
    {
        //check if queue is full: if front is at position 0 and rear is at the last position  OR
        //if rear == the placement of the front index -1 the queue is full
        if(is_full())
      {
          System.out.print("Queue is Full");
      }
      else if(is_empty()){//insert into empty queue
       ++ front ;
        System.out.println("Queue was empty. Added "+ rr_proc + " to queue. at rear "+ rear + " and the new front is: "+ front);
      }  
      System.out.println("Front is : " + front);
         rear= (rear+1)%size ;  //set rear = to the next available position to insert 
         queue[rear] =  rr_proc;
        //queue.set(rear,rr_proc);    //inserts at the new rear
        System.out.println("Inserted "+  queue[rear].id + " to queue. rear at index "+ rear + "Front index: "+ front);
      /*  ++curr_size;
        System.out.println();
        System.out.println("Displaying queue withing enqueue");
        displayQueue();
        System.out.println();*/
    }

    public RR_Process peek(){
       if(is_empty())
       {
           System.out.println("Queue is empty. Cannot Peek");
           throw new NoSuchElementException();
       }
     // RR_Process rr_proc= queue.get(front);
        return queue[front];
    }

    public RR_Process deQueue()
    {
       // RR_Process rr_proc= new RR_Process(-1,-1);

       if(is_empty())//empty queue
       {
        System.out.print("Queue is Empty");
        throw new NoSuchElementException();
      //  return rr_proc; 
       }

       RR_Process rr_proc= queue[front];// may invoke the copy constructor

       if(front == rear)//single item in the queue
       {
            front = rear = -1;//removed the single item in the queue so now it is empty
            System.out.println("Removed the only item from the queue");
       }
       else{    
            front = (front+1)%size;             //remove element from the front and new front follows the previous front
            System.out.println("The new front is at : " + front);
       }
       System.out.println("The item dequeue is : " + rr_proc);
       --curr_size;
        return rr_proc;
    }


}

