// S Dinesh Raj
// A0227252L 
import java.lang.*;
import java.io.*;
import java.util.*;


public class Work {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		
		Condition1 c1 = new Condition1();
		PriorityQueue<Researcher> r_q = new PriorityQueue<>(c1);
		
		for(int i = 0; i < n; i++) {
			String[] in = br.readLine().split(" ");
			r_q.add(new Researcher(
				Integer.parseInt(in[0]),
				Integer.parseInt(in[1])));
		}

		Condition2 c2 = new Condition2();
		PriorityQueue<Machine> m_q = new PriorityQueue<>(c2);
		
		Researcher r = r_q.poll();
		m_q.add(new Machine(r.get_en(),r.get_en() + m));

		int result = 0;

		while (!r_q.isEmpty()) {
			Researcher next = r_q.poll();
			if (!m_q.isEmpty()) {
				int time =  next.get_st();
				Machine first = m_q.peek();
				if (time >= first.get_op() && time <= first.get_cl()) {
					m_q.poll();
					m_q.add(new Machine(next.get_en(),
						next.get_en() + m));
					result+=1;
				} else if (time >= first.get_op() && !(time <= first.get_cl())) {
					m_q.poll();
					r_q.add(next);
				} else {
					m_q.add(new Machine(next.get_en(),
						next.get_en() + m));
				}
			} else {
				m_q.add(new Machine(next.get_en(),
						next.get_en() + m));
			}
        }

		pw.println(result);
		pw.close();
		br.close();
	}

	public static class Researcher {
		int start;
		int end;

		Researcher(int x, int y) {
			this.start = x;
			this.end = x + y;
		}

		int get_st() { return start;} 
		int get_en() { return end;}
	}

	public static class Machine {
		int open;
		int close;

		Machine(int x, int y) {
			this.open = x;
			this.close = y;
		}

		int get_op() { return open;}
		int get_cl() { return close;}
	}

	public static class Condition1 implements Comparator<Researcher> {
        @Override
        public int compare(Researcher r1, Researcher r2) {
            return r1.get_st() > r2.get_st() ? 1 : -1;
        }
    }

    public static class Condition2 implements Comparator<Machine> {
        @Override
        public int compare(Machine m1, Machine m2) {
            return m1.get_op() > m2.get_op() ? 1 : -1;
        }
    }
}