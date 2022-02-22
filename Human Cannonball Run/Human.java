// S Dinesh Raj
// A0227252L 
import java.io.*;
import java.util.*;


public class Human {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		// start
		String[] line1 = br.readLine().split(" ");
		double x1 = Double.parseDouble(line1[0]);
		double y1 = Double.parseDouble(line1[1]);

		// end
		String[] line2 = br.readLine().split(" ");
		double x2 = Double.parseDouble(line2[0]);
		double y2 = Double.parseDouble(line2[1]);

		// cannon 
		int n = Integer.parseInt(br.readLine());
		double[][] coor = new double[n][2];
		int INF = 1000000000;

		for(int i = 0; i < n; i++){
			String[] input = br.readLine().split(" ");
			coor[i][0] = Double.parseDouble(input[0]);
			coor[i][1] = Double.parseDouble(input[1]);
		}

		// time matrix 
		// timing point to point 
		int v = n+2;
		double[][] time = new double[v][v];

		for (int k = 0; k < v; k++) {
			for (int j = 0; j < v; j++) {
				if (k == j) { time[k][j] = 0;} // diagonal 
				else if (k == 0) {             // run from start to all cannon
					if (j!= 0 && j < v - 1) {
						time[0][j] = dist(x1,y1,coor[j - 1][0],coor[j - 1][1],0) / 5; 
					} else if (j == v - 1) {   // run to end
						time[0][j] = dist(x1,y1,x2,y2,0) / 5;
					}
				} else { time[k][j] = INF;}    // initialize to INF 
			}
		}

		// find min time; run vs cannon
		for(int k = 1; k < v - 1; k++) {
			for(int j = 1; j < v; j++) {
				if (j == v - 1) {
					time[k][j] = Math.min(2 + Math.abs(dist(coor[k - 1][0], coor[k - 1][1], x2, y2, 50)) / 5,
						                  dist(coor[k - 1][0], coor[k - 1][1], x2, y2, 0) / 5);
				} else {
					time[k][j] = Math.min(2 + Math.abs(dist(coor[k - 1][0], coor[k - 1][1], coor[j - 1][0], coor[j - 1][1], 50)) / 5,
						                  dist(coor[k - 1][0], coor[k - 1][1], coor[j - 1][0], coor[j - 1][1],0) / 5);
				}
			}
		}

		// Floyd Warshall's
		for(int k = 0; k < v; k++) {
			for(int i = 0; i < v; i++) {
				for(int j = 0; j < v; j++) {
					time[i][j] = Math.min(time[i][j], 
						                  time[i][k] + time[k][j]);
				}
			}
		}

		// best time 
		double result = time[0][v-1];

		pw.println(result);
		pw.close();
		br.close();
	}

	public static double dist(double x1, double y1, double x2, double y2, double z) {
		double X = Math.pow(x1 - x2, 2);
		double Y = Math.pow(y1 - y2, 2);
		return Math.sqrt(X + Y) - z;
	}
}