import java.util.Comparator;

class SortEvents implements Comparator<Event> { 
    public int compare(Event a, Event b) 
    { 
        return a.getTime() - b.getTime(); 
    } 
} 