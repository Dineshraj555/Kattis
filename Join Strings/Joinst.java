// S Dinesh Raj
// A0227252L 
import java.lang.*;
import java.io.*;
import java.util.*;

public class Joinst {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int no_str = Integer.parseInt(br.readLine());
		
		Node[] words = new Node[no_str];
		for(int i = 0; i < no_str; i++) {
            words[i] = new Node(br.readLine());
		}

		int no_op = no_str - 1;
		int in1 = 0; 
		int in2 = 0;
		int last = 0;

		for(int i = 0; i < no_op; i++) {
			String[] op_line = br.readLine().split(" ");
			in1 = Integer.parseInt(op_line[0]) - 1;
		    in2 = Integer.parseInt(op_line[1]) - 1;
		    words[in1].add(words[in2]);
		    last = in1;
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		Node output = words[last];
		while(output!= null) {
			pw.print(output.getItem());
			output = output.getNext();
		}

		pw.close();

	}

	public static class Node {

		public String item;
		public Node next;
		public Node tail;

		public Node(String val) { 
			this.item = val;
			this.next = null;
			this.tail = null;
		}

		public String getItem() { return item; }
		public Node getNext() { return next; }
		public Node getTail() { return tail; }

		public void setNext(Node n) { next = n; }
		public void setTail(Node n) { tail = n; }

		public void add(Node in) {
			if (this.getNext() == null && in.getNext() == null) {
				setNext(in);
				setTail(getNext());
			} else if (this.getNext() == null && in.getNext() != null) {
				setNext(in);
				setTail(in.getTail());
			} else if (this.getNext() != null && in.getNext() == null) {
				getTail().setNext(in);
				setTail(getTail().getNext());
			} else {
				getTail().setNext(in);
				setTail(in.getTail());
			}
		}

	}

}