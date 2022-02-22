import java.util.*;

public class Relay {

	public static void main(String[] args) {

		int runner_num;
		Runner[] in_runner;
		ArrayList<Runner> out_runner = new ArrayList<Runner>();
		S_Comparator s_comp = new S_Comparator();
		double time = 81;

		// input 
		Scanner sc = new Scanner(System.in);
		runner_num = sc.nextInt();
		in_runner = new Runner[runner_num];
		
		for(int i = 0; i < runner_num; i++) {

			String name = sc.next();
			double f = sc.nextDouble();
			double s = sc.nextDouble();

			in_runner[i] = new Runner(name,f,s);
		}

		// sort based on second timing
		Arrays.sort(in_runner,s_comp);
		
		// compute timing with each being the first runner
		for(int k = 0; k < runner_num; k++) {

			double tem_time = in_runner[k].getFi();
			ArrayList<Runner> temp_runner = new ArrayList<Runner>();
			temp_runner.add(in_runner[k]);

			// compute team time and create array of team
			for (int y = 0; temp_runner.size() < 4; y++) {
				if (y != k) {
					temp_runner.add(in_runner[y]);
					tem_time += in_runner[y].getSec();
				}
			}

			// store the best timing
			if(tem_time < time) {
				time = tem_time;
				out_runner = temp_runner;
			}
			
		}
		// create final array output
		Object[] output = out_runner.toArray();
		Runner[] final_arr = new Runner[4];

		for(int x = 0; x < 4; x++) {
			final_arr[x] = (Runner) output[x];
		}

		// print output
		System.out.println(time);
		for(int z = 0; z < 4; z++) {
			System.out.println(final_arr[z].getName());
		}

	}

	private static class S_Comparator implements Comparator<Runner> {
		public int compare(Runner r1, Runner r2) {
			double diff = r1.getSec() - r2.getSec();
			if (diff > 0) {
				return 1;
			} else if (diff < 0) {
				return -1;
			} else {
				return 0;
			}
		}

		public boolean equals(Object obj) {
			return this == obj;
		}
	}

	private static class Runner {

		private String name;
		private double f_time;
		private double s_time;

		public Runner(String name, double f_time, double s_time) {
			this.name = name;
			this.f_time = f_time;
			this.s_time = s_time;
		}

		public String getName() {
			return this.name;
		}

		public double getFi() {
			return this.f_time;
		}

		public double getSec() {
			return this.s_time;
		}
	}
}