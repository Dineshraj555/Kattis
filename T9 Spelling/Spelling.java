// S Dinesh Raj
// A0227252L

import java.util.*;

public class Spelling{

	public static void main(String[] args) {

		int in_size;
		String[] output;

		Scanner sc = new Scanner(System.in);

		in_size = sc.nextInt() + 1;
		output = new String[in_size];

		// store inputs in array
		for(int i = 0; i < in_size; i++) {
			output[i] = sc.nextLine();
		}

		// covert and print output
		for(int t = 1; t < in_size; t++) {
			String line = wordtonum(output[t]);
			System.out.println("Case #" + t + ": " + line);
		}

	}

	// function to convert the word to numbers
	private static String wordtonum(String input) {

		// store data in array as per question
		String num[] = {
			"2","22","222",
			"3","33","333",
			"4","44","444",
			"5","55","555",
			"6","66","666",
			"7","77","777","7777",
			"8","88","888",
			"9","99","999","9999",
			"0"
	    };
		
		String out = "";
		// last will store the LSB; at start it will be 3000
		int last = 3000; 
		int size = input.length();

		for(int x = 0; x < size; x++) {

			// find index based of ASCII Table
			int index = input.charAt(x) - 'a';

			// if " " is detected assign to index 26
			if (index == -65) {
				index = 26;
			}
			
			// get LSB
			int in = Integer.valueOf(num[index].substring(0,1));

			// check to see if same number is used
			if (last == in) {
				out = out + " " + num[index];
			} else {
				out += num[index];
				last = in;
			}
		}

		return out;
	}
}