
public class Debug {
	public static boolean on = false;
	public static void print(String string) {
		if (on) {
			System.out.println(string);
		}
	}
}
