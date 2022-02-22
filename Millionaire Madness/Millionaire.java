// S Dinesh Raj
// A0227252L 
import java.io.*;
import java.util.*;


public class Millionaire {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		String[] input = br.readLine().split(" ");
		int m = Integer.parseInt(input[0]); // row - length
		int n = Integer.parseInt(input[1]); // column - width

		int[][] grid_w = new int[m][n];
		int[][] grid_v = new int[m][n]; 

		for(int i = 0; i < m; i++){
			String[] row = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				grid_w[i][j] = Integer.parseInt(row[j]);
				grid_v[i][j] = 0;
			}
		}

		InTri source = new InTri(0,0,0);
		PriorityQueue<InTri> pq = new PriorityQueue<InTri>();
		pq.add(source);

		ArrayList<InTri> path = BFS(pq,grid_v,grid_w,m-1,n-1);
		int len = 0;

		for (InTri x : path) {
			if (x.dis() > len) {
				len = x.dis();
			}
		}

		pw.println(len);
		pw.close();
		br.close();
	}

	public static ArrayList<InTri> BFS(PriorityQueue<InTri> pq, int[][] grid_v, int[][] grid_w, int r, int c) {
		
		ArrayList<InTri> result = new ArrayList<InTri>();

		int i = pq.peek().r();
		int j = pq.peek().c();

		while(!pq.isEmpty() && !(i == r && j == c)) {
			
			result.add(pq.poll());

			if (grid_v[i][j] == 0) {
				grid_v[i][j] = 1;

				if(i < r) {
					int k = grid_w[i+1][j] - grid_w[i][j]; 
					if (k < 0) {
						pq.add(new InTri(i+1,j,0));
					} else {
						pq.add(new InTri(i+1,j,k));
					}
				}

				if(i > 0) {
					int k = grid_w[i-1][j] - grid_w[i][j]; 
					if(k < 0) {
						pq.add(new InTri(i-1,j,0));
					} else {
						pq.add(new InTri(i-1,j,k));
					}
				}

				if(j < c) {
					int k = grid_w[i][j+1] - grid_w[i][j]; 
					if (k < 0) {
						pq.add(new InTri(i,j+1,0));
					} else {
						pq.add(new InTri(i,j+1,k));
					}
				}

				if(j > 0) {
					int k = grid_w[i][j-1] - grid_w[i][j]; 
					if (k < 0) {
						pq.add(new InTri(i,j-1,0));
					} else {
						pq.add(new InTri(i,j-1,k));
					}
				}
			}
			
			i = pq.peek().r();
		    j = pq.peek().c();

		}

		return result;
	}
}

class InTri implements Comparable<InTri>{
	public Integer r;
	public Integer c;
	public Integer dis;

	public InTri(int r, int c, int dis){
		this.r = r;
		this.c = c;
		this.dis = dis;
	}
		
	public int compareTo(InTri x) {
		return this.dis - x.dis;
	}

	public Integer r() {return r;}
	public Integer c() {return c;}
	public Integer dis() {return dis;}
}