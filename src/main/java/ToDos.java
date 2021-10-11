import java.util.HashMap;

public abstract class ToDos {

    HashMap<Event, Boolean> eventMap;

    private boolean done;

    /**
     * A generic constructor that initializes eventMap
     * as an empty HashMap.
     */
    public ToDos() {
        eventMap = new HashMap<>();
    }

    public boolean getDone() {
        return done;
    }

    /**
     * updateToDos is am abstract method that adds the list of tasks/events and whether they have been completed
     * it returns void
     * @param task   Event Object
     * @param done   This is true when the task/event is completed and false when the event/task is not completed
     */
   @Override
    public abstract void updateToDos (Event task, Boolean done);


    /**
     * completeToDos is am abstract method that adds the list of tasks/events and whether they have been completed
     * it returns true if all tasks have been completed
     * @param task   Event Object
     * @param done   This is true when the task/event is completed and false when the event/task is not completed
     */
    @Override
    public abstract boolean completeToDos (Event task, Boolean done);
}

