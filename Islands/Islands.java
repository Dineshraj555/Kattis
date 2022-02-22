// S Dinesh Raj
// A0227252L 
import java.io.*;
import java.util.*;


public class Islands {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int result = 0;

		String[] input = br.readLine().split(" ");
		int r = Integer.parseInt(input[0]); // row
		int c = Integer.parseInt(input[1]); // column
	
		char[][] grid = new char[r][c];
		int [][] visited = new int[r][c];

		for(int i = 0; i < r; i++){
			String row = br.readLine();
			for(int j = 0; j < c; j++) {
				grid[i][j] = row.charAt(j);
				visited[i][j] = 0; // 0 -> not visited
			}
		}

		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++) {
				if (visited[i][j] == 0 && grid[i][j] == 'L') {
					dfs(grid,visited,i,j);
					result += 1;
				}
			}
		}

		pw.println(result);
		pw.close();
		br.close();
	}

	// W -> WATER
	// L -> Land
	// C -> Water || Land

	// Check all directions up, down, left, right
	public static void dfs(char[][] grid, int[][] visited, int r, int c) {
		visited[r][c] = 1;

		// up
		if (r - 1 > -1) {            
			if (grid[r-1][c] == 'C' || grid[r-1][c] == 'L') {
				if (visited[r-1][c] == 0) {
					dfs(grid,visited,r-1,c);
				}
			}
		}

		// down
		if (r + 1 < grid.length) { 
			if (grid[r+1][c] == 'C' || grid[r+1][c] == 'L') {
				if (visited[r+1][c] == 0) {
					dfs(grid,visited,r+1,c);
				}
			}
		}

		// left
		if (c - 1 > -1) {     
			if (grid[r][c-1] == 'C' || grid[r][c-1] == 'L') {
				if (visited[r][c-1] == 0) {
					dfs(grid,visited,r,c-1);
				}
			}
		} 

		// right
		if (c + 1 < grid[r].length) {
			if (grid[r][c+1] == 'C' || grid[r][c+1] == 'L') {
				if (visited[r][c+1] == 0) {
					dfs(grid,visited,r,c+1);
				}
			}
		}
	}
}