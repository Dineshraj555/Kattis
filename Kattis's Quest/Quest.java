// S Dinesh Raj
// A0227252L 
import java.io.*;
import java.util.*;


public class Quest {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int no = Integer.parseInt(br.readLine());

		TreeMap<Integer, PriorityQueue<Integer>> tm = new TreeMap<>();

		for(int i = 0; i < no; i++) {
			String[] input = br.readLine().split(" ");
			String cmd = input[0];
			
			if(cmd.equals("add")) {
				
				int e = Integer.parseInt(input[1]);
			    int g = Integer.parseInt(input[2]);

				if (tm.isEmpty() || !tm.containsKey(e)) {
					PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Comparator.reverseOrder());
					pq.add(g);
					tm.put(e, pq);
				} else {
					PriorityQueue<Integer> pq = tm.get(e);
					pq.add(g);
					tm.put(e, pq);
				}

			} else {

				int val = Integer.parseInt(input[1]);
				long gold = 0;

				Integer key = tm.floorKey(val);

				while(key != null) {
					val -= key;
					gold += tm.get(key).poll();
					if(tm.get(key).isEmpty()) {
						tm.remove(key);
					}
					key = tm.floorKey(val);
				}

				System.out.println(gold);
			}
		}

		br.close();
	}
}