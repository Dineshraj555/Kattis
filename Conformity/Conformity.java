// S Dinesh Raj
// A0227252L 
import java.lang.*;
import java.io.*;
import java.util.*;


public class Conformity {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int no_fro = Integer.parseInt(br.readLine());
		// table to indicate the combo and no students taking it
		HashMap<Set<String>,Integer> table = new HashMap<>();

		for(int i = 0; i < no_fro; i++) {
			String[] in = br.readLine().split(" ");
			// set of input
			Set<String> com = new HashSet<>(); 
			com.add(in[0]);
			com.add(in[1]);
			com.add(in[2]);
			com.add(in[3]);
			com.add(in[4]);

			// check if set exist
			// if yes, increase count by 1
			// if no, add combo to table and indicate 1 student
			if(!table.containsKey(com)){
				table.put(com, 1);
			} else {
				table.put(com,table.get(com) + 1);
			}
		}

		Collection<Integer> num_ls = table.values();
		int most = Collections.max(num_ls);
		int no = Collections.frequency(num_ls, most);
		int result = most * no;

		pw.println(result);
		pw.close();
		br.close();
	}
}