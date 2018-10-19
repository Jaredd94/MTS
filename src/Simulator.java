import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class Simulator {

	private Integer simTime;
	private ArrayList<Event> events;
	private ArrayList<Bus> buses;
	private ArrayList<Passenger> passengers;
	private ArrayList<Route> routes;
	private ArrayList<Depot> depots;

	private ArrayList<Stop> stops;
	
	public static final int ITERATIONS = 20;
	
	// This constructor is for testing purposes
	public Simulator() {
		this.simTime = 1;
		this.events = new ArrayList<Event>();
		this.buses = new ArrayList<Bus>();
		this.passengers = new ArrayList<Passenger>();
		this.routes = new ArrayList<Route>();
		this.stops = new ArrayList<Stop>();
	}
	
	// Main constructor we will use
	public Simulator(ArrayList<String> instructions) {
		if (instructions.isEmpty()) {
			System.out.println("You need to input instructions for the simulator");
		}
		
		for (String instruction : instructions) {
			parser(instruction);
		}
		
		this.simTime = 1;
		this.events = new ArrayList<Event>();
		this.buses = new ArrayList<Bus>();
		this.passengers = new ArrayList<Passenger>();
		this.routes = new ArrayList<Route>();
		this.stops = new ArrayList<Stop>();
	}
	
	// Parse command and handle with proper call
	public void parser(String line) {
		String[] cmd_opts = line.split(",");
		
		String action = cmd_opts[0];
		
		switch (action) {
			case "add_bus":
			case "add_depot":
			case "add_event":
			case "add_route":
			case "add_stop":
			case "extend_route":
		}
	}
	
	public void addBus(String[] args) {
		Integer id 				= Integer.parseInt(args[0]);
		Route route 				= findRouteByNumber(Integer.parseInt(args[1]));
		Double latitude 			= Double.parseDouble(args[2]);
		Double longitude 		= Double.parseDouble(args[3]);
		Integer passengerCount 	= Integer.parseInt(args[4]);
		Integer maxCapacity 		= Integer.parseInt(args[5]);
		Integer fuelLevel 		= Integer.parseInt(args[6]);
		Integer speed 			= Integer.parseInt(args[7]);
		
		// TODO
		// Need to find the correct route index in route
		// Need to create the bus object
	}
	
	public void addDepot(String[] args) {
		Integer id		= Integer.parseInt(args[0]);
		String name		= args[1];
		Double latitude 	= Double.parseDouble(args[2]);
		Double longitude	= Double.parseDouble(args[3]);
		
		Depot depot = new Depot(id, name, latitude, longitude);
		depots.add(depot);
	}
	
	public void addEvent(String[] args) {
		Integer time 	= Integer.parseInt(args[0]);
		String event		= args[1];
		Integer id 		= Integer.parseInt(args[2]);
	}
	
	public void addRoute(String[] args) {
		Integer id 		= Integer.parseInt(args[0]);
		Integer number	= Integer.parseInt(args[1]);
		String name 		= args[2];
		
		Route route = new Route(id, number, name);
		routes.add(route);
	}
	
	public void addStop(String[] args) {
		// add_stop id name rider_count latitude longitude
	}
	
	public void extendRoute(String[] args) {
		// extend_route route_id stop_id
	}
	
	public void processEvents(Integer timeLimit) {
		System.out.println("Starting event loop");
		while(simTime < timeLimit && !events.isEmpty()) {
			
			// sort the event queue
			events.sort(new SortEvents());
			
			// Choose new event
			Event event = events.remove(0);
			
			// Execute event
			System.out.println(event.getType() + " at time " + event.getTime());
			
			// Add new event to queue if needed
			
			// increment simTime
			simTime++;
		}
		System.out.println("Finishing event loop");
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
	
	// TODO
	public Route findRouteByNumber(Integer name) {
		if (getRoutes().isEmpty()) {
			System.out.println("No routes defined.  You need to add routes!");
			return null;
		}
		
		for (Route route: getRoutes()) {
			if (route.getNumber().equals(name)) {
				return route;
			}
		}
		
		return null;
	}
}
