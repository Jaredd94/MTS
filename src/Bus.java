import java.util.ArrayList;

public class Bus {
	private int id;
	private int routeIndex;
	private int passengerCount;
	private int maxCapacity;
	private int fuelLevel;
	private int fuelCapacity;
	private int speed;
	private int arrivalTime;
	private Route route;
	
	public static final int CONVERSION_FACTOR = 70;
	public static final int MINUTES = 60;

	public Bus(int id, Route route, int routeIndex, int passengerCount, int maxCapacity, int fuelLevel, int fuelCapacity, int speed) {
		this.id = id;
		this.route = route;
		this.routeIndex = routeIndex;
		this.passengerCount = passengerCount;
		this.maxCapacity = maxCapacity;
		this.fuelLevel = fuelLevel;
		this.fuelCapacity = fuelCapacity;
		this.speed = speed;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getRouteIndex() {
		return routeIndex;
	}


	public void setRouteIndex(int routeIndex) {
		this.routeIndex = routeIndex;
	}


	public int getPassengerCount() {
		return passengerCount;
	}


	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}


	public int getMaxCapacity() {
		return maxCapacity;
	}


	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}


	public int getFuelLevel() {
		return fuelLevel;
	}


	public void setFuelLevel(int fuelLevel) {
		this.fuelLevel = fuelLevel;
	}


	public int getFuelCapacity() {
		return fuelCapacity;
	}


	public void setFuelCapacity(int fuelCapacity) {
		this.fuelCapacity = fuelCapacity;
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public Route getRoute() {
		return route;
	}


	public void setRoute(Route route) {
		this.route = route;
	}


	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public int getArrivalTime() {
		return this.arrivalTime;
	}


	public Boolean isFull() {
		return (passengerCount == maxCapacity);
	}
	
	public int calculateTravelTime(Double distance) {
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
	public void moveBus() {
		System.out.println("Departing current stop!");
	}
}
