import java.util.Arrays;

public class Simulator {

	private Bus[] buses;
	private Passenger[] passengers;
	private Route[] routes;
	private Stop[] stops;
	
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
	
	public void handle_add_bus() {
		
	}
	
	public void handle_add_depot() {
		
	}
	
	public void handle_add_event() {
		
	}
	
	public void handle_add_route() {
		
	}
	
	public void handle_add_stop() {
		
	}
	
	public void handle_extend_route() {
		
	}
	
	public void invoke_event() {
		
	}
}
