package assignment3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NetworkTester {

	static List < Float > pingResponceList = new ArrayList < Float > ();

	/* calculates the median by sort the array and find the middle element for median */

	static public void calculateMean() {

		Collections.sort(pingResponceList);
		int pingresponcelistlenght = pingResponceList.size();
		if (pingresponcelistlenght % 2 == 0) {
			System.out.println("median is" + (pingResponceList.get(pingresponcelistlenght / 2 - 1) + pingResponceList.get(pingresponcelistlenght / 2 - 1)) / 2);
		} else {
			System.out.println("median is" + pingResponceList.get((pingresponcelistlenght - 1) / 2));
		}
	}

	/*calculates median given an ip address*/
	static public void ping(String ipadress) {

		try {
			/*runs the command ping in runtime and pass the output stream to BufferedReader*/
			Process p = Runtime.getRuntime().exec("ping " + ipadress);
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String s = "";
			int count = 0;
			while ((s = inputStream.readLine()) != null) {
				if (s.contains("time")) {
					/* converts the time in ping response to float and add to arraylist */
					float f = Float.parseFloat(s.substring(s.indexOf("time") + 5, s.indexOf("ms") - 1));
					if (count >= 0 && count <= 10) {
						pingResponceList.add(f);
						if (count == 10) {
							calculateMean();
						}
					}
				}
				count++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		System.out.println("enter ip address:");
		Scanner scanner = new Scanner(System. in );
		String ipAddress = scanner.next();
		System.out.println("please wait ....");
		ping(ipAddress);

	}
}