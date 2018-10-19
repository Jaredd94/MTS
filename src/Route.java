import java.util.ArrayList;

public class Route {
	
	private int id;
	private int number;
	private String name;
	private ArrayList<Stop> stops;

	public Route() {
		
	}
	
	public Route(int id, int number, String name) {
		this.id = id;
		this.number = number;
		this.name = name;
		this.stops = new ArrayList<Stop>();
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Stop> stopList() {
		return stops;
	}

	public Stop getStop(Integer index) {
		return stops.get(index);
	}

	public void addStop (Stop stop) {		
		stops.add(stop);
	}
	
	public Stop getStopById(Integer id) {
		if (stops.isEmpty()) {
			System.out.println("No stops found in route.  You need to add stops to this route!");
			return null;
		}
		for (Stop stop: stops) {
			if (id.equals(stop.getId())) {
				return stop;
			}
		}
		
		System.out.printf("Unable to find any stops with id %d",id);
		return null;
	}
}
