import java.util.ArrayList;

public class Simulator implements Cloneable{

	private int simTime;
	private ArrayList<Event> adjustments;
	private ArrayList<Event> events;
	private ArrayList<Bus> buses;
	private ArrayList<Passenger> passengers;
	private ArrayList<Route> routes;
	private ArrayList<Depot> depots;
	private ArrayList<Stop> stops;
	private ArrayList<Simulator> pastStates;
	
	public static final int ITERATIONS = 20;
	
	public final double SPEED = 1.0;
	public final double WAITING = 1.0;
	public final double CAPACITY = 1.0;
	public final double COMBINED = 1.0;
	public final double BUSES = 1.0;
	
	// This constructor is for testing purposes
	public Simulator() {
		this.simTime = 0;
		this.events = new ArrayList<Event>();
		this.buses = new ArrayList<Bus>();
		this.passengers = new ArrayList<Passenger>();
		this.routes = new ArrayList<Route>();
		this.stops = new ArrayList<Stop>();
		this.depots = new ArrayList<Depot>();
		this.adjustments = new ArrayList<Event>();
		this.pastStates = new ArrayList<Simulator>();
	}
	
	public Simulator(ArrayList<String> instructions) {
		this.simTime = 0;
		this.events = new ArrayList<Event>();
		this.buses = new ArrayList<Bus>();
		this.passengers = new ArrayList<Passenger>();
		this.routes = new ArrayList<Route>();
		this.stops = new ArrayList<Stop>();
		this.depots = new ArrayList<Depot>();
		this.adjustments = new ArrayList<Event>();
		this.pastStates = new ArrayList<Simulator>();
		
		if (instructions.isEmpty()) {
			Debug.print("You need to input instructions for the simulator");
		}
		
		for (String instruction : instructions) {
			parseInstruction(instruction);
		}
	}
	
	public Integer getSimTime() {
		return simTime;
	}

	public void setSimTime(Integer simTime) {
		this.simTime = simTime;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}

	public ArrayList<Bus> getBuses() {
		return buses;
	}

	public void setBuses(ArrayList<Bus> buses) {
		this.buses = buses;
	}

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}

	public ArrayList<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(ArrayList<Route> routes) {
		this.routes = routes;
	}

	public ArrayList<Stop> getStops() {
		return stops;
	}

	public void setStops(ArrayList<Stop> stops) {
		this.stops = stops;
	}

	public static int getIterations() {
		return ITERATIONS;
	}
	
	public Route getRouteByNumber(Integer number) {
		if (routes.isEmpty()) {
			Debug.print("No routes defined.  You need to add routes!");
			return null;
		}
		
		for (Route route: routes) {
			if (route.getNumber() == number) {
				return route;
			}
		}
		
		System.out.printf("No routes found with number %d", number).println();
		return null;
	}
	
	public Route getRouteById(Integer id) {
		if (routes.isEmpty()) {
			Debug.print("No routes defined.  You need to add routes!");
			return null;
		}
		
		for (Route route: routes) {
			if (route.getId() == id) {
				return route;
			}
		}
		
		System.out.printf("No routes found with id %d", id);
		return null;
	}
	
	public Stop getStopById(Integer id) {
		if (stops.isEmpty()) {
			Debug.print("No stops defined.  You need to add stops!");
			return null;
		}
		
		for (Stop stop: stops) {
			if (stop.getId() == id) {
				return stop;
			}
		}
		
		System.out.printf("No stops found with id %d", id).println();
		return null;
	}
	
	public Bus getBusById(Integer id) {
		if (buses.isEmpty()) {
			Debug.print("No buses defined.  You need to add buses!");
			return null;
		}
		
		for(Bus bus: buses) {
			if (bus.getId() == id) {
				return bus;
			}
		}
		
		System.out.printf("No buses found with id %d", id).println();
		return null;
	}
	
	public ArrayList<Event> getEventsByTime(int time) {
		ArrayList<Event> temp = new ArrayList<Event>();
		for (Event event : events) {
			if (event.getTime() == time) {
				temp.add(event);
			}
		}
		return temp;
	}
	
	public ArrayList<Event> getAdjustmentsForBus(int id) {
		ArrayList<Event> temp = new ArrayList<Event>();
		for (Event event : adjustments) {
			if (Integer.parseInt(event.getArgs()[0]) == id) {
				temp.add(event);
			}
		}
		return temp;
	}
	
	public void addBus(String[] args) {
		if (args.length != 9) {
			System.out.printf("Incorrect number of args passed to cmd: %s\n", args[0]);
			return;
		}
		
		int id = Integer.parseInt(args[1]);
		Route route = getRouteById(Integer.parseInt(args[2]));
		int routeIndex = Integer.parseInt(args[3]);
		int passengerCount = Integer.parseInt(args[4]);
		int maxCapacity = Integer.parseInt(args[5]);
		int fuelLevel = Integer.parseInt(args[6]);
		int fuelCapacity = Integer.parseInt(args[7]);
		int speed = Integer.parseInt(args[8]);
		
		Bus bus = new Bus(id, route, routeIndex, passengerCount, maxCapacity, fuelLevel, fuelCapacity, speed);
		buses.add(bus);
	}
	
	public void addDepot(String[] args) {
		if (args.length != 5) {
			System.out.printf("Incorrect number of args passed to cmd: %s", args[0]).println();
			return;
		}
		int id = Integer.parseInt(args[1]);
		String name = args[2];
		double latitude = Double.parseDouble(args[3]);
		double longitude = Double.parseDouble(args[4]);
		
		Depot depot = new Depot(id, name, latitude, longitude);
		depots.add(depot);
	}
	
	public void addEvent(String[] args) {
		if (args.length != 4) {
			System.out.printf("Incorrect number of args passed to cmd: %s", args[0]).println();
			return;
		}
		int time = Integer.parseInt(args[1]);
		Event.EventType type	= Event.EventType.valueOf(args[2]);
		String[] eventArgs 	= new String[args.length - 3];
		for(int i = 3; i < args.length; i++) {
			eventArgs[i-3] = args[i];
		}
		Event event = new Event(time, type, eventArgs);
		events.add(event);
	}
	
	public void addRoute(String[] args) {
		if (args.length != 4) {
			System.out.printf("Incorrect number of args passed to cmd: %s", args[0]).println();
			return;
		}
		int id = Integer.parseInt(args[1]);
		int number = Integer.parseInt(args[2]);
		String name = args[3];
		
		Route route = new Route(id, number, name);
		routes.add(route);
	}
	
	public void addStop(String[] args) {
		if (args.length != 6) {
			System.out.printf("Incorrect number of args passed to cmd: %s", args[0]).println();
			return;
		}
		int id = Integer.parseInt(args[1]);
		String name = args[2];
		int riders = Integer.parseInt(args[3]);
		double latitude = Double.parseDouble(args[4]);
		double longitude = Double.parseDouble(args[5]);
		
		Stop stop = new Stop(id, name, riders, latitude, longitude);
		stops.add(stop);
	}
	
	public void extendRoute(String[] args) {
		if (args.length != 3) {
			System.out.printf("Incorrect number of args passed to cmd: %s", args[0]).println();
			return;
		}
		int routeId = Integer.parseInt(args[1]);
		int stopId = Integer.parseInt(args[2]);
		
		Route route = getRouteById(routeId);
		Stop stop = getStopById(stopId);
		
		if (route != null && stop != null) {
			route.addStop(stop);
		}
	}
	
	public void adjustPassengerCapacity(Event event) {
		
		int busId = Integer.parseInt(event.getArgs()[0]);
		Bus bus = getBusById(busId);
		if (bus == null) {
			System.out.printf("Unable to find bus with id %d", busId);
			return;
		}
		
		int newPassengerCapacity = Integer.parseInt(event.getArgs()[1]);
		bus.setMaxCapacity(newPassengerCapacity);
	}
	
	public void adjustBusSpeed(Event event) {
		
		int busId = Integer.parseInt(event.getArgs()[0]);
		Bus bus = getBusById(busId);
		if (bus == null) {
			System.out.printf("Unable to find bus with id %d", busId);
			return;
		}
		
		int newBusSpeed = Integer.parseInt(event.getArgs()[1]);
		bus.setMaxCapacity(newBusSpeed);
	}
	
	public void adjustBusRoute(Event event) {
		
		int busId = Integer.parseInt(event.getArgs()[0]);
		Bus bus = getBusById(busId);
		if (bus == null) {
			System.out.printf("Unable to find bus with id %d", busId);
			return;
		}
		
		Route route = getRouteById(Integer.parseInt(event.getArgs()[1]));
		int routeIndex = Integer.parseInt(event.getArgs()[2]);
		bus.setRoute(route);
		bus.setRouteIndex(routeIndex%route.stopList().size());
	}
	
	public void moveBus(Event event) {
		
		int busId = Integer.parseInt(event.getArgs()[0]);
		Bus bus = getBusById(busId);
		if (bus == null) {
			System.out.printf("Unable to find bus with id %d", busId);
			return;
		}
		
		ArrayList<Event> changes = getAdjustmentsForBus(busId);
		changes.sort(new SortEvents());
		for (Event change : changes) {
			handleEvent(change);
		}
		
		// Initialize print vars
		int stopId = bus.getNextStop().getId();
		int time = bus.getArrivalTime();
		
		System.out.printf("b:%d->s:%d@%d//p:%d/f:%d", busId, stopId, time, 0, 0).println();
		
		// Add next move_bus event for bus
		Event newEvent = new Event(time, Event.EventType.move_bus, event.getArgs());
		events.add(newEvent);
		
		// Move the bus
		bus.moveBus();
	}
	
	public void broadcastTime() {
		for (Bus bus: buses) {
			bus.setCurrentTime(getSimTime());
		}
	}
	
	public void handleEvent(Event event) {
		String type = event.getType().toString();
		switch (type) {
			case "move_bus":
				moveBus(event);
				break;
			case "adjust_passenger_count":
				adjustPassengerCapacity(event);
				break;
			case "adjust_bus_speed":
				adjustBusSpeed(event);
				break;
			case "adjust_bus_route":
				adjustBusRoute(event);
				break;
			default: 
				System.out.printf("%s is not a valid event", type).println();
				break;
		}		
	}
	
	public void processEvents(Integer loopLimit) throws CloneNotSupportedException {
		Debug.print("Starting event loop");
		int iterations = 0;
		while(iterations < loopLimit && !events.isEmpty()) {
			
			// Sort the event queue
			events.sort(new SortEvents());
			
			// Handle all events at the current time
			ArrayList<Event> eventsAtTime = getEventsByTime(simTime);
			for (Event event : eventsAtTime) {				
				handleEvent(event);
				iterations++;
				
				// save the current sim state
				Simulator simState = clone();
				saveState(simState);
				
				if (iterations >= loopLimit) {
					break;
				}
			}
			
			simTime++;
			
			broadcastTime();
		}
		Debug.print("Finishing event loop");
	}
	
	public ArrayList<Simulator> getPastStates() {
		return pastStates;
	}

	public void setPastStates(ArrayList<Simulator> pastStates) {
		this.pastStates = pastStates;
	}

	public void parseInstruction(String line) {
		String[] cmdArgs = line.split(",");
		String action = cmdArgs[0];
		Debug.print("Processing " + line + " with args length " + cmdArgs.length);
		switch (action) {
			case "add_bus":
				addBus(cmdArgs);
				break;
			case "add_depot":
				addDepot(cmdArgs);
				break;
			case "add_event":
				addEvent(cmdArgs);
				break;
			case "add_route":
				addRoute(cmdArgs);
				break;
			case "add_stop":
				addStop(cmdArgs);
				break;
			case "extend_route":
				extendRoute(cmdArgs);
				break;
			default:
				System.out.printf("%s is not an available command", action);
				break;
		}
	}
	
	public double waitingPassengers() {
		int totalWaitingPassengers = 0;
		for (Stop stop : stops) {
			totalWaitingPassengers += stop.getRidersWaitingCount();
		}
		return totalWaitingPassengers;
	}
	
	public double busCost() {
		double totalBusCost = 0;
		for (Bus bus : buses) {
			totalBusCost += ((this.SPEED * bus.getSpeed()) + (this.CAPACITY * bus.getMaxCapacity()));
		}
		return totalBusCost;
	}
	
	public double systemEfficiency() {
		return (this.WAITING * waitingPassengers()) + (this.BUSES * busCost()) + (this.COMBINED * waitingPassengers() * busCost());
	}
	
	public Simulator clone() throws CloneNotSupportedException {
		return (Simulator) super.clone();
	}
	
	public void saveState(Simulator simState) {
		if (pastStates.size() < 3) {
			pastStates.add(simState);
		} else {
			pastStates.remove(0);
			pastStates.add(simState);
		}
	}
	
	public void rewind (int pastState) throws InstantiationException, IllegalAccessException {
		Runtime run = Runtime.getRuntime();
		System.out.println(this);
		if (pastState > 0 && pastState < 3) {
			Simulator sim = pastStates.get(pastState-1);
			System.out.println(sim);
		}
	}
}
