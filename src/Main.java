import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	@SuppressWarnings({ "resource"})
	public static void main (String[] args) throws IOException, CloneNotSupportedException, InstantiationException, IllegalAccessException {
		Debug.on = false;
		Debug.print("Start Simulator!");
		File inputFile = new File(args[0]);
		Debug.print("Found File: " + inputFile.getAbsolutePath());
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		ArrayList<String> instructions = new ArrayList<String>();
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			instructions.add(line);
			
		}
		Simulator sim = new Simulator(instructions);
		Debug.print("Processing events!");
		sim.processEvents(Simulator.ITERATIONS);
		sim.rewind(2);
	}
}
