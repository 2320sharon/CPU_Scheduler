package cpu_scheduler.app;

public class Process {
    int id;
    int time;
    int priority;

    public int get_id() {
        return id;
    }

    public int get_time() {
        return time;
    }

    public int get_priority() {
        return priority;
    }

    Process(int given_id, int given_time, int given_priority)
    {
        id=given_id;
        time= given_time;
        priority=given_priority;
    }

    @Override
    public String toString() {
        String str= "ID: "+ id + " time: "+ time+" priority: "+ priority;
        return str;
    }
}
