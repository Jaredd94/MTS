import java.util.ArrayList;
import java.util.Random;

public class Stop {

	private int id;
	private String name;
	private double latitude;
	private double longitude;
	private ArrayList<Passenger> waiting;
	private ArrayList<Passenger> transfers;
	
	private final int ridersArriveHigh = 10;
	private final int ridersArriveLow = 0;
	private final int ridersDepartHigh = 10;
	private final int ridersDepartLow = 0;
	private final int ridersOnHigh = 10;
	private final int ridersOnLow = 0;
	private final int ridersOffHigh = 10;
	private final int ridersOffLow = 0;
		
	public Stop(int id, String name, int riders, double latitude, double longitude) {
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		waiting = new ArrayList<Passenger>();
		transfers = new ArrayList<Passenger>();
		arriveAtStop(riders);
	}

	public int getId() {
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
	
	public ArrayList<Passenger> getWaiting() {
		return waiting;
	}

	public void setWaiting(ArrayList<Passenger> waiting) {
		this.waiting = waiting;
	}

	public ArrayList<Passenger> getTransfers() {
		return transfers;
	}

	public void setTransfers(ArrayList<Passenger> transfers) {
		this.transfers = transfers;
	}
	
	public int getRidersWaitingCount() {
		return waiting.size();
	}
	
	public int ridersArrive() {
		Random rand = new Random();
		return rand.nextInt(ridersArriveHigh - ridersArriveLow) + ridersArriveLow;
	}
	
	public int ridersDepart() {
		Random rand = new Random();
		return rand.nextInt(ridersDepartHigh - ridersDepartLow) + ridersDepartLow;
	}
	
	public int ridersOn() {
		Random rand = new Random();
		return rand.nextInt(ridersOnHigh - ridersOnLow) + ridersOnLow;
	}
	
	public int ridersOff() {
		Random rand = new Random();
		return rand.nextInt(ridersOffHigh - ridersOffLow) + ridersOffLow;
	}
	
	public void addPassengers(ArrayList<Passenger> list, int riders) {
		for (int i = 0; i < riders; i++) {
			Passenger passenger = new Passenger();
			list.add(passenger);
		}
	}
	
	public void removePassengers(ArrayList<Passenger> list, int riders) {
		for (int i = 0; i < riders; i++) {
			if (list.isEmpty( )) {
				break;
			}
			list.remove(0);
		}
	}
	
	public void movePassengersToTransfers(ArrayList<Passenger> src, int riders) {
		if (riders > src.size()) {
			riders = src.size();
		}
		
		for (int i = 0; i < riders; i++) {
			Passenger passenger = src.remove(0);
			transfers.add(passenger);
		}
	}
	
	public void movePassengersToWaiting(ArrayList<Passenger> src, int riders) {
		if (riders > src.size()) {
			riders = src.size();
		}
		
		for (int i = 0; i < riders; i++) {
			Passenger passenger = src.remove(0);
			waiting.add(passenger);
		}
	}
	
	public void arriveAtStop(int ridersArrive) {
		addPassengers(waiting, ridersArrive);
	}
	
	public void departStop(int ridersDepart) {
		if (ridersDepart <= transfers.size()) {
			removePassengers(transfers, ridersDepart);
			movePassengersToWaiting(transfers, transfers.size());
		} else {
			int diff = ridersDepart - transfers.size();
			removePassengers(transfers, transfers.size());
			removePassengers(waiting, diff);
		}
	}
}
