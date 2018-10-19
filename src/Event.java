
public class Event {
	enum EventType {
		move_bus;
	}
	
	EventType type;
	int time;
	String[] args;

	public Event(int time, EventType type, String[] args) {
		this.time = time;
		this.type = type;
		this.args = args;
	}
	
	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public int getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
}
