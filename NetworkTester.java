package assignment3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NetworkTester {
	static List<Float> pingResponceList = new ArrayList<Float>();

	static public  void calculateMean() {
		/* calculates the median by sort the array and find the middle element for median */
		Collections.sort(pingResponceList);
		int pingresponcelistlenght = pingResponceList.size();
		if (pingresponcelistlenght % 2 == 0) {
			System.out.println("median is" + (pingResponceList.get(pingresponcelistlenght / 2 - 1)
					+ pingResponceList.get(pingresponcelistlenght / 2 - 1)) / 2);
		} else {
			System.out.println("median is" + pingResponceList.get((pingresponcelistlenght - 1) / 2));
		}
	}

	static public void ping(String ipadress) {
		try {
			/*run the command ping in runtime and pass the output stream to BufferedReader*/
			Process p = Runtime.getRuntime().exec("ping " + ipadress);
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String s = "";
			while ((s = inputStream.readLine()) != null) {
				if (s.contains("time")) {
					/* convert the time in ping response to float and add to arraylist */
					float f = Float.parseFloat(s.substring(s.indexOf("time") + 5, s.indexOf("ms") - 1));
					pingResponceList.add(f);
					System.out.println(f);
					calculateMean();	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ping("8.8.8.8");
	}
}