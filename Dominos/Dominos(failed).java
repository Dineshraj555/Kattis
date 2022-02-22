// S Dinesh Raj
// A0227252L 
import java.io.*;
import java.util.*;


public class Dominos {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int testcases = Integer.parseInt(br.readLine());

		for (int i = 0; i < testcases; i++) {
			String[] input = br.readLine().split(" ");
		    int n = Integer.parseInt(input[0]); // no of tiles (vertex)
		    int m = Integer.parseInt(input[1]); // no of lines (edges)
            Dominos d = new Dominos();

            ArrayList < ArrayList < Integer > > AdjList = new ArrayList < ArrayList < Integer > >();
            for (int w = 0; w < n; w++) {
                ArrayList < Integer > Neighbor = new ArrayList < Integer >();
                AdjList.add(Neighbor); 
            }

		    for(int j = 0; j < m; j++) {
		    	String[] in = br.readLine().split(" ");
		    	int x = Integer.parseInt(in[0]) - 1; 
		    	int y = Integer.parseInt(in[1]) - 1;
                AdjList.get(x).add(y);
            
		    }

            pw.println(d.noSCC(AdjList));
		}

        pw.close();
		br.close();
	}

    public void DFS(ArrayList<ArrayList<Integer>> graph, int v, boolean[] visited, ArrayList<Integer> o) {
        visited[v] = true;
        for (int i = 0; i < graph.get(v).size(); i++){
            if (visited[graph.get(v).get(i)] == false){
                DFS(graph, graph.get(v).get(i), visited, o);
            }
        }
        o.add(v);
    }

    public ArrayList<Integer> postOrder(ArrayList<ArrayList<Integer>> graph, boolean[] visited) {        
        
        int V = graph.size();
        ArrayList<Integer> o = new ArrayList<Integer>();

        for (int i = 0; i < V; i++) {
            if (visited[i] == false) {
                DFS(graph, i, visited, o);
            }
        }

        return o;
    }

    public long noSCC(ArrayList<ArrayList<Integer>> graph) {
        int V = graph.size();
        boolean[] visited = new boolean[V];
        ArrayList<Integer> order = postOrder(graph, visited);       
        Collections.reverse(order);
 
        long result = 0;
        visited = new boolean[V];
        for (int i = 0; i < order.size(); i++) {
            int v = order.get(i);
            if (visited[v] == false) {
                result+=1;
                ArrayList<Integer> o = new ArrayList<>();
                DFS(graph, v, visited, o);
            } 
        }
        return result;
    }
}