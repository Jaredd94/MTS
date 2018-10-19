import java.util.ArrayList;

public class Route {
	
	private Integer id;
	private Integer number;
	private String name;
	private ArrayList<Stop> stops;

	public Route() {
		
	}
	
	public Route(Integer id, Integer number, String name) {
		this.id = id;
		this.number = number;
		this.name = name;
		this.stops = new ArrayList<Stop>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
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
}
