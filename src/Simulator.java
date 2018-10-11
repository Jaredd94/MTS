import java.util.ArrayList;
import java.util.Arrays;

public class Simulator {

	private ArrayList<Bus> buses;
	private ArrayList<Passenger> passengers;
	private ArrayList<Route> routes;
	private ArrayList<Stop> stops;
	
	enum Event {
		move_bus;
	}
	
	public static void main (String[] args) {
		
		System.out.println("Start Simulator!");
		
		
	}
	
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
	
	public void addBus() {
		// add_bus id name route_id route_index passenger_count max_capacity speed
	}
	
	public void addDepot() {
		// add_depot id name latitude longitude
	}
	
	public void addEvent() {
		// add_event time type id
	}
	
	public void addRoute() {
		// add_route id number name
	}
	
	public void addStop() {
		// add_stop id name rider_count latitude longitude
	}
	
	public void extendRoute() {
		// extend_route route_id stop_id
	}
	
	public void invokeEvent() {
		
	}
}
