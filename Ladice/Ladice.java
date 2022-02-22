// S Dinesh Raj
// A0227252L 
import java.io.*;
import java.util.*;


public class Ladice {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]); // no of items
		int l = Integer.parseInt(input[1]); // no of drawers

		UnionFind dis_set = new UnionFind(l);

		for(int i = 0; i < n; i++) {
			String[] in = br.readLine().split(" ");
			int a = Integer.parseInt(in[0]) - 1;
			int b = Integer.parseInt(in[1]) - 1;

			dis_set.unionSet(a,b);
			int c = dis_set.findSet(a);
			boolean success = dis_set.add(c);

		    if (success == true) {
		    	pw.println("LADICA");
		    } else {
		    	pw.println("SMECE");
		    }

		}
		pw.close();
		br.close();
	}

	static class UnionFind {
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
}