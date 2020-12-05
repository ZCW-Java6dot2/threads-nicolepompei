import java.util.HashMap;
import java.util.Map;

public class EventTracker implements Tracker {

    private static EventTracker INSTANCE = new EventTracker();

    private Map<String, Integer> tracker;

    private EventTracker() {
        this.tracker = new HashMap<>();
    }

    synchronized public static EventTracker getInstance() {
        return INSTANCE;
    }

    synchronized public void push(String message) {
        int count = tracker.getOrDefault(message, 0);
        tracker.put(message, count++);

    }

    synchronized public Boolean has(String message) {
        int count = tracker.getOrDefault(message, 0);
        return count > 0;
    }

    synchronized public void handle(String message, EventHandler e) {
        int count = tracker.getOrDefault(message,0);

        if(count > 0){
            tracker.put(message, count--);
        }
    }

    // Do not use this. This constructor is for tests only
    // Using it breaks the singleton class
    EventTracker(Map<String, Integer> tracker) {
        this.tracker = tracker;
    }
}
