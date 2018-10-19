
public class Event {
	enum EventType {
		move_bus;
	}
	
	Integer id;
	EventType type;
	Integer time;

	public Event(EventType type, Integer time, Integer id) {
		this.id = id;
		this.type = type;
		this.time = time;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
}
