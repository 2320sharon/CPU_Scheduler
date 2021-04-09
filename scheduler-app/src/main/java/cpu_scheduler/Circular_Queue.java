package cpu_scheduler;
import java.util.NoSuchElementException;

import cpu_scheduler.app.RR_Process;

public class Circular_Queue {
    private int size, front, rear;

    //private LinkedList<RR_Process> queue;
    RR_Process[] queue;

    public Circular_Queue(int given_size){
        size=given_size;
       front=rear=-1;//nothing is in the queue, so set both front and rear to -1
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
      }  
         rear= (rear+1)%size ;  //set rear = to the next available position to insert 
         queue[rear] =  rr_proc;
    }

    public RR_Process peek(){
       if(is_empty())
       {
           System.out.println("Queue is empty. Cannot Peek");
           throw new NoSuchElementException();
       }
        return queue[front];
    }

    public RR_Process deQueue()
    {
       if(is_empty())//empty queue
       {
        System.out.print("Queue is Empty");
        throw new NoSuchElementException();
       }
       RR_Process rr_proc= queue[front];// may invoke the copy constructor
       if(front == rear)//single item in the queue
            front = rear = -1;//removed the single item in the queue so now it is empty
       else    
            front = (front+1)%size;             //remove element from the front and new front follows the previous front
      
        return rr_proc;
    }


}

