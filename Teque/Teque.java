// S Dinesh Raj
// A0227252L
import java.lang.*;
import java.util.*;
import java.io.*;


public class Teque {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int no_op = Integer.parseInt(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		for(int i = 0; i < no_op; i++) {

			String[] input = br.readLine().split(" ");
		    String cmd = input[0];
		    int num = Integer.parseInt(input[1]);

		    switch (cmd) {
		    	case "push_back":
		    	     tail.put(t_b++, num);
		    	     update();
		    	     break;

		    	case "push_front":
		    	     head.put(h_f--, num);
		    	     update();
		    	     break;

		    	case "push_middle":
		    	     head.put(h_b++, num);
		    	     update();
		    	     break;

		    	case "get":
		    	     pw.println(show(num));
		    	     break;
		    }
		}

		br.close();
		pw.close();
	}

	public static HashMap<Integer,Integer> head = new HashMap<>();
	public static HashMap<Integer,Integer> tail = new HashMap<>();
	public static int h_f = -1;
	public static int t_f = -1;
    public static int h_b = 0;
    public static int t_b = 0;

    public static void update() {
    	if (head.size() < tail.size()) {
    		head.put(h_b++, tail.get(++t_f));
		    tail.remove(t_f);
		} else if (head.size() - 1 > tail.size()) {
		    tail.put(t_f--, head.get(--h_b));
		    head.remove(h_b);
		} else {
			// do nothing
		}
	}

	public static int show(int index){
		if (index < head.size()) {
			return head.get(index + 1 + h_f);
		} else {
			return tail.get(index + 1 + t_f - head.size());
		}
	}

}