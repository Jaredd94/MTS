
public class Event {
	
	enum EventType {
		move_bus, adjust_passenger_capacity, adjust_bus_speed, adjust_bus_route
	}
	
	private int time;
	private EventType type;
	private String[] args;

	public Event(int time, EventType type, String[] args) {
		this.time = time;
		this.type = type;
		this.args = args;
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}
	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}
}
