import java.util.ArrayList;

public class Bus {
	private int id;
	private int routeIndex;
	private int passengerCount;
	private int maxCapacity;
	private int fuelLevel;
	private int fuelCapacity;
	private int speed;
	private int currentTime;
	
	private Route route;
	private Stop currStop;
	private ArrayList<Passenger> riders;
	
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
		this.currentTime = 0;
		this.currStop = this.route.stopList().get((routeIndex)%route.stopList().size());
		this.riders = new ArrayList<Passenger>();
		for (int i = 0; i < passengerCount; i++) {
			if (riders.size() < maxCapacity) {
				this.riders.add(new Passenger());
			} else {
				break;
			}	
		}
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

	public void updatePassengerCount() {
		passengerCount = riders.size();
	}
	
	public int getPassengerCount() {
		return passengerCount;
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
	
	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}
	
	public Stop getCurrStop() {
		return currStop;
	}

	public Stop getNextStop() {
		return route.stopList().get((routeIndex+1)%route.stopList().size());
	}
	
	private double getDistance(Stop curr, Stop dst) {
		double currX = curr.getLatitude();
		double currY = curr.getLongitude();
		double dstX = dst.getLatitude();
		double dstY = dst.getLongitude();
		double distance = Bus.CONVERSION_FACTOR * Math.sqrt(Math.pow((currX - dstX),2) + Math.pow((currY - dstY),2));
		Debug.print("Distance Calculated: " + distance + "Miles");
		return distance;
	}
	
	private double getDistanceToNextStop() {
		Stop currStop = getCurrStop();
		Stop nextStop = getNextStop();
		return getDistance(currStop, nextStop);
	}
	
	private int calculateTravelTime(Double distance) {
		return 1 + ((distance.intValue() * Bus.MINUTES) / getSpeed());
	}
	
	public int getArrivalTime() {
		return currentTime + calculateTravelTime(getDistanceToNextStop());
	}
	
	public boolean isFull() {
		return (passengerCount >= maxCapacity);
	}
	
	public void moveBus() {
		
		/* Any user adjustments that happened 
		 * when this bus was on its way to the current stop
		 * have been changed at this point
		 */
		
		// New passengers arrive at the stop
		int ridersArrive = currStop.ridersArrive();
		currStop.arriveAtStop(ridersArrive);
		
		// Riders get off the bus
		broadcastExitBus();
		
		// Riders get on the bus
		broadcastBoardBus();
		
		// Passengers leave
		int ridersDepart = currStop.ridersDepart();
		currStop.departStop(ridersDepart);
		
		// Adjust passenger variables accordingly
		routeIndex = (routeIndex + 1)%route.stopList().size();
		currStop = route.stopList().get((routeIndex+1)%route.stopList().size());
	}
	
	public void broadcastExitBus() {
		int ridersOff = currStop.ridersOff();
		
		// If the number of passengers after ridersOff is still > maxCapacity 
		if ((passengerCount - ridersOff) > this.maxCapacity) {
			ridersOff += ((passengerCount - ridersOff) - maxCapacity);
		}
		
		currStop.movePassengersToTransfers(riders, ridersOff);
		updatePassengerCount();
	}
	
	public void broadcastBoardBus() {
		int ridersOn = currStop.ridersOn();
		
		if (isFull()) { return; }
		
		// If the number of passengers boarding exceeds the max capacity
		if ((passengerCount + ridersOn) > maxCapacity) {
			ridersOn = maxCapacity - passengerCount;
		}
		
		movePassengersToBus(currStop.getWaiting(), ridersOn);
		updatePassengerCount();
	}
	
	public void movePassengersToBus(ArrayList<Passenger> src, int ridersOn) {
		if (ridersOn > src.size()) {
			ridersOn = src.size();
		}
		
		for (int i = 0; i < ridersOn; i++) {
			Passenger passenger = src.remove(0);
			riders.add(passenger);
		}
	}
}
