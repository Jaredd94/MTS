import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	@SuppressWarnings({ "resource"})
	public static void main (String[] args) throws IOException {
		
		System.out.println("Start Simulator!");
		File inputFile = new File(args[0]);
		System.out.println("Found File: " + inputFile.getAbsolutePath());

		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		
		ArrayList<String> instructions = new ArrayList<String>();
		
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			instructions.add(line);
			
		}
		
		Simulator sim = new Simulator(instructions);

		System.out.println("Processing events!");
		sim.processEvents(Simulator.ITERATIONS);
	}
}
