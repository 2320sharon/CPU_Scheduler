package cpu_scheduler.app;

public class RR_Process {
    public int id;
    int time;
    int time_left;
    int timeslices=0;

    public int get_id() {
        return id;
    }

    public int get_time() {
        return time;
    }

    public RR_Process(int given_id, int given_time)
    {
        id=given_id;
        time= given_time;
        time_left= time;
        timeslices=0;
    }


    public RR_Process(int given_id, int given_time, int given_timeslices, int given_time_left)
    {
       id=given_id;
       time= given_time;
        time_left= given_time_left;
        timeslices=given_timeslices;
    }

    //copy constructor
    public RR_Process(RR_Process rr_proc){
        System.out.println("\nCopy constructor evoked!\n");
        time= rr_proc.time;
        time_left= rr_proc.time_left;
        id= rr_proc.id;
        timeslices= rr_proc.timeslices;
    }

    public RR_Process()
    {
        id=1;
        time= 2;
        time_left=time;
        timeslices=0;
    }

    @Override
    public String toString() {
        String str= "ID: "+ id + " time: "+ time + " Time _left: " + time_left;
        return str;
    }
}
