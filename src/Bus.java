import java.util.ArrayList;

public class Bus {
	private Integer id;
	private String name;
	private Integer routeIndex;
	private Integer passengerCount;
	private Integer maxCapacity;
	private Integer speed;
	private Route route;
	
	public static final int CONVERSION_FACTOR = 70;
	public static final int MINUTES = 60;

	public Bus(Integer id, String name, Route route, Integer routeIndex, Integer passengerCount, Integer maxCapacity, Integer speed) {
		this.id = id;
		this.name = name;
		this.route = route;
		this.routeIndex = routeIndex;
		this.passengerCount = passengerCount;
		this.maxCapacity = maxCapacity;
		this.speed = speed;
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

	public Integer getRouteIndex() {
		return routeIndex;
	}

	public void setRouteIndex(Integer routeIndex) {
		this.routeIndex = routeIndex;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
	
	public Stop getNextStop() {
		return route.getStop((routeIndex+1)%(route.stopList().size()));
	}
	
	public Stop getCurrStop() {
		return route.getStop(routeIndex);
	}
	
	public Boolean isFull() {
		return (passengerCount == maxCapacity);
	}
	
	public Integer calculateTravelTime(Double distance) {
		return 1 + (distance.intValue() * Bus.MINUTES / this.getSpeed());
	}
	
	public double getDistance(Stop curr, Stop dst) {
		double currX = curr.getLatitude();
		double currY = curr.getLongitude();
		double dstX = dst.getLatitude();
		double dstY = dst.getLongitude();
		double distance = Bus.CONVERSION_FACTOR * Math.sqrt(Math.pow((currX - dstX),2) + Math.pow((currY - dstY),2));
		System.out.printf("Distance Calculated: %.2f Miles", distance);
		return distance;
	}
	
	// TODO
	public void broadcastExit() {
		System.out.println("Is anyone getting off?");
	}
	
	// TODO
	public void broadcastBoarding() {
		System.out.println("Is anyone getting on?");
	}
	
	// TODO
	public void move_bus() {
		System.out.println("Departing current stop!");
	}
}
