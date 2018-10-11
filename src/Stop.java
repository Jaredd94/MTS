
public class Stop {

	private Integer id;
	private String name;
	private Integer riders;
	private double latitude;
	private double longitude;
	private boolean depot;
	
	public Stop(Integer id, String name, Integer riders, double latitude, double longitude) {
		this.id = id;
		this.name = name;
		this.riders = riders;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRiders() {
		return riders;
	}

	public void setRiders(Integer riders) {
		this.riders = riders;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public boolean isDepot() {
		return depot;
	}
}
