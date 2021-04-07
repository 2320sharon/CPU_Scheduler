package cpu_scheduler.app;

public class RR_Process {
    int id;
    int time;
    int time_left;
    int timeslices=0;

    public int get_id() {
        return id;
    }

    public int get_time() {
        return time;
    }

    RR_Process(int given_id, int given_time)
    {
        id=given_id;
        time= given_time;
        time_left= time;
        timeslices=0;
    }

    RR_Process()
    {
        id=1;
        time= 2;
        time_left=time;
        timeslices=0;
    }

    @Override
    public String toString() {
        String str= "ID: "+ id + " time: "+ time;
        return str;
    }
}
