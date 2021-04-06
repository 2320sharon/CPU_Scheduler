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

    @Override
    public String toString() {
        String str= "ID: "+ id + " time: "+ time+" priority: "+ priority;
        return str;
    }
}