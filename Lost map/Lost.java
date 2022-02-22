// S Dinesh Raj
// A0227252L 
import java.io.*;
import java.util.*;


public class Lost {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int n = Integer.parseInt(br.readLine());
		int[][] table = new int[n][n];

		for(int i = 0; i < n; i++){
			String[] row = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				table[i][j] = Integer.parseInt(row[j]);
			}
		}

		ArrayList<InTri> Edgelist = new ArrayList<InTri>();
		for(int i = 0; i < n; i++){
			int w = i + 1;
			for(int j = w; j < n; j++) {
				InTri edge = new InTri(i,j,table[i][j]);
				Edgelist.add(edge);
			}
		}
		
		Collections.sort(Edgelist);
		UnionFind set = new UnionFind(n);

		for(InTri egde : Edgelist) {
			Integer u = egde.u();
			Integer v = egde.v();
			if (!set.isSameSet(u,v)) {
				set.unionSet(u,v);
				int w = u+1;
				int y = v+1;
				pw.println(w + " " + y);
			}

		}

		pw.close();
		br.close();
	}
}

class UnionFind {
	public int[] p;
	public int[] rank;
	public int[] count;

	public UnionFind(int N) {
		p = new int[N];
		rank = new int[N];
		count = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
			rank[i] = 0;
			count[i] = 1;
		}
	}

	public Boolean add(int x) {
		if (count[x] > 0) {
			count[x]-=1;
			return true;
		} else {
			return false;
		}
	}

	public int findSet(int i) { 
		if (p[i] == i) {
			return i;
		} else {
			p[i] = findSet(p[i]);
			return p[i]; 
		}
	}

	public Boolean isSameSet(int i, int j) {
		return findSet(i) == findSet(j); 
    }

    public void unionSet(int i, int j) { 
    	if (!isSameSet(i, j)) { 
    		int x = findSet(i);
    		int y = findSet(j);
    		// rank is used to keep the tree short
    		if (rank[x] > rank[y]) {
    			p[y] = x;
    			count[x] += count[y];
    		} else { 
    			p[x] = y;
    			if (rank[x] == rank[y]) {
    				rank[y] = rank[y] + 1;
    			}
    			count[y] += count[x];
    		} 
    	} 
    }
}

class InTri implements Comparable<InTri>{
	public Integer u;
	public Integer v;
	public Integer dis;

	public InTri(int u, int v, int dis){
		this.u = u;
		this.v = v;
		this.dis = dis;
	}
	
	public int compareTo(InTri x) {
		return this.dis - x.dis;
	}

	public Integer u() {return u;}
	public Integer v() {return v;}
	public Integer dis() {return dis;}

}